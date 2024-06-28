package com.diego.planningpoker.domain;


import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class BaseEntity implements Serializable, Cloneable {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "SEQUENCE_GENERATOR")
	private Long id;

	@javax.persistence.Column(name = "TT_DATA_CRIACAO")
	@CreationTimestamp
	@Hidden
	private LocalDateTime dataCriacao;

	@javax.persistence.Column(name = "TT_DATA_ULTIMA_ATUALIZACAO")
	@UpdateTimestamp
	@Hidden
	private LocalDateTime dataUltimaAtualizacao;

	@javax.persistence.Column(name = "FL_DELETADO")
	@Hidden
	private boolean deletado;
}