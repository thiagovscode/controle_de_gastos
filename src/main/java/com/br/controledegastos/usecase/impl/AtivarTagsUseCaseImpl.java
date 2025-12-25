package com.br.controledegastos.usecase.impl;

import com.br.controledegastos.gateway.CategoriaGateway;
import com.br.controledegastos.usecase.AtivarTagsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service // 🔑 ISSO É O MAIS IMPORTANTE
@RequiredArgsConstructor
public class AtivarTagsUseCaseImpl implements AtivarTagsUseCase {

    private final CategoriaGateway categoriaGateway;

    @Override
    public void executar(UUID uid) {
        var categoria = categoriaGateway.buscarPorUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("Tag não encontrada"));

        if (categoria.isAtiva()) {
            return;
        }

        categoriaGateway.salvar(categoria.ativa());
    }
}
