package ru.text.nastya.utils;


import org.apache.commons.lang3.builder.EqualsBuilder;
import ru.text.nastya.domain.entities.*;
import ru.text.nastya.domain.entities.base.Identity;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static org.junit.Assert.*;


public class AssertionUtils {

    private AssertionUtils() {
        throw new UnsupportedOperationException();
    }

    private static void throwAssertionError(boolean equals) {
        if (!equals) {
            throw new AssertionError();
        }
    }

    public static void assertPersist(Identity target) {
        assertFalse(target.isNew());
    }

    public static <E> void assertReflectionEquals(E expected, E actual, String... excludeFields) {
        boolean equals = EqualsBuilder.reflectionEquals(expected, actual, excludeFields);
        throwAssertionError(equals);
    }

    public static <T extends Collection> void assertNotEmpty(T collection) {
        assertNotNull(collection);
        assertFalse(collection.isEmpty());
    }

    public static <T, R> void assertListEqualsInAnyOrder(List<T> expected, List<R> actual,
                                                         BiFunction<T, R, Boolean> equalsFunc) {
        Map<Integer, Integer> eqIndexes = new HashMap<>();
        if (expected != null && actual != null) {
            assertEquals(expected.size(), actual.size());

            for (int i = 0; i < actual.size(); i++) {
                R actualEl = actual.get(i);
                boolean hasEqualEl = false;
                for (int j = 0; j < expected.size(); j++) {
                    T expectedEl = expected.get(j);
                    if (equalsFunc.apply(expectedEl, actualEl) && eqIndexes.get(j) == null) {
                        eqIndexes.put(j, i);
                        hasEqualEl = true;
                        break;
                    }
                }
                assert hasEqualEl : "There is no equal element in expected list for element with index = " + i;
            }
        } else {
            assertTrue(expected == null && actual == null);
        }
    }

    public static void assertFieldsEquals(Tag expected, Tag actual) {
        boolean equals = isFieldsEquals(expected, actual);
        throwAssertionError(equals);
    }

    public static boolean isFieldsEquals(Tag expected, Tag actual) {
        return new EqualsBuilder()
                    .append(expected.getCode(), actual.getCode())
                .append(expected.getName(), actual.getName())
                    .isEquals();
    }

    public static void assertFieldsEquals(Attachment expected, Attachment actual) {
        boolean equals = new EqualsBuilder()
                .append(expected.getDescription(), actual.getDescription())
                .append(expected.getFile(), actual.getFile()) //todo проверить сравнение массива
                .isEquals();
        throwAssertionError(equals);
    }

    public static void assertFieldsEquals(Commentary expected, Commentary actual) {
        boolean equals = new EqualsBuilder()
                .append(expected.getAuthor(), actual.getAuthor())
                .append(expected.getCreatedTime(), actual.getCreatedTime())
                .append(expected.getEmail(), actual.getEmail())
                .append(expected.getMessage(), actual.getMessage())
                .isEquals();
        throwAssertionError(equals);
        assertFieldsEquals(expected.getPostRegister(), actual.getPostRegister());
    }

    public static void assertFieldsEquals(Post expected, Post actual) {
        boolean equals = new EqualsBuilder()
                .append(expected.getAuthor(), actual.getAuthor())
                .append(expected.getCode(), actual.getCode())
                .append(expected.getState(), actual.getState())
                .append(expected.getText(), actual.getText())
                .append(expected.getTitle(), actual.getTitle())
//                .append(expected.getTags(), actual.getTags())
                .isEquals();
        throwAssertionError(equals);
    }

    public static void assertFieldsEquals(PostRegister expected, PostRegister actual) {
        boolean equals = new EqualsBuilder()
                .append(expected.getPreview(), actual.getPreview())
                .append(expected.getCommentsNum(), actual.getCommentsNum())
                .append(expected.getCreatedTime(), actual.getCreatedTime())
                .append(expected.getLikes(), actual.getLikes())
                .append(expected.getMetaInfo(), actual.getMetaInfo())
                .append(expected.getViews(), actual.getViews())
                .isEquals();
        throwAssertionError(equals);
    }
}
