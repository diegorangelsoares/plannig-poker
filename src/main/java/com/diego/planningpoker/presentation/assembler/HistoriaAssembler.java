package com.diego.planningpoker.presentation.assembler;

import com.diego.planningpoker.domain.Historia;
import com.diego.planningpoker.presentation.dto.http.request.HistoriaRequest;
import com.diego.planningpoker.presentation.dto.http.response.HistoriaResponse;
import com.diego.planningpoker.presentation.dto.http.response.PlanningResponse;
import com.diego.planningpoker.presentation.dto.http.response.VotoResponse;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoriaAssembler {

    private HistoriaAssembler() {}

    public static Historia convertToEntity(HistoriaRequest request) {

        return Historia.builder()
                .card(request.getCard())
                .descricao(request.getDescricao())
            .build();
    }

    public static HistoriaResponse convertToResponse(Historia entity) {

        List<VotoResponse> votos = Optional
            .ofNullable(entity.getVotos())
            .orElse(new ArrayList<>())
            .stream()
            .map(VotoAssembler::convertToResponse)
            .toList();

        return HistoriaResponse.builder()
            .id(entity.getId())
            .status(entity.getStatus())
            .descricao(entity.getDescricao())
            .card(entity.getCard())
            .votos(votos)
            .build();
    }

//    public static Page<HistoriaResponse> toPageObjectDto(Page<Historia> objects) {
//        Page<HistoriaResponse> dtos  = objects.map(this::convertToObjectDto);
//        return dtos;
//    }
//
//    public HistoriaResponse convertToObjectDto(Historia historia) {
//
//        List<VotoResponse> votos = Optional
//                .ofNullable(historia.getVotos())
//                .orElse(new ArrayList<>())
//                .stream()
//                .map(VotoAssembler::convertToResponse)
//                .toList();
//
//        return HistoriaResponse.builder()
//                .status(historia.getStatus())
//                .id(historia.getId())
//                .card(historia.getCard())
//                .votos(votos)
//                .build();
//    }


}
