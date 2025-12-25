package com.br.controledegastos.entrypoint.api;

import com.br.controledegastos.entrypoint.api.model.CategoriaRequest;
import com.br.controledegastos.entrypoint.api.model.CategoriaResponse;
import com.br.controledegastos.mapper.CategoriaMapper;
import com.br.controledegastos.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CadastrarCategoriaUseCase cadastrarCategoriaUseCase;
    private final InativarCategoriaUseCase inativarCategoriaUseCase;
    private final ListarCategoriasAtivasUseCase listarCategoriasAtivasUseCase;
    private final ListarCategoriasUseCase listarCategoriasUseCase;
    private final AtivarCategoriasUseCase ativarCategoriasUseCase;

    private final CategoriaMapper categoriaMapper;

    @PostMapping
    public ResponseEntity<CategoriaResponse> criar(@RequestBody CategoriaRequest request) {

        var categoriaDomain = categoriaMapper.toDomain(request);
        var criada = cadastrarCategoriaUseCase.executar(categoriaDomain);
        var response = categoriaMapper.toResponse(criada);

        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{uid}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable UUID uid) {
        inativarCategoriaUseCase.executar(uid);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listar() {

        var categorias = listarCategoriasAtivasUseCase.executar();
        var response = categorias.stream()
                .map(categoriaMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/todas")
    public ResponseEntity<List<CategoriaResponse>> listarTodas() {

        var categorias = listarCategoriasUseCase.executar();
        var response = categorias.stream()
                .map(categoriaMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{uid}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable UUID uid) {
        ativarCategoriasUseCase.executar(uid);
        return ResponseEntity.noContent().build();
    }


}
