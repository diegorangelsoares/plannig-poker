package com.diego.planningpoker.api.services;

import com.diego.planningpoker.api.exception.RecursoNaoEncontradoException;
import com.diego.planningpoker.api.requests.HistoriaRequest;
import com.diego.planningpoker.domain.Historia;
import com.diego.planningpoker.domain.Planning;
import com.diego.planningpoker.infrastructure.persistence.HistoriaRepository;
import com.diego.planningpoker.infrastructure.persistence.PlanningRepository;
import com.diego.planningpoker.infrastructure.gson.GsonLocalDateSerializer;
import com.diego.planningpoker.infrastructure.gson.GsonLocalDateTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
public class HistoriaServiceImpl implements HistoriaService {

    private final HistoriaRepository historiaRepository;

    private final PlanningRepository planningRepository;

    public HistoriaServiceImpl(HistoriaRepository historiaRepository, PlanningRepository planningRepository) {
        this.historiaRepository = historiaRepository;
        this.planningRepository = planningRepository;
    }

    @Override
    public Historia cadastrar (HistoriaRequest historiaRequest){
        Planning planning = planningRepository.findById(historiaRequest.getIdPlanning()).orElseThrow((() -> new RecursoNaoEncontradoException("Planning com id "+historiaRequest.getIdPlanning()+" não encontrada.")));
        Historia historia = new Historia();
        historia.setCard(historiaRequest.getCard());
        historia.setDescricao(historiaRequest.getDescricao());
        historia.setPlanning(planning);
        historia.setStatus("INICIADA");
        return historiaRepository.save(historia);
    }

    @Override
    public Page<Historia> listarTodos(long page, long size, String sort){

        String[] sortParams = sort.split(",");
        String sortPropery = sortParams.length > 0 ? sortParams[0] : "id";
        String sortDirection  = sortParams.length > 1 ? sortParams[1] : "asc";
        Sort sortObj = Sort.by(Sort.Direction.fromString(sortDirection), sortPropery);
        Pageable pageable = PageRequest.of((int) page, (int) size, sortObj);
        try {
            return historiaRepository.findAll(pageable);
        }catch (Exception e){
            log.error("Erro na consulta: "+e.toString());
            throw e;
        }
    }

    @Override
    public Historia buscarPorId(Long id){
        return historiaRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Historia com id "+id+" não encontrada.")));
    }

    @Override
    public void deletePorId(long id) {
        Historia planning = historiaRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Historia com id "+id+" não encontrado para remoção.")));
        historiaRepository.delete(planning);
    }

    @Override
    public Historia alterarValores(long id, HistoriaRequest historiaRequest) {
        Historia historia = historiaRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Historia com id "+id+" não encontrado para remoção.")));
        if (historiaRequest.getCard() != null) historia.setCard(historiaRequest.getCard());
        if (historiaRequest.getDescricao() != null) historia.setDescricao(historiaRequest.getDescricao());
        if (historiaRequest.getIdPlanning() != null) {
            Planning planning = planningRepository.findById(historiaRequest.getIdPlanning()).orElseThrow((() -> new RecursoNaoEncontradoException("Planning com id "+historiaRequest.getIdPlanning()+" não encontrada.")));
            historia.setPlanning(planning);
        }
        return historiaRepository.save(historia);
    }

    @Override
    public Historia alterar(long id, Historia planning) {
        Historia planningAtual = historiaRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Historia com id "+id+" não encontrado para remoção.")));
        BeanUtils.copyProperties(planning, planningAtual, "id_planning");
        return historiaRepository.save(planningAtual);
    }


    private Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, GsonLocalDateSerializer.INSTANCE)
                .registerTypeAdapter(LocalDateTime.class, GsonLocalDateTimeSerializer.INSTANCE)
                .setPrettyPrinting()
                .create();
    }



}
