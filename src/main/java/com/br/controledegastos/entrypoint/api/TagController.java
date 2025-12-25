package com.br.controledegastos.entrypoint.api;

import com.br.controledegastos.entrypoint.api.model.TagRequest;
import com.br.controledegastos.entrypoint.api.model.TagResponse;
import com.br.controledegastos.mapper.TagMapper;
import com.br.controledegastos.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {


    private final CadastrarTagsUseCase cadastrarTagUseCase;
    private final InativarTagsUseCase inativarTagUseCase;
    private final ListarTagsAtivasUseCase listarTagsAtivasUseCase;
    private final ListarTagsUseCase listarTagsUseCase;
    private final AtivarTagsUseCase ativarTagsUseCase;

    private final TagMapper TagMapper;

    @PostMapping
    public ResponseEntity<TagResponse> criar(@RequestBody TagRequest request) {

        var TagDomain = TagMapper.toDomain(request);
        var criada = cadastrarTagUseCase.executar(TagDomain);
        var response = TagMapper.toResponse(criada);

        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{uid}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable UUID uid) {
        inativarTagUseCase.executar(uid);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<TagResponse>> listar() {

        var tags = listarTagsAtivasUseCase.executar();
        var response = tags.stream()
                .map(TagMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/todas")
    public ResponseEntity<List<TagResponse>> listarTodas() {

        var tags = listarTagsUseCase.executar();
        var response = tags.stream()
                .map(TagMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{uid}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable UUID uid) {
        ativarTagsUseCase.executar(uid);
        return ResponseEntity.noContent().build();
    }
}
