package com.br.controledegastos.usecase.impl;

import com.br.controledegastos.gateway.TagGateway;
import com.br.controledegastos.usecase.InativarTagsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service // 🔑 ISSO É O MAIS IMPORTANTE
@RequiredArgsConstructor
public class InativarTagUseCaseImpl implements InativarTagsUseCase {

    private final TagGateway TagGateway;

    @Override
    public void executar(UUID uid) {
        var tag = TagGateway.buscarPorUid(uid)
                .orElseThrow(() -> new IllegalArgumentException("Tag não encontrada"));

        if (!tag.isAtiva()) {
            return;
        }

        TagGateway.salvar(tag.inativar());
    }
}
