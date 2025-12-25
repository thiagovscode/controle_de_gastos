package com.br.controledegastos.gateway.impl.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID técnico (banco)

    @Column(nullable = false, unique = true)
    private UUID uid; // Identidade de negócio

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private boolean ativa;
}
