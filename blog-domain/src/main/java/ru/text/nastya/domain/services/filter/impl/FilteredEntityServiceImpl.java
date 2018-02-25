package ru.text.nastya.domain.services.filter.impl;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.filters.Filter;

import java.util.Optional;

public abstract class FilteredEntityServiceImpl<E extends Identity, F extends Filter> {

    protected abstract PersistedEntityRepository<E> getRepository();

    @Autowired
    protected ConversionService conversionService;

    protected Predicate convert(F filter) {
        return conversionService.convert(filter, Predicate.class);
    }

    @Transactional(readOnly = true)
    public boolean exists(F filter) {
        return getRepository().exists(convert(filter));
    }

    @Transactional(readOnly = true)
    public Optional<E> findOne(F filter) {
        return Optional.ofNullable(getRepository().findOne(convert(filter)));
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(F filter, Pageable pageable) {
        return getRepository().findAll(convert(filter), pageable);
    }

}
