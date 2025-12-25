package com.br.controledegastos.usecase;

import com.br.controledegastos.domain.Categoria;

import java.util.List;

public interface ListarCategoriasUseCase {

    List<Categoria> executar();
}
