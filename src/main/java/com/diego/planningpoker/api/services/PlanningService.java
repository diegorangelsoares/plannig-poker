package com.diego.planningpoker.api.services;

import com.diego.planningpoker.presentation.assembler.PlanningAssembler;
import com.diego.planningpoker.presentation.dto.http.request.PlanningRequest;
import com.diego.planningpoker.domain.Planning;
import com.diego.planningpoker.presentation.dto.http.response.PlanningResponse;
import org.springframework.data.domain.Page;


public interface PlanningService {

    Planning cadastrar (PlanningRequest testeRequest);

    Page<Planning> listarTodos(long page, long size, String sort);

    Planning buscarPorId(Long id);

    void deletePorId(long id);

    Planning alterarValores(long id, PlanningRequest planningRequest);

    Planning alterar(long id, Planning planning);

    PlanningResponse convertToResponse(Planning entity);

    Page<PlanningResponse> convertToPageResponse(Page<Planning> entitys);
}
