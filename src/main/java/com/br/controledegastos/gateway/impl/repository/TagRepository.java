package com.br.controledegastos.gateway.impl.repository;

import com.br.controledegastos.gateway.impl.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    Optional<TagEntity> findByUid(UUID uid);

    boolean existsByNomeIgnoreCase(String nome);

    List<TagEntity> findByAtivaTrue();

}
