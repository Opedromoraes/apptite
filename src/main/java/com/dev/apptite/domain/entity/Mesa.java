package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "mesa")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O número é obrigatório")
    @Column(name = "numero", nullable = false)
    int numero;

    @ManyToOne
    @JoinColumn(name = "restaurante_id",nullable = false)
    private Restaurante restaurante;
}
