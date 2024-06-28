package com.diego.planningpoker.api.services;

import com.diego.planningpoker.api.requests.PlanningRequest;
import com.diego.planningpoker.domain.Planning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PlanningService {

    Planning cadastrar (PlanningRequest testeRequest);

    Page<Planning> listarTodos(long page, long size, String sort);

    Planning buscarPorId(Long id);

    void deletePorId(long id);

    Planning alterarValores(long id, PlanningRequest planningRequest);

    Planning alterar(long id, Planning planning);


}
