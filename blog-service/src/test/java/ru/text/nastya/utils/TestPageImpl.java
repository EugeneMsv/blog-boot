package ru.text.nastya.utils;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class TestPageImpl<T> extends PageImpl<T> {

    public TestPageImpl() {
        super(new ArrayList());
    }

    public TestPageImpl(List content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public TestPageImpl(List content) {
        super(content);
    }
}
