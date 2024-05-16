package com.dev.apptite.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome")
    String nome;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_cardapio", insertable = false, updatable = false)
    @JsonIgnore
    private Cardapio cardapio;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Item> item;

}
