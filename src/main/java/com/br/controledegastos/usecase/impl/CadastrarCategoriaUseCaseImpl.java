package com.br.controledegastos.usecase.impl;

import com.br.controledegastos.domain.Categoria;
import com.br.controledegastos.gateway.CategoriaGateway;
import com.br.controledegastos.usecase.CadastrarCategoriaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarCategoriaUseCaseImpl implements CadastrarCategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    @Override
    public Categoria executar(Categoria categoria) {

        if (categoriaGateway.existePorNome(categoria.getNome())) {
            throw new IllegalArgumentException("Categoria já cadastrada");
        }

        return categoriaGateway.salvar(categoria);
    }
}
