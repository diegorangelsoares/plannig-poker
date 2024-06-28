package com.diego.planningpoker.api.services;

import com.diego.planningpoker.api.requests.HistoriaRequest;
import com.diego.planningpoker.domain.Historia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface HistoriaService {

    Historia cadastrar (HistoriaRequest historiaRequest);

    Page<Historia> listarTodos(long page, long size, String sort);

    Historia buscarPorId(Long id);

    void deletePorId(long id);

    Historia alterarValores(long id, HistoriaRequest historiaRequest);

    Historia alterar(long id, Historia planning);


}
