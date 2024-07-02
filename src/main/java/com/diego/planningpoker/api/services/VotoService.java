package com.diego.planningpoker.api.services;

import com.diego.planningpoker.presentation.dto.http.request.VotoRequest;
import com.diego.planningpoker.domain.Voto;
import com.diego.planningpoker.presentation.dto.http.response.VotoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface VotoService {

    Voto cadastrar (VotoRequest votoRequest);

    Page<Voto> listarTodos(Pageable pageable);

    List<VotoResponse> buscarPorIdHistoria(Long id);

    List<String> votaram (long idHistoria);

    void deletePorId(long id);

    void deletePorHistoria(long id);

    Page<VotoResponse> convertToPageResponse(Page<Voto> entitys);

    Page<VotoResponse> toPageObjectDto(Page<Voto> objects);

    VotoResponse convertToObjectDto(Voto voto);



}
