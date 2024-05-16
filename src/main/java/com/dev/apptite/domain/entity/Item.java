package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", nullable = false)
    String nome;

    @NotBlank(message = "O preço é obrigatório")
    @Column(name = "nome", nullable = false)
    private Double preco;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Variacao> variacao;

//    @ManyToOne
//    @JoinColumn(name = "pedido_id",nullable = false)
//    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "pedido_id",nullable = false)
    private Categoria categoria;
}
