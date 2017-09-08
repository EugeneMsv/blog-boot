package ru.text.nastya.specifications;


import com.querydsl.core.types.Predicate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.text.nastya.domain.entities.QPost;
import ru.text.nastya.PostFilter;
import ru.text.nastya.specifications.criteria.BooleanExpressionBuilder;

import java.util.Objects;

/**
 * Спецификации поиска сущности {@link ru.text.nastya.domain.entities.Post}
 */
@Component
public final class PostFilterConverter implements Converter<PostFilter, Predicate> {

    private static final QPost POST = QPost.post;


    @Override
    public Predicate convert(PostFilter filter) {
        return buildPredicate(filter);
    }

    public static Predicate buildPredicate(PostFilter filter) {
        return BooleanExpressionBuilder.create(POST.isNotNull())
                .and().value(filter.getAuthor()).checkIfNot(StringUtils::isEmpty).that(POST.author::containsIgnoreCase)
                .and().value(filter.getTitle()).checkIfNot(StringUtils::isEmpty).that(POST.author::containsIgnoreCase)
                .and().value(filter.getFrom()).checkIf(Objects::nonNull).that(POST.postRegister.createdTime::after)
                .and().value(filter.getTo()).checkIf(Objects::nonNull).that(POST.postRegister.createdTime::before)
                .and().value(filter.getTags()).checkIfNot(CollectionUtils::isEmpty).that(POST.tags.any().code::in)
                .build();
    }
}
