package ru.text.nastya.dto.mapper.collection;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.collection.strategy.UpdateCollectionStrategy;
import ru.text.nastya.dto.mapper.collection.strategy.UpdateCollectionStrategyFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Base class for custom handle collections in the mapping
 *
 * @param <E> domain entity type
 * @param <D> dto type
 */
public abstract class AbstractCollectionMapper<E extends Identity, D extends IdentityDto> {

    @Autowired
    private UpdateCollectionStrategyFactory updateStrategiesFactory;

    /**
     * Get mapper between dto and entity
     *
     * @return mapper object
     */
    protected abstract EntityMapper<E, D> getEntityMapper();

    /**
     * Check dto is new or not
     *
     * @param dto object for check
     * @return true if new else false
     */
    protected abstract boolean isNewDto(D dto);

    /**
     * Check equality between entity and dto
     *
     * @param entity entity object
     * @param dto    dto object
     * @return true if equal else false
     */
    protected abstract boolean isEquals(E entity, D dto);

    /**
     * Map dtos wrapper to entities list
     *
     * @param dtosWrapper object with dtos for mapping
     * @return entities list or empty {@link ArrayList}
     */
    public List<E> mapDtosToEntities(ListWrapper<D> dtosWrapper) {
        List<E> entities = null;
        if (dtosWrapper != null) {
            entities = dtosWrapper.getValues().stream()
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
    public ListWrapper<D> mapEntitiesToDtos(List<E> entities) {
        ListWrapper<D> dtosWrapper = new ListWrapper<>();
        if (entities != null) {
            dtosWrapper.setValues(new ArrayList<>(entities.size()));
            List<D> dtos = entities.stream()
                    .map(e -> getEntityMapper().mapToDto(e))
                    .collect(Collectors.toList());
            dtosWrapper.getValues().addAll(dtos);
        }
        return dtosWrapper;
    }

    /**
     * Update entities with dtos values with strategy defined in dtos wrapper
     *
     * @param dtos     values wrapper with strategy name
     * @param entities entities for update
     * @return updated entities
     */
    public List<E> updateCollectionWithDto(ListWrapper<D> dtos, List<E> entities) {
        if (dtos == null) {
            return entities;
        }
        Validate.notEmpty(dtos.getUpdateStrategyType(), "Update strategy not defined");
        UpdateCollectionStrategy strategy = updateStrategiesFactory.getStrategy(dtos.getUpdateStrategyType());
        return strategy.updateCollectionWithDto(dtos.getValues(),
                entities,
                getEntityMapper(),
                this::isNewDto,
                this::isEquals);
    }

}
