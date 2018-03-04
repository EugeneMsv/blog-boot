package ru.text.nastya.dto.mapper.collection;

import org.springframework.beans.factory.annotation.Autowired;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.collection.strategy.RemoveOldItemsUpdateCollectionStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base class for custom handle collections in the mapping
 *
 * @param <E> domain entity type
 * @param <D> dto type
 */
public abstract class ListMapper<E extends Identity, D extends IdentityDto> {

    @Autowired
    private RemoveOldItemsUpdateCollectionStrategy removeOldItemsUpdateCollectionStrategy;

    /**
     * Get mapper between dto and entity
     *
     * @return mapper object
     */
    protected abstract EntityMapper<E, D> getEntityMapper();

    /**
     * Map dtos wrapper to entities list
     *
     * @param dtos object with dtos for mapping
     * @return entities list or empty {@link ArrayList}
     */
    public List<E> mapDtosToEntities(List<D> dtos) {
        List<E> entities = null;
        if (dtos != null) {
            entities = dtos.stream()
                    .map(d -> getEntityMapper().mapToEntity(d))
                    .collect(Collectors.toList());
        }
        return entities;
    }

    /**
     * Map entities list to dtos wrapper
     *
     * @param entities list of entities
     * @return wrapper with mapped dtos, or with empty dto list if entities null
     */
    public List<D> mapEntitiesToDtos(List<E> entities) {
        List<D> dtos = null;
        if (entities != null) {
            dtos = entities.stream()
                    .map(e -> getEntityMapper().mapToDto(e))
                    .collect(Collectors.toList());
        }
        return dtos;
    }

    /**
     * Update entities with dtos values with strategy defined in dtos wrapper
     *
     * @param dtos     values wrapper with strategy name
     * @param entities entities for update
     * @return updated entities
     */
    public List<E> updateListWithDto(List<D> dtos, List<E> entities) {
        if (dtos == null) {
            return entities;
        }
        return removeOldItemsUpdateCollectionStrategy.updateCollectionWithDto(dtos, entities, getEntityMapper());
    }

}
