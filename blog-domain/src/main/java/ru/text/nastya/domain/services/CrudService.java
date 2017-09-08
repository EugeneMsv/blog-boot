package ru.text.nastya.domain.services;

import ru.text.nastya.domain.entities.base.Identity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Base service for all persist entities
 *
 * @param <E> entity type
 */
public interface CrudService<E extends Identity> {

    /**
     * Save entity
     *
     * @param target entity
     * @return saved or merged entity
     */
    E save(E target);

    /**
     * Check for existence in persist storage
     *
     * @return true if exist false if not
     */
    boolean exists();

    /**
     * Delete entity  by id
     *
     * @param id entity id
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    void delete(Long id);

    /**
     * Delete all entities
     */
    void deleteAll();

    /**
     * Find entity by id
     *
     * @param id entity id
     * @return optional of entity, empty if there is no such entity
     */
    Optional<E> findOne(Long id);

    /**
     * Find all entities with given page
     *
     * @param pageable page descriptor
     * @return page corresponding for pageable
     */
    Page<E> findAll(Pageable pageable);
}
