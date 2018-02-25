package ru.text.nastya.domain.services.filter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.filters.Filter;

import java.util.Optional;

/**
 * Service for persist entities with capability for filtering
 *
 * @param <F> entity type
 * @param <F> filter type
 */
public interface FilteredEntityService<E extends Identity, F extends Filter> {

    /**
     * Check for existence in persist storage by filter
     *
     * @param filter search condition
     * @return true if exist false if not
     */
    boolean exists(F filter);

    /**
     * Find entity by filter
     *
     * @param filter search condition
     * @return optional of entity, empty if there is no such entity
     */
    Optional<E> findOne(F filter);

    /**
     * Find all entities by filter with given page
     *
     * @param filter   search condition
     * @param pageable page descriptor
     * @return page corresponding for pageable and filter
     */
    Page<E> findAll(F filter, Pageable pageable);

}
