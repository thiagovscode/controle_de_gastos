package com.br.controledegastos.gateway;

import com.br.controledegastos.domain.Categoria;
import com.br.controledegastos.domain.Tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagGateway {

    Tag salvar(Tag tag);

    Optional<Tag> buscarPorUid(UUID uid);

    // 🔑 ADICIONE ESTES
    List<Tag> listarAtivas();

    List<Tag> listarTodas();

    boolean existePorNome(String nome);
}
