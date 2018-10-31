package ru.text.nastya.domain.services.crud.impl;

import org.apache.commons.lang3.Validate;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.util.ValidationMessages;

import javax.validation.constraints.NotNull;
import java.util.Optional;

// TODO: 24.07.2017 тестировать
@Validated
public abstract class AbstractCrudServiceImpl<E extends Identity> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected abstract PersistedEntityRepository<E> getRepository();

    @Transactional
    public E save(@NotNull E target) {
        Validate.notNull(target, ValidationMessages.NOT_NULL, "saving " + target.getClass());
        logger.info("Save entity with uuid={}", target.getUuid());
        if (logger.isTraceEnabled()) {
            logger.trace("Full entity for save {}", target);
        }
        E saved = getRepository().save(preSaveAction(target));
        logger.info("Saved entity with uuid={}", saved.getUuid());
        return saved;
    }

    protected E preSaveAction(E target) {
        return target;
    }

    @Transactional(readOnly = true)
    public boolean exists() {
        return getRepository().exists();
    }

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

    @Transactional(readOnly = true)
    public Optional<E> findOne(@NotEmpty String uuid) {
        Validate.notNull(uuid, ValidationMessages.NOT_NULL, "id");
        logger.info("Find entity with uuid={}", uuid);
        Optional<E> found = getRepository().findOne(uuid);
        if (logger.isTraceEnabled()) {
            logger.trace("Full entity find {}", found);
        }
        return found;
    }

    @Transactional(readOnly = true)
    public Page<E> findAll(Pageable pageable) {
        Validate.notNull(pageable, ValidationMessages.NOT_NULL, "page");
        return getRepository().findAll(pageable);
    }
}
