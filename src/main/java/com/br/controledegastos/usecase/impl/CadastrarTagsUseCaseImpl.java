package com.br.controledegastos.usecase.impl;

import com.br.controledegastos.domain.Tag;
import com.br.controledegastos.gateway.TagGateway;
import com.br.controledegastos.usecase.CadastrarTagsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarTagsUseCaseImpl implements CadastrarTagsUseCase {

    private final TagGateway tagGateway;

    @Override
    public Tag executar(Tag tag) {

        if (tagGateway.existePorNome(tag.getNome())) {
            throw new IllegalArgumentException("Tag já cadastrada");
        }

        return tagGateway.salvar(tag);
    }
}
