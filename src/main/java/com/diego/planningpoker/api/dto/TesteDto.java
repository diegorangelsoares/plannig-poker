package com.diego.planningpoker.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class TesteDto implements Serializable {
//
//    @Serial
//    private static final long serialVersionUID = 2L;

    public String texto;

    @Override
    public String toString() {
        return "TesteDto{" +
                "texto=" + texto + '\'' +
                '}';
    }
}
