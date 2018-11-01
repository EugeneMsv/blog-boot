package ru.text.nastya.domain.services.crud.impl;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.services.action.PostSaveInterceptor;
import ru.text.nastya.domain.services.action.PreSaveInterceptor;
import ru.text.nastya.validation.constraint.PageSize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Validated
public abstract class AbstractCrudServiceImpl<E extends Identity> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract PersistedEntityRepository<E> getRepository();

    private List<PreSaveInterceptor<E>> preSaveInterceptors = Collections.emptyList();

    private List<PostSaveInterceptor<E>> postSaveInterceptors = Collections.emptyList();

    @Autowired(required = false)
    public void setPreSaveInterceptors(List<PreSaveInterceptor<E>> preSaveInterceptors) {
        this.preSaveInterceptors = preSaveInterceptors;
    }

    @Autowired(required = false)
    public void setPostSaveInterceptors(List<PostSaveInterceptor<E>> postSaveInterceptors) {
        this.postSaveInterceptors = postSaveInterceptors;
    }

    @Validated
    public E save(@Valid @NotNull E target) {
        logger.info("Save entity with uuid={}", target.getUuid());
        logger.trace("Full entity for save {}", target);
        preSaveAction(target);
        E saved = getRepository().save(target);
        postSaveAction(saved);
        logger.info("Saved entity with uuid={}", saved.getUuid());
        return saved;
    }

    protected void preSaveAction(E target) {
        preSaveInterceptors.forEach(interceptors -> interceptors.execute(target));
    }

    protected void postSaveAction(E target) {
        postSaveInterceptors.forEach(interceptors -> interceptors.execute(target));
    }

    @Transactional(readOnly = true)
    public boolean exists() {
        return getRepository().exists();
    }

    @Validated
    @Transactional
    public void delete(@NotEmpty String uuid) {
        logger.info("Save entity with uuid={}", uuid);
        getRepository().delete(uuid);
        logger.info("Deleted entity with uuid={}", uuid);
    }

    @Transactional
    public void deleteAll() {
        logger.info("Delete all entities");
        getRepository().deleteAll();
    }

    @Validated
    @Transactional(readOnly = true)
    public Optional<E> findOne(@NotEmpty String uuid) {
        logger.info("Find entity with uuid={}", uuid);
        Optional<E> found = getRepository().findOne(uuid);
        logger.trace("Full entity find {}", found);
        return found;
    }

    @Validated
    @Transactional(readOnly = true)
    public Page<E> findAll(@Valid @NotNull @PageSize Pageable pageable) {
        return getRepository().findAll(pageable);
    }
}
