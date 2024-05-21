package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    private Long idCardapio;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany
    @JoinColumn(name = "id_cardapio")
    private List<Categoria> categorias;

    @OneToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
}