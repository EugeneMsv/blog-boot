package ru.text.nastya.dto.mapper.collection;

import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.dto.base.IdentityDto;

/**
 * Base class for custom handle collections in the mapping
 *
 * @param <E> domain entity type with long id
 * @param <D> dto type with long id
 */
public abstract class AbstractCollectionIdentityMapper<E extends Identity, D extends IdentityDto>
        extends AbstractCollectionMapper<E, D> {

    @Override
    protected boolean isNewDto(D dto) {
        return dto.getUuid() == null;
    }

    @Override
    protected boolean isEquals(E entity, D dto) {
        return dto.getUuid().equals(entity.getUuid());
    }

}
