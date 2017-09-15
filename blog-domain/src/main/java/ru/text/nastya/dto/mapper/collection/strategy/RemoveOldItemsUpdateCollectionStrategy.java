package ru.text.nastya.dto.mapper.collection.strategy;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class RemoveOldItemsUpdateCollectionStrategy implements UpdateCollectionStrategy {

    public static final String NAME = "REMOVE_OLD_ITEMS";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public <E extends Identity, D extends IdentityDto> List<E> updateCollectionWithDto(List<D> dtos,
                                                                                       List<E> entities,
                                                                                       EntityMapper<E, D> entityMapper,
                                                                                       Predicate<D> isNewPredicate,
                                                                                       BiPredicate<E, D> isEqualsPredicate) {
        entities.clear();
        if (CollectionUtils.isEmpty(dtos)) {
            return entities;
        }
        List<E> newEntities = dtos.stream()
                .map(entityMapper::mapToEntity)
                .collect(Collectors.toList());

        entities.addAll(newEntities);
        return entities;
    }
}
