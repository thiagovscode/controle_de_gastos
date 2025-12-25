package com.br.controledegastos.usecase.impl;

import com.br.controledegastos.domain.Tag;
import com.br.controledegastos.gateway.TagGateway;
import com.br.controledegastos.usecase.ListarTagsAtivasUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarTagsAtivasUseCaseImpl implements ListarTagsAtivasUseCase {

    private final TagGateway tagGateway;

    @Override
    public List<Tag> executar() {
        return tagGateway.listarAtivas();
    }
}


