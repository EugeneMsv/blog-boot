package ru.text.nastya.web.controllers.base;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.domain.services.FilteredEntityService;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.Filter;
import ru.text.nastya.profiling.Profiling;
import ru.text.nastya.web.controllers.utils.ResponseUtils;

@Profiling(showArgs = true, timeRecord = true, showOutput = true)
public abstract class AbstractFilterController<E extends Identity, D extends IdentityDto, F extends Filter> {

    protected ResponseUtils responseUtils = ResponseUtils.getInstance();

    protected abstract FilteredEntityService<E, F> getFilteredEntityService();

    protected abstract EntityMapper<E, D> getEntityMapper();

    private D toDto(E entity) {
        return getEntityMapper().mapToDto(entity);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<D>> filter(Pageable pageable, F filter) {
        Page<E> entityPage = this.getFilteredEntityService().findAll(filter, pageable);
        Page<D> dtos = entityPage.map(this::toDto);
        return responseUtils.ok(dtos);
    }

    @RequestMapping(value = "/exists", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> filter(F filter) {
        boolean exists = this.getFilteredEntityService().exists(filter);
        return exists ? responseUtils.ok() : responseUtils.notFound();
    }
}
