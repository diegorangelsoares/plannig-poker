package com.diego.planningpoker.api.services;

import com.diego.planningpoker.api.exception.RecursoNaoEncontradoException;
import com.diego.planningpoker.domain.Historia;
import com.diego.planningpoker.presentation.assembler.HistoriaAssembler;
import com.diego.planningpoker.presentation.assembler.PlanningAssembler;
import com.diego.planningpoker.presentation.assembler.VotoAssembler;
import com.diego.planningpoker.presentation.dto.http.request.PlanningRequest;
import com.diego.planningpoker.domain.Planning;
import com.diego.planningpoker.infrastructure.persistence.PlanningRepository;
import com.diego.planningpoker.infrastructure.gson.GsonLocalDateSerializer;
import com.diego.planningpoker.infrastructure.gson.GsonLocalDateTimeSerializer;
import com.diego.planningpoker.presentation.dto.http.response.HistoriaResponse;
import com.diego.planningpoker.presentation.dto.http.response.PlanningResponse;
import com.diego.planningpoker.presentation.dto.http.response.VotoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PlanningServiceImpl implements PlanningService {

    private final PlanningRepository planningRepository;

    public PlanningServiceImpl(PlanningRepository planningRepository) {
        this.planningRepository = planningRepository;
    }

    @Override
    public Planning cadastrar (PlanningRequest planningRequest){
        Planning planning = new Planning();
        planning.setTitulo(planningRequest.getTitulo());
        planning.setObservacao(planningRequest.getObservacao());
        planning.setEquipe(planningRequest.getEquipe());
        planning.setStatus("PENDENTE");
        return planningRepository.save(planning);
    }

    @Override
    public Page<Planning> listarTodos(long page, long size, String sort){

        String[] sortParams = sort.split(",");
        String sortPropery = sortParams.length > 0 ? sortParams[0] : "id";
        String sortDirection  = sortParams.length > 1 ? sortParams[1] : "asc";
        Sort sortObj = Sort.by(Sort.Direction.fromString(sortDirection), sortPropery);
        Pageable pageable = PageRequest.of((int) page, (int) size, sortObj);
        try {
            return planningRepository.findAll(pageable);
        }catch (Exception e){
            log.error("Erro na consulta: "+e.toString());
            throw e;
        }
    }

    @Override
    public Planning buscarPorId(Long id){
        return planningRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Planning com id "+id+" não encontrada.")));
    }

    @Override
    public void deletePorId(long id) {
        Planning planning = planningRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Planning com id "+id+" não encontrado para remoção.")));
        planningRepository.delete(planning);
    }

    @Override
    public Planning alterarValores(long id, PlanningRequest planningRequest) {
        Planning planning = planningRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Planning com id "+id+" não encontrado para remoção.")));
        if (planningRequest.getObservacao() != null) planning.setObservacao(planningRequest.getObservacao());
        if (planningRequest.getTitulo() != null) planning.setTitulo(planningRequest.getTitulo());
        return planningRepository.save(planning);
    }

    @Override
    public Planning alterar(long id, Planning planning) {
        Planning planningAtual = planningRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Planning com id "+id+" não encontrado para remoção.")));
        BeanUtils.copyProperties(planning, planningAtual, "id_planning");
        return planningRepository.save(planningAtual);
    }


    private Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, GsonLocalDateSerializer.INSTANCE)
                .registerTypeAdapter(LocalDateTime.class, GsonLocalDateTimeSerializer.INSTANCE)
                .setPrettyPrinting()
                .create();
    }

    public PlanningResponse convertToResponse(Planning entity) {
        return PlanningAssembler.convertToResponse(entity);
    }

    public Page<PlanningResponse> convertToPageResponse(Page<Planning> entitys) {
        return toPageObjectDto(entitys);
    }

    public Page<PlanningResponse> toPageObjectDto(Page<Planning> objects) {
        Page<PlanningResponse> dtos  = objects.map(this::convertToObjectDto);
        return dtos;
    }

    public PlanningResponse convertToObjectDto(Planning planning) {

        List<HistoriaResponse> historias = Optional
                .ofNullable(planning.getHistorias())
                .orElse(new ArrayList<>())
                .stream()
                .map(HistoriaAssembler::convertToResponse)
                .toList();

        return PlanningResponse.builder()
                .observacao(planning.getObservacao())
                .titulo(planning.getTitulo())
                .status(planning.getStatus())
                .historias(historias)
                .build();
    }



}
