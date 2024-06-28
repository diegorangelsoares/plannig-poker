package com.diego.planningpoker.api.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

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


}
