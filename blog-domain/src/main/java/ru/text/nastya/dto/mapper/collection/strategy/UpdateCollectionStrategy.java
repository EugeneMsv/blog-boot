package ru.text.nastya.dto.mapper.collection.strategy;


import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.specifications.common.Named;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Base interface for update collection by dtos
 */
public interface UpdateCollectionStrategy extends Named {

    /**
     * Update entities collection by dtos
     *
     * @param dtos              dtos for update
     * @param entities          existing collection
     * @param entityMapper      mapper between dto and entity
     * @param isEqualsPredicate predicate for check equality between entity and dto
     * @param isNewPredicate    predicate for check that dto is new
     * @return updated collection
     */
    <E extends Identity, D extends IdentityDto> List<E> updateCollectionWithDto(List<D> dtos,
                                                                                List<E> entities,
                                                                                EntityMapper<E, D> entityMapper,
                                                                                Predicate<D> isNewPredicate,
                                                                                BiPredicate<E, D> isEqualsPredicate);
}
