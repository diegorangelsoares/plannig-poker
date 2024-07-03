package com.diego.planningpoker.domain;

import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_historia")
public class Historia extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_historia", updatable = false, nullable = false)
    private Long id;

    @Column(name = "ds_descricao")
    public String descricao;

    @Column(name = "ds_card")
    public String card;

    @Column(name = "ds_status")
    public String status;

    @Column(name = "nu_orcamento")
    public Integer orcamento;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "FK_ID_PLANNING",
            foreignKey = @ForeignKey(name = "FK_HISTORIA_ID_PLANNING"))
    @ToString.Exclude
    private Planning planning;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER, mappedBy = "historia")
    public List<Voto> votos;

}
