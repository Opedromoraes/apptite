package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;


@Entity
@Table(name = "restaurante")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", nullable = false)
    String nome;

    @NotBlank(message = "O endereço é obrigatório")
    @Column(name = "endereco", nullable = false)
    String endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cardapio_id", referencedColumnName = "id")
    private Cardapio cardapio;

    @OneToMany(mappedBy = "restaurante",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Mesa> mesas;

}
