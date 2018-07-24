package ru.text.nastya.specifications;

import com.querydsl.core.types.Predicate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.text.nastya.domain.entities.QPostRegister;
import ru.text.nastya.filters.PostRegisterFilter;
import ru.text.nastya.specifications.criteria.BooleanExpressionBuilder;

/**
 * Спецификации поиска сущности {@link ru.text.nastya.domain.entities.PostRegister}
 */
@Component
public final class PostRegisterFilterConverter implements Converter<PostRegisterFilter, Predicate> {

    private static final QPostRegister POST_REGISTER = QPostRegister.postRegister;

    public static Predicate buildPredicate(PostRegisterFilter filter) {
        return BooleanExpressionBuilder.create(POST_REGISTER.isNotNull())
                .and()
                .value(filter.getPreview()).checkIfNot(String::isEmpty).map(s -> "%" + s + "%").that(POST_REGISTER.preview::likeIgnoreCase)
                .build();
    }

    @Override
    public Predicate convert(PostRegisterFilter filter) {
        return buildPredicate(filter);
    }
}
