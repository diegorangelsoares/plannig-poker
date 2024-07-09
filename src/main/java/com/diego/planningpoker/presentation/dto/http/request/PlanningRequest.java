package com.diego.planningpoker.presentation.dto.http.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class PlanningRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1312L;

    @NotBlank
    @Size(min = 11, max = 11)
    @Schema(required = true, example = "Planning Poker para orçamentos")
    public String titulo;

    @Schema(example = "Observacao do planning", description = "Observacao do planning")
    public String observacao;

    @Schema(example = "HPay", description = "Equipe que está realizando o planning")
    public String equipe;

    @Schema(description = "Equipe que está realizando o planning")
    public List<HistoriaRequest> historias;


}
