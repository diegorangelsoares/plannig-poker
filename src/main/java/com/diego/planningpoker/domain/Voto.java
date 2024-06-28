package com.diego.planningpoker.domain;


import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_voto")
public class Voto extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_voto", updatable = false, nullable = false)
    private Long id;

    @Column(name = "ds_votante", nullable = false)
    public String votante;

    @Column(name = "nu_valor_orcamento", nullable = false)
    public Integer orcamento;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "FK_ID_HISTORIA",
            foreignKey = @ForeignKey(name = "FK_VOTO_ID_HISTORIA"))
    @ToString.Exclude
    private Historia historia;


}
