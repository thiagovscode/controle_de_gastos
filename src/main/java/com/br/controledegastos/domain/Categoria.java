package com.br.controledegastos.domain;


import lombok.Getter;

import java.util.UUID;

@Getter
public class Categoria {

    private final UUID uid;
    private final String nome;
    private final String tipo;
    private final boolean ativa;

    public Categoria(String nome, String tipo) {
        this.uid = UUID.randomUUID();
        this.nome = nome;
        this.tipo = tipo;
        this.ativa = true;
    }

    public Categoria(UUID uid, String nome, String tipo, boolean ativa) {
        this.uid = uid;
        this.nome = nome;
        this.tipo = tipo;
        this.ativa = ativa;
    }


    public Categoria inativar() {
        if (!this.ativa) {
            return this; // idempotente
        }
        return new Categoria(this.uid, this.nome, this.tipo, false);
    }

    public Categoria ativa() {
        if (this.ativa) {
            return this; // idempotente
        }
        return new Categoria(this.uid, this.nome, this.tipo, true);
    }

}

