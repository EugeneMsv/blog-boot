package ru.text.nastya.utils;

import org.junit.Test;

import java.util.Arrays;

import static ru.text.nastya.utils.AssertionUtils.assertListEqualsInAnyOrder;

public class AssertListEqualsInAnyOrderTest {


    @Test
    public void allEqualsWithSymmetric() {
        assertListEqualsInAnyOrder(Arrays.asList("a"), Arrays.asList("a"), String::equals);

        assertListEqualsInAnyOrder(Arrays.asList("A"), Arrays.asList("a"), String::equalsIgnoreCase);
        assertListEqualsInAnyOrder(Arrays.asList("a"), Arrays.asList("A"), String::equalsIgnoreCase);

        assertListEqualsInAnyOrder(Arrays.asList("a", "b"), Arrays.asList("b", "a"), String::equals);
        assertListEqualsInAnyOrder(Arrays.asList("b", "a"), Arrays.asList("a", "b"), String::equals);

        assertListEqualsInAnyOrder(Arrays.asList("a", "b", "A"), Arrays.asList("b", "a", "A"), String::equals);
        assertListEqualsInAnyOrder(Arrays.asList("b", "a", "A"), Arrays.asList("a", "b", "A"), String::equals);

        assertListEqualsInAnyOrder(Arrays.asList("a", "b", "a"), Arrays.asList("b", "a", "a"), String::equals);
        assertListEqualsInAnyOrder(Arrays.asList("b", "a", "a"), Arrays.asList("a", "b", "a"), String::equals);

        assertListEqualsInAnyOrder(Arrays.asList("a", "b", "A"), Arrays.asList("b", "a", "A"),
                String::equalsIgnoreCase);
        assertListEqualsInAnyOrder(Arrays.asList("b", "a", "A"), Arrays.asList("a", "b", "A"),
                String::equalsIgnoreCase);

        assertListEqualsInAnyOrder(Arrays.asList("a", "b", "A"), Arrays.asList("b", "a", "a"),
                String::equalsIgnoreCase);
        assertListEqualsInAnyOrder(Arrays.asList("b", "a", "a"), Arrays.asList("a", "b", "A"),
                String::equalsIgnoreCase);

    }

    @Test(expected = AssertionError.class)
    public void failDiffElements() {
        assertListEqualsInAnyOrder(Arrays.asList("a"), Arrays.asList("b"), String::equals);
    }

    @Test(expected = AssertionError.class)
    public void failWrongEqualsFunc() {
        assertListEqualsInAnyOrder(Arrays.asList("a"), Arrays.asList("A"), String::equals);
    }

    @Test(expected = AssertionError.class)
    public void failDiffSize() {
        assertListEqualsInAnyOrder(Arrays.asList("a", "a"), Arrays.asList("a"), String::equals);
    }

    @Test(expected = AssertionError.class)
    public void failNoSuchRepeatedElement() {
        assertListEqualsInAnyOrder(Arrays.asList("a", "a", "b"), Arrays.asList("a", "b", "b"), String::equals);
    }

    @Test(expected = AssertionError.class)
    public void failNoSuchElement() {
        assertListEqualsInAnyOrder(Arrays.asList("a", "a", "a"), Arrays.asList("a", "a", "b"), String::equals);
    }
}
