package com.diego.planningpoker.api.requests;

import com.diego.planningpoker.domain.Historia;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Data
public class VotoRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1312L;

    @NotBlank
    @Size(min = 2, max = 100)
    @Schema(required = true, example = "Nome da pessoa que esta votando")
    public String votante;

    @Schema(example = "1", description = "Valor do orcamento")
    public Integer orcamento;

    @Schema(example = "1", description = "Identificacao da Historia")
    public Integer idHistoria;




}
