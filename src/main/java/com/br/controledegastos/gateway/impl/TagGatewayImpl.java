package com.br.controledegastos.gateway.impl;

import com.br.controledegastos.domain.Tag;
import com.br.controledegastos.gateway.TagGateway;
import com.br.controledegastos.gateway.impl.entity.TagEntity;
import com.br.controledegastos.gateway.impl.repository.TagRepository;
import com.br.controledegastos.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagGatewayImpl implements TagGateway {

    private final TagRepository repository;
    private final TagMapper mapper;

    @Override
    public Tag salvar(Tag tag) {

        TagEntity entity = repository.findByUid(tag.getUid())
                .map(existing -> {
                    existing.setNome(tag.getNome());
                    existing.setAtiva(tag.isAtiva());
                    return existing;
                })
                .orElseGet(() -> mapper.toEntity(tag)); // INSERT

        TagEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Tag> buscarPorUid(UUID uid) {
        return repository.findByUid(uid)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existePorNome(String nome) {
        return repository.existsByNomeIgnoreCase(nome);
    }

    @Override
    public List<Tag> listarAtivas() {
        return repository.findByAtivaTrue()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    // 🔑 IMPLEMENTAÇÃO NOVA
    @Override
    public List<Tag> listarTodas() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
