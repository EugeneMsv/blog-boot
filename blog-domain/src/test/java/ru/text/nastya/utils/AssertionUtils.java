package ru.text.nastya.utils;


import org.apache.commons.lang3.builder.EqualsBuilder;
import ru.text.nastya.domain.entities.*;
import ru.text.nastya.domain.entities.base.Identity;

import static org.junit.Assert.assertFalse;


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


    public static <E extends Identity> void assertReflectionEquals(E expected, E actual, String... excludeFields) {
        boolean equals = EqualsBuilder.reflectionEquals(expected, actual, excludeFields);
        throwAssertionError(equals);
    }


    public static void assertFieldsEquals(Tag expected, Tag actual) {
        boolean equals = new EqualsBuilder()
                .append(expected.getCode(), actual.getCode())
                .append(expected.getDescription(), actual.getDescription())
                .isEquals();
        throwAssertionError(equals);
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
        assertFieldsEquals(expected.getPost(), actual.getPost());
    }
}
