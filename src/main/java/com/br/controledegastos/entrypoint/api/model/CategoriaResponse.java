package com.br.controledegastos.entrypoint.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaResponse {

    private UUID uid;
    private String nome;
    private String tipo;
    private boolean ativa;
}
