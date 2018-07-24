package ru.text.nastya.specifications.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Parameter<T> {

    /**
     * Проверка на прохождение с предикатом
     *
     * @param predicate предикат
     * @return параметр
     */
    Parameter<T> checkIf(Predicate<T> predicate);

    /**
     * Проверка на НЕ прохождение с предикатом
     *
     * @param predicate предикат
     * @return параметр
     */
    Parameter<T> checkIfNot(Predicate<T> predicate);

    /**
     * Доп обработка значения фильтра
     *
     * @param mapFunction функция обработки
     * @return параметр другого типа
     */
    Parameter<T> map(Function<T, T> mapFunction);

    /**
     * Применение функции поиска к параметру
     *
     * @param expressionFunc фугкция поиска
     * @return выражение
     */
    Expression that(Function<T, BooleanExpression> expressionFunc);
}
