package ru.text.nastya.dto.mapper;

import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.dto.base.IdentityDto;

/**
 * Base interface for all mappers between Dto and domain entities
 *
 * @param <E> entity type
 * @param <D> dto type
 */
public interface EntityMapper<E extends Identity, D extends IdentityDto> {

    E mapToEntity(D dto);

    E updateEntityWithDto(D dto, E entity);

    D mapToDto(E entity);
}
