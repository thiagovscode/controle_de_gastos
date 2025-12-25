package com.br.controledegastos.gateway.impl.repository;

import com.br.controledegastos.gateway.impl.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByUid(UUID uid);

    boolean existsByNomeIgnoreCase(String nome);

    List<CategoriaEntity> findByAtivaTrue();

}
