package com.diego.planningpoker.api.services;

import com.diego.planningpoker.api.exception.RecursoNaoEncontradoException;
import com.diego.planningpoker.presentation.dto.http.request.VotoRequest;
import com.diego.planningpoker.domain.Historia;
import com.diego.planningpoker.domain.Voto;
import com.diego.planningpoker.infrastructure.persistence.HistoriaRepository;
import com.diego.planningpoker.infrastructure.persistence.VotoRepository;
import com.diego.planningpoker.infrastructure.gson.GsonLocalDateSerializer;
import com.diego.planningpoker.infrastructure.gson.GsonLocalDateTimeSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;

    private final HistoriaRepository historiaRepository;

    public VotoServiceImpl(VotoRepository votoRepository, HistoriaRepository historiaRepository) {
        this.votoRepository = votoRepository;
        this.historiaRepository = historiaRepository;
    }

    @Override
    public Voto cadastrar (VotoRequest votoRequest){
        Historia historia = historiaRepository.findById(votoRequest.getIdHistoria()).orElseThrow((() -> new RecursoNaoEncontradoException("Historia com id "+votoRequest.getIdHistoria()+" não encontrada.")));
        Voto voto = new Voto();
        voto.setOrcamento(votoRequest.getOrcamento());
        voto.setVotante(votoRequest.getVotante());
        voto.setHistoria(historia);
        return votoRepository.save(voto);
    }

    @Override
    public Page<Voto> listarTodos(Pageable pageable){
        try {
            return votoRepository.findAll(pageable);
        }catch (Exception e){
            log.error("Erro na consulta: "+e.toString());
            throw e;
        }
    }

    @Override
    public List<Voto> buscarPorIdHistoria(Long id){
        Historia historia = historiaRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Historia com id "+id+" não encontrado para remoção.")));
        return votoRepository.findVotoByHistoria(historia);
    }

    @Override
    public List<String> votaram (long idHistoria){
        List<Voto> votos = buscarPorIdHistoria(idHistoria);
        if (votos == null || votos.isEmpty()){
            return new ArrayList<>();
        }
        List<String> jaVotatam = new ArrayList<>();
        votos.forEach(voto -> jaVotatam.add(voto.getVotante()));
        return jaVotatam;
    }

    @Override
    public void deletePorId(long id) {
        Voto voto = votoRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Voto com id "+id+" não encontrado para remoção.")));
        votoRepository.delete(voto);
    }

    @Override
    public void deletePorHistoria(long id) {
        Historia historia = historiaRepository.findById(id).orElseThrow((() -> new RecursoNaoEncontradoException("Historia com id "+id+" não encontrado para remoção.")));
        votoRepository.deleteAllByHistoria(historia);
    }

    private Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, GsonLocalDateSerializer.INSTANCE)
                .registerTypeAdapter(LocalDateTime.class, GsonLocalDateTimeSerializer.INSTANCE)
                .setPrettyPrinting()
                .create();
    }



}
