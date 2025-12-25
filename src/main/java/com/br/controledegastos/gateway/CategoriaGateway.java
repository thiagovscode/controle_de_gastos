package com.br.controledegastos.gateway;

import com.br.controledegastos.domain.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaGateway {

    Categoria salvar(Categoria categoria);

    Optional<Categoria> buscarPorUid(UUID uid);

    // 🔑 ADICIONE ESTES
    List<Categoria> listarAtivas();

    List<Categoria> listarTodas();

    boolean existePorNome(String nome);
}
