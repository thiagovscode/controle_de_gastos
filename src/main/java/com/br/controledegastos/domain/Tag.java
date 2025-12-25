package com.br.controledegastos.domain;

import lombok.Getter;

import java.util.UUID;


@Getter
public class Tag {


    private final UUID uid;
    private final String nome;
    private final boolean ativa;

    public Tag( String nome) {
        this.uid = UUID.randomUUID();
        this.nome = nome;
        this.ativa = true;
    }


    public Tag(UUID uid, String nome, boolean ativa) {
        this.uid = uid;
        this.nome = nome;
        this.ativa = ativa;
    }


    public Tag inativar() {
        if (!this.ativa) {
            return this; // idempotente
        }
        return new Tag(this.uid, this.nome, false);
    }

    public Tag ativa() {
        if (this.ativa) {
            return this; // idempotente
        }
        return new Tag(this.uid, this.nome, true);
    }
}
