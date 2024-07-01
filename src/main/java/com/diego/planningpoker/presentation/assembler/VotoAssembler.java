package com.diego.planningpoker.presentation.assembler;

import com.diego.planningpoker.domain.Historia;
import com.diego.planningpoker.domain.Voto;
import com.diego.planningpoker.presentation.dto.http.request.HistoriaRequest;
import com.diego.planningpoker.presentation.dto.http.request.VotoRequest;
import com.diego.planningpoker.presentation.dto.http.response.HistoriaResponse;
import com.diego.planningpoker.presentation.dto.http.response.VotoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VotoAssembler {

    private VotoAssembler() {}

    public static Voto convertToEntity(VotoRequest request) {

        return Voto.builder()
                .votante(request.getVotante())
                .orcamento(request.getOrcamento())
            .build();
    }

    public static VotoResponse convertToResponse(Voto entity) {

        return VotoResponse.builder()
            .id(entity.getId())
            .votante(entity.getVotante())
            .orcamento(entity.getOrcamento())
            .build();
    }

//    public static InadimplenteResumoResponse convertToResumoResponse(Inadimplente entity) {
//
//        return InadimplenteResumoResponse.builder()
//                .id(entity.getId())
//                .idContaExterno(entity.getIdContaExterno())
//                .idPortadorExterno(entity.getIdPortadorExterno())
//                .status(entity.getStatus().name())
//                .idProdutoExterno(entity.getIdProdutoExterno())
//                .dataEntradaAtraso(entity.getDataEntradaAtraso())
//                .dataEntradaCreliq(entity.getDataEntradaCreliq())
//                .dataLiberacao(entity.getDataLiberacao())
//                .build();
//    }
}
