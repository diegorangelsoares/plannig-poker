package com.diego.planningpoker.presentation.assembler;


import com.diego.planningpoker.domain.Planning;
import com.diego.planningpoker.presentation.dto.http.request.PlanningRequest;
import com.diego.planningpoker.presentation.dto.http.response.HistoriaResponse;
import com.diego.planningpoker.presentation.dto.http.response.PlanningResponse;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanningAssembler {

    private PlanningAssembler() {}

    public static Planning convertToEntity(PlanningRequest request) {

        return Planning.builder()
                .equipe(request.getEquipe())
                .observacao(request.getObservacao())
                .titulo(request.getTitulo())
            .build();
    }

    public static PlanningResponse convertToResponse(Planning entity) {

        List<HistoriaResponse> historias = Optional
            .ofNullable(entity.getHistorias())
            .orElse(new ArrayList<>())
            .stream()
            .map(HistoriaAssembler::convertToResponse)
            .toList();

        return PlanningResponse.builder()
            .id(entity.getId())
            .status(entity.getStatus())
            .titulo(entity.getTitulo())
            .observacao(entity.getObservacao())
            .equipe(entity.getEquipe())

            .historias(historias)
            .build();
    }

//    public static Page<PlanningResponse> toPageObjectDto(Page<Planning> objects) {
//        Page<PlanningResponse> dtos  = objects.map(this::convertToObjectDto);
//        return dtos;
//    }
//
//    public static PlanningResponse convertToObjectDto(Planning planning) {
//
//        List<HistoriaResponse> historias = Optional
//                .ofNullable(planning.getHistorias())
//                .orElse(new ArrayList<>())
//                .stream()
//                .map(HistoriaAssembler::convertToResponse)
//                .toList();
//
//        return PlanningResponse.builder()
//                .observacao(planning.getObservacao())
//                .titulo(planning.getTitulo())
//                .status(planning.getStatus())
//                .equipe(planning.getEquipe())
//                .historias(historias)
//                .build();
//    }

}
