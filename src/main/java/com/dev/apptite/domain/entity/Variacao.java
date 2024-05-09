package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "variacao")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Variacao {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "O nome é obrigatório")
        @Column(name = "nome", nullable = false)
        String nome;

        @ManyToOne
        @JoinColumn(name = "item_id", nullable = false)
        private Item item;
}
