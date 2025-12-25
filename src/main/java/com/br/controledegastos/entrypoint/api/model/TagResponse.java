package com.br.controledegastos.entrypoint.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagResponse {

    private UUID uid;
    private String nome;
    private boolean ativa;
}
