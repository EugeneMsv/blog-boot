package ru.text.nastya.domain.services.action;

import org.springframework.core.Ordered;

public interface PostSaveInterceptor<E> extends Ordered {

    void execute(E target);
}
