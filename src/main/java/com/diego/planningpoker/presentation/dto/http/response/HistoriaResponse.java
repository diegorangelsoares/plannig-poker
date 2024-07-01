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
public class HistoriaResponse {

    private Long id;
    public String descricao;
    public String card;
    public String status;
    public List<VotoResponse> votos;

}
