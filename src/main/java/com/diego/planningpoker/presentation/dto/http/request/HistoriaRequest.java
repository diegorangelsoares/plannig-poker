package com.diego.planningpoker.presentation.dto.http.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Data
public class HistoriaRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1312L;

    @NotBlank
    @Size(min = 11, max = 11)
    @Schema(required = true, example = "Descricao da historia")
    public String descricao;

    @Schema(example = "HPAY-1", description = "Identificacao do card")
    public String card;

    @Schema(example = "1", description = "Identificacao da Planning")
    public Integer idPlanning;



}
