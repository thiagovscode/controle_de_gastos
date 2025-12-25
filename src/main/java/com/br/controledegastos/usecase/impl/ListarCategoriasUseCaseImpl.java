package com.br.controledegastos.usecase.impl;

import com.br.controledegastos.domain.Categoria;
import com.br.controledegastos.gateway.CategoriaGateway;
import com.br.controledegastos.usecase.ListarCategoriasUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarCategoriasUseCaseImpl implements ListarCategoriasUseCase {

    private final CategoriaGateway categoriaGateway;

    @Override
    public List<Categoria> executar() {
        return categoriaGateway.listarTodas();
    }
}


