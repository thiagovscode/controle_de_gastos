package com.br.controledegastos.gateway.impl;

import com.br.controledegastos.domain.Categoria;
import com.br.controledegastos.gateway.CategoriaGateway;
import com.br.controledegastos.gateway.impl.entity.CategoriaEntity;
import com.br.controledegastos.gateway.impl.repository.CategoriaRepository;
import com.br.controledegastos.mapper.CategoriaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaGatewayImpl implements CategoriaGateway {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    @Override
    public Categoria salvar(Categoria categoria) {

        CategoriaEntity entity = repository.findByUid(categoria.getUid())
                .map(existing -> {
                    // UPDATE
                    existing.setNome(categoria.getNome());
                    existing.setTipo(categoria.getTipo());
                    existing.setAtiva(categoria.isAtiva());
                    return existing;
                })
                .orElseGet(() -> mapper.toEntity(categoria)); // INSERT

        CategoriaEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Categoria> buscarPorUid(UUID uid) {
        return repository.findByUid(uid)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existePorNome(String nome) {
        return repository.existsByNomeIgnoreCase(nome);
    }

    // 🔑 IMPLEMENTAÇÃO NOVA
    @Override
    public List<Categoria> listarAtivas() {
        return repository.findByAtivaTrue()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    // 🔑 IMPLEMENTAÇÃO NOVA
    @Override
    public List<Categoria> listarTodas() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
