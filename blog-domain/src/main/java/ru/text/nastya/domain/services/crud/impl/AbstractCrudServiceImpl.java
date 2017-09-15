package ru.text.nastya.domain.services.crud.impl;

import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// TODO: 24.07.2017 тестировать
public abstract class AbstractCrudServiceImpl<E extends Identity> {

    protected abstract PersistedEntityRepository<E> getRepository();

    @Transactional
    public E save(E target) {
        return getRepository().save(target);
    }

    @Transactional(readOnly = true)
    public boolean exists() {
        return getRepository().exists();
    }

    @Transactional
    public void delete(Long id) {
        getRepository().delete(id);
    }

    @Transactional
    public void deleteAll() {
        getRepository().deleteAll();
    }

    @Transactional(readOnly = true)
    public Optional<E> findOne(Long id) {
        return getRepository().findOne(id);
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }
}
