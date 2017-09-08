package ru.text.nastya.specifications;

import com.querydsl.core.types.Predicate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.text.nastya.domain.entities.QTag;
import ru.text.nastya.TagFilter;
import ru.text.nastya.specifications.criteria.BooleanExpressionBuilder;

/**
 * Спецификации поиска сущности {@link ru.text.nastya.domain.entities.Tag}
 */
@Component
public final class TagFilterConverter implements Converter<TagFilter, Predicate> {

    private static final QTag TAG = QTag.tag;

    @Override
    public Predicate convert(TagFilter filter) {
        return buildPredicate(filter);
    }

    public static Predicate buildPredicate(TagFilter filter) {
        return BooleanExpressionBuilder.create(TAG.isNotNull())
                .and().value(filter.getCode()).checkIfNot(String::isEmpty).that(TAG.code::containsIgnoreCase)
                .build();
    }
}
