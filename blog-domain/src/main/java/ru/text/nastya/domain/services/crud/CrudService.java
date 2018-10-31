package ru.text.nastya.domain.services.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.text.nastya.domain.entities.base.Identity;

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
     * Delete entity  by uuid
     *
     * @param uuid entity uuid
     * @throws IllegalArgumentException in case the given entity is {@literal null}
     */
    void delete(String uuid);

    /**
     * Delete all entities
     */
    void deleteAll();

    /**
     * Find entity by uuid
     *
     * @param uuid entity uuid
     * @return optional of entity, empty if there is no such entity
     */
    Optional<E> findOne(String uuid);

    /**
     * Find all entities with given page
     *
     * @param pageable page descriptor
     * @return page corresponding for pageable
     */
    Page<E> findAll(Pageable pageable);
}
