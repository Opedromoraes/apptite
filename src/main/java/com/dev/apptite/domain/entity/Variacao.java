package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Variacao {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idVariacao;

        @NotBlank(message = "O nome é obrigatório")
        @Column(name = "nome", nullable = false)
        private String nome;

        @Min(value = 0)
        @Column(name = "preço", nullable = false)
        private Double preco;

        @ManyToOne
        @JoinColumn(name = "item_id", nullable = false)
        private Item item;

}
