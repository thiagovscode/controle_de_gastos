package com.br.controledegastos.mapper;

import com.br.controledegastos.domain.Categoria;
import com.br.controledegastos.entrypoint.api.model.CategoriaRequest;
import com.br.controledegastos.entrypoint.api.model.CategoriaResponse;
import com.br.controledegastos.gateway.impl.entity.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;


@Mapper(componentModel = "spring")

public interface CategoriaMapper {

    Categoria toDomain(CategoriaRequest request);

    Categoria toDomain(CategoriaEntity entity);

    CategoriaEntity toEntity(Categoria categoria);

    CategoriaResponse toResponse(Categoria categoria);

    // API → Domain
    @ObjectFactory
    default Categoria criarCategoria(CategoriaRequest request) {
        return new Categoria(request.getNome(), request.getTipo());
    }

    // Entity → Domain
    @ObjectFactory
    default Categoria criarCategoria(CategoriaEntity entity) {
        return new Categoria(
                entity.getUid(),
                entity.getNome(),
                entity.getTipo(),
                entity.isAtiva()
        );
    }

    // Domain → Entity
    @ObjectFactory
    default CategoriaEntity criarEntity(Categoria categoria) {
        return new CategoriaEntity(
                null,
                categoria.getUid(),
                categoria.getNome(),
                categoria.getTipo(),
                categoria.isAtiva()
        );
    }

    // 🔑 DOMAIN → RESPONSE (FALTAVA ISSO)
    @ObjectFactory
    default CategoriaResponse criarResponse(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getUid(),
                categoria.getNome(),
                categoria.getTipo(),
                categoria.isAtiva()
        );
    }
}
