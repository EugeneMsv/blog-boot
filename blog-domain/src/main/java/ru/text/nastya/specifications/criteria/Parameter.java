package ru.text.nastya.specifications.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Parameter<T> {

    Parameter<T> checkIf(Predicate<T> predicate);

    Parameter<T> checkIfNot(Predicate<T> predicate);

    Expression that(Function<T, BooleanExpression> expressionFunc);
}
