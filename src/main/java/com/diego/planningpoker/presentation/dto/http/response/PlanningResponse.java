package com.diego.planningpoker.presentation.dto.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
@JsonInclude(Include.NON_NULL)
public class PlanningResponse {

    private Long id;

    public String titulo;

    public String observacao;

    public String equipe;

    public String status;

    public List<HistoriaResponse> historias;

}
