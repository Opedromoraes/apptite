package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "cardapio")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", nullable = false)
    String nome;

    @OneToOne(mappedBy = "cardapio")
    private Restaurante restaurante;

    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Categoria> categorias;

}
