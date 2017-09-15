package ru.text.nastya.dto.mapper.collection.strategy;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@Component
public class KeepOldItemsUpdateCollectionStrategy implements UpdateCollectionStrategy {

    public static final String NAME = "KEEP_OLD_ITEMS";

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
        if (CollectionUtils.isEmpty(dtos)) {
            return entities;
        }
        List<E> addedElements = new ArrayList<>();
        for (D dto : dtos) {
            if (!isNewPredicate.test(dto)) {
                OptionalInt optionalIndex = IntStream.range(0, entities.size())
                        .filter(i -> isEqualsPredicate.test(entities.get(i), dto))
                        .findFirst();

                if (optionalIndex.isPresent()) {
                    int index = optionalIndex.getAsInt();
                    E searchResult = entities.get(index);
                    entities.set(index, entityMapper.updateEntityWithDto(dto, searchResult));
                    continue;
                }
            }
            addedElements.add(entityMapper.mapToEntity(dto));
        }
        entities.addAll(addedElements);
        return entities;
    }
}
