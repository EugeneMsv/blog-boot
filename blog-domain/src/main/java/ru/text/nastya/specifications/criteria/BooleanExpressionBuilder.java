package ru.text.nastya.specifications.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Строитель логического выражения для поиска доменных сущностей
 */
public final class BooleanExpressionBuilder {

    private BooleanExpression predicate = Expressions.TRUE;

    private BooleanExpressionBuilder() {
    }

    /**
     * Создать билдер из логического предиката
     *
     * @param predicate предикат
     * @return выражение
     */
    public static Expression create(BooleanExpression predicate) {
        BooleanExpressionBuilder builder = new BooleanExpressionBuilder();
        return builder.new ExpressionImpl(predicate);
    }

    public static BooleanExpressionBuilder create() {
        return new BooleanExpressionBuilder();
    }

    /**
     * Построить логическое выражение(предикат) из билдера
     *
     * @return построенный логический предикат
     */
    public BooleanExpression build() {
        return predicate;
    }

    /**
     * Добавить параметр со значением в билдер
     *
     * @param value значение параметра
     * @param <V>   тип значения параметра
     * @return параметр со значением
     */
    public <V> Parameter<V> value(V value) {
        return new ParameterImpl<>(Optional.ofNullable(value));
    }

    private class ParameterImpl<T> implements Parameter<T> {
        private final Optional<T> optional;

        ParameterImpl(Optional<T> optional) {
            this.optional = optional;
        }

        /**
         * Проверка на прохождение с предикатом
         *
         * @param predicate предикат
         * @return параметр
         */
        public Parameter<T> checkIf(Predicate<T> predicate) {
            optional.filter(predicate);
            return this;
        }

        /**
         * Проверка на НЕ прохождение с предикатом
         *
         * @param predicate предикат
         * @return параметр
         */
        public Parameter<T> checkIfNot(Predicate<T> predicate) {
            optional.filter(predicate.negate());
            return this;
        }

        /**
         * Применение функции поиска к параметру
         *
         * @param expressionFunc фугкция поиска
         * @return выражение
         */
        public Expression that(Function<T, BooleanExpression> expressionFunc) {
            return optional.map(t -> BooleanExpressionBuilder.this.new ExpressionImpl(expressionFunc.apply(t)))
                    .orElseGet(() -> BooleanExpressionBuilder.this.new ExpressionImpl(Expressions.TRUE));
        }
    }

    public class ExpressionImpl implements Expression {

        private final BooleanExpression booleanExpression;

        ExpressionImpl(BooleanExpression booleanExpression) {
            this.booleanExpression = booleanExpression;
        }

        public BooleanExpressionBuilder and() {
            BooleanExpressionBuilder.this.predicate.and(booleanExpression);
            return BooleanExpressionBuilder.this;
        }

        public BooleanExpressionBuilder or() {
            BooleanExpressionBuilder.this.predicate.or(booleanExpression);
            return BooleanExpressionBuilder.this;
        }

        public BooleanExpression build() {
            BooleanExpressionBuilder.this.predicate = booleanExpression;
            return BooleanExpressionBuilder.this.build();
        }
    }

}
