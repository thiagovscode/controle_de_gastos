package com.br.controledegastos.mapper;

import com.br.controledegastos.domain.Tag;
import com.br.controledegastos.entrypoint.api.model.TagRequest;
import com.br.controledegastos.entrypoint.api.model.TagResponse;
import com.br.controledegastos.gateway.impl.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;


@Mapper(componentModel = "spring")

public interface TagMapper {

    Tag toDomain(TagRequest request);

    Tag toDomain(TagEntity entity);

    TagEntity toEntity(Tag Tag);

    TagResponse toResponse(Tag Tag);

    // API → Domain
    @ObjectFactory
    default Tag criarTag(TagRequest request) {
        return new Tag(request.getNome());
    }

    // Entity → Domain
    @ObjectFactory
    default Tag criarTag(TagEntity entity) {
        return new Tag(
                entity.getUid(),
                entity.getNome(),
                entity.isAtiva()
        );
    }

    // Domain → Entity
    @ObjectFactory
    default TagEntity criarEntity(Tag Tag) {
        return new TagEntity(
                null,
                Tag.getUid(),
                Tag.getNome(),
                Tag.isAtiva()
        );
    }

    // 🔑 DOMAIN → RESPONSE (FALTAVA ISSO)
    @ObjectFactory
    default TagResponse criarResponse(Tag Tag) {
        return new TagResponse(
                Tag.getUid(),
                Tag.getNome(),
                Tag.isAtiva()
        );
    }
}
