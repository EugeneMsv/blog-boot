package ru.text.nastya.utils;


import ru.text.nastya.domain.entities.Commentary;
import ru.text.nastya.domain.entities.Tag;

import java.util.Random;
import java.util.UUID;

public class DomainEntityBuilder {

    private DomainEntityBuilder() {
        throw new UnsupportedOperationException();
    }

    private static final Random rand = new Random(System.nanoTime());

    public static String buildRandomString() {
        return UUID.randomUUID().toString();
    }

    public static String buildRandomString(int maxLength) {
        String randString = UUID.randomUUID().toString();
        if (randString.length() > maxLength) {
            return randString.substring(0, maxLength);
        }
        return randString;
    }

    public static Tag buildRandomTag() {
        return new Tag(buildRandomString(20), buildRandomString());
    }

    public static Commentary buildRandomCommentary() {
        Commentary commentary = new Commentary(buildRandomString(10));
        commentary.setMessage(buildRandomString());
        commentary.setEmail(buildRandomString());
        return commentary;
    }

    public static PostRegisterBuilder getPostRegisterBuilder() {
        return new PostRegisterBuilder();
    }
}
