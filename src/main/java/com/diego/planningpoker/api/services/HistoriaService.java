package com.diego.planningpoker.api.services;

import com.diego.planningpoker.presentation.assembler.VotoAssembler;
import com.diego.planningpoker.presentation.dto.http.request.HistoriaRequest;
import com.diego.planningpoker.domain.Historia;
import com.diego.planningpoker.presentation.dto.http.response.HistoriaResponse;
import com.diego.planningpoker.presentation.dto.http.response.VotoResponse;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface HistoriaService {

    Historia cadastrar (HistoriaRequest historiaRequest);

    Page<Historia> listarTodos(long page, long size, String sort);

    Historia buscarPorId(Long id);

    void deletePorId(long id);

    Historia alterarValores(long id, HistoriaRequest historiaRequest);

    Historia alterar(long id, Historia planning);

    Page<HistoriaResponse> convertToPageResponse(Page<Historia> entitys);

    Page<HistoriaResponse> toPageObjectDto(Page<Historia> objects);

    HistoriaResponse convertToObjectDto(Historia historia);

}
