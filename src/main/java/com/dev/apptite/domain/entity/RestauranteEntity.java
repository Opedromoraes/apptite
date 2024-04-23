package com.dev.apptite.domain.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "restaurante")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class RestauranteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String nome;
}
