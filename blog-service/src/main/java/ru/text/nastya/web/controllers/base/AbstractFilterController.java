package ru.text.nastya.web.controllers.base;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.domain.services.filter.FilteredEntityService;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.filters.Filter;

import static ru.text.nastya.web.controllers.base.ControllerConstants.FILTER_POSTFIX;

public abstract class AbstractFilterController<E extends Identity, D extends IdentityDto, F extends Filter> {

    protected abstract FilteredEntityService<E, F> getFilteredEntityService();

    protected abstract EntityMapper<E, D> getEntityMapper();

    protected abstract String getModelParameter();

    private D toDto(E entity) {
        return getEntityMapper().mapToDto(entity);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String filter(@RequestBody F filter, Model model, Pageable pageable) {
        Page<E> entityPage = this.getFilteredEntityService().findAll(filter, pageable);
        model.addAttribute(getModelParameter() + FILTER_POSTFIX, entityPage.map(this::toDto));
        return getModelParameter() + FILTER_POSTFIX;
    }

    @GetMapping(value = "/exists")
    @ResponseStatus(HttpStatus.OK)
    public boolean filter(@RequestBody F filter) {
        return this.getFilteredEntityService().exists(filter);
    }
}
