package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "produto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", nullable = false)
    String nome;

    @ManyToOne
    @JoinColumn(name = "cardapio_id", nullable = false)
    private Cardapio cardapio;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Variacao> variacao;

    @ManyToOne
    @JoinColumn(name = "pedido_id",nullable = false)
    private Pedido pedido;
}
