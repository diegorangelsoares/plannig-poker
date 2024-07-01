package com.diego.planningpoker.presentation.dto.http.response;

import com.diego.planningpoker.domain.Historia;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
@JsonInclude(Include.NON_NULL)
public class VotoResponse {

    private Long id;

    public String votante;

    public Integer orcamento;

}
