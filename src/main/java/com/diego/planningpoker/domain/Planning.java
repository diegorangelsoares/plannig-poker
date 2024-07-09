package com.diego.planningpoker.domain;


import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_planning")
public class Planning extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_planning", updatable = false, nullable = false)
    private Long id;

    @Column(name = "ds_titulo")
    public String titulo;

    @Column(name = "ds_observacao")
    public String observacao;

    @Column(name = "ds_equipe")                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    public String equipe;

    @Column(name = "ds_status")
    public String status;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, mappedBy = "planning")
    public List<Historia> historias;

}
