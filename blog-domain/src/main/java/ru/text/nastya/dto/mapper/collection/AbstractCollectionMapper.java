package ru.text.nastya.dto.mapper.collection;

import org.springframework.util.CollectionUtils;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Base class for mapping {@link List} of entities from/to {@link List} of dtos
 *
 * @param <E> domain entity type
 * @param <D> dto type
 * @param <K> key type
 */
public abstract class AbstractCollectionMapper<E extends Identity, D extends IdentityDto, K> {

    /**
     * Get mapper between dto and entity
     *
     * @return mapper object
     */
    protected abstract EntityMapper<E, D> getEntityMapper();

    /**
     * Map dtos to entities
     *
     * @param dtos {@link List} of dtos for mapping
     * @return {@link List} of mapped entities or empty {@link ArrayList} when source is null
     */
    public List<E> mapToEntities(List<D> dtos) {
        return convertList(dtos, getEntityMapper()::mapToEntity);
    }

    /**
     * Map dtos to entities
     *
     * @param dtos {@link Set} of dtos for mapping
     * @return {@link Set} of mapped entities or empty {@link HashSet} when source has no data
     */
    public Set<E> mapToEntities(Set<D> dtos) {
        return convertSet(dtos, getEntityMapper()::mapToEntity);
    }

    /**
     * Map entities to dtos
     *
     * @param entities {@link List} of entities for mapping
     * @return {@link List} of mapped dtos or empty {@link ArrayList} when source is null
     */
    public List<D> mapToDtos(List<E> entities) {
        return convertList(entities, getEntityMapper()::mapToDto);
    }

    /**
     * Map entities to dtos
     *
     * @param entities {@link Set} of entities for mapping
     * @return {@link Set} of mapped dtos or empty {@link HashSet} when source has no data
     */
    public Set<D> mapToDtos(Set<E> entities) {
        return convertSet(entities, getEntityMapper()::mapToDto);
    }

    private <F, T> Set<T> convertSet(Set<F> source, Function<? super F, ? extends T> mapper) {
        if (CollectionUtils.isEmpty(source)) {
            return new HashSet<>();
        }
        return source.stream().map(mapper).collect(Collectors.toSet());
    }

    private <F, T> List<T> convertList(List<F> source, Function<? super F, ? extends T> mapper) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }
        return source.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * Extract key value for current entity
     *
     * @param entity object for key extraction
     * @return key value
     */
    protected abstract K getEntityKeyValue(E entity);

    /**
     * Extract key value for current dto
     *
     * @param dto object for key extraction
     * @return key value
     */
    protected abstract K getDtoKeyValue(D dto);

    /**
     * Update entities with dtos.
     * For entities and dtos which have equal key values extracted by
     * {@link AbstractCollectionMapper#getEntityKeyValue(Identity)} (Object)}
     * and {@link AbstractCollectionMapper#getDtoKeyValue(IdentityDto)}.
     * Note that null key values are not equals!!!
     *
     * @param dtos     dtos
     * @param entities entities for update
     * @return updated entities
     */
    public List<E> updateWithDtos(List<D> dtos, List<E> entities) {
        return updateWithDtosCollection(dtos, entities);
    }

    /**
     * Update entities with dtos.
     * For entities and dtos which have equal key values extracted by
     * {@link AbstractCollectionMapper#getEntityKeyValue(Identity)}
     * and {@link AbstractCollectionMapper#getDtoKeyValue(IdentityDto)}.
     * Note that null key values are not equal!!!
     *
     * @param dtos     dtos
     * @param entities entities for update
     * @return updated entities
     */
    public Set<E> updateWithDtos(Set<D> dtos, Set<E> entities) {
        return updateWithDtosCollection(dtos, entities);
    }

    private <R extends Collection<E>> R updateWithDtosCollection(Collection<D> dtos, R entities) {
        if (CollectionUtils.isEmpty(dtos)) {
            entities.clear();
            return entities;
        }
        if (dtos.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Невозможно обновить коллекцию объектов через ["
                    + getClass().getCanonicalName() + "] на обновление прислан пустой объект");
        }

        // Collect already existing entities
        Map<K, E> existingEntitiesMap = entities.stream()
                .filter(Objects::nonNull)
                .filter(e -> getEntityKeyValue(e) != null)
                .collect(Collectors.toMap(this::getEntityKeyValue, e -> e));

        // Fill result with updated and new mapped entities
        List<E> updatedEntities = dtos.stream()
                .map(dto ->
                        Optional.ofNullable(getDtoKeyValue(dto))
                                .map(existingEntitiesMap::get)
                                .map(forUpdate -> getEntityMapper().updateEntityWithDto(dto, forUpdate))
                                .orElseGet(() -> getEntityMapper().mapToEntity(dto))
                ).collect(Collectors.toList());
        entities.clear();
        entities.addAll(updatedEntities);

        return entities;
    }

}