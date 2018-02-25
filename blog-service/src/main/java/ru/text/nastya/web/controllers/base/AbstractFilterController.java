package ru.text.nastya.web.controllers.base;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.domain.services.filter.FilteredEntityService;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.filters.Filter;
import ru.text.nastya.profiling.Profiling;

@Profiling(showArgs = true, timeRecord = true, showOutput = true)
public abstract class AbstractFilterController<E extends Identity, D extends IdentityDto, F extends Filter> {

    protected abstract FilteredEntityService<E, F> getFilteredEntityService();

    protected abstract EntityMapper<E, D> getEntityMapper();

    private D toDto(E entity) {
        return getEntityMapper().mapToDto(entity);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<D> filter(Pageable pageable, F filter) {
        Page<E> entityPage = this.getFilteredEntityService().findAll(filter, pageable);
        return entityPage.map(this::toDto);
    }

    @RequestMapping(value = "/exists", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public boolean filter(F filter) {
        return this.getFilteredEntityService().exists(filter);
    }
}
