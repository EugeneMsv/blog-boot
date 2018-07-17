package ru.text.nastya.web.controllers.base;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.text.nastya.domain.entities.base.Identity;
import ru.text.nastya.domain.services.crud.CrudService;
import ru.text.nastya.dto.base.IdentityDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.exception.DataNotFoundException;

import java.util.Optional;

//@Profiling(showArgs = true, timeRecord = true, showOutput = true)
public abstract class AbstractCrudController<E extends Identity, D extends IdentityDto> {

    public static final String PAGE_POSTFIX = "Page";


    protected abstract CrudService<E> getCrudService();

    protected abstract EntityMapper<E, D> getEntityMapper();

    protected String getModelParameter() {
        return "FAILED";
    }

    private E toEntity(D dto) {
        return getEntityMapper().mapToEntity(dto);
    }

    private D toDto(E entity) {
        return getEntityMapper().mapToDto(entity);
    }

    private E updateEntityByDto(E entity, D dto) {
        return getEntityMapper().updateEntityWithDto(dto, entity);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public D save(@RequestBody D request) {
        E entity = Optional.ofNullable(request)
                .map(this::toEntity)
                .orElseThrow(() -> new IllegalArgumentException("Input for save is null"));
        E result = getCrudService().save(entity);
        return toDto(result);
    }


    @PutMapping(value = {"{id}"},
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public D update(@PathVariable Long id, @RequestBody D request) {
        checkDtoForUpdate(request);
        request.setId(id);
        E entity;
        if (id == null) {
            entity = getCrudService().save(toEntity(request));
        } else {
            Optional<E> founded = getCrudService().findOne(id);
            if (founded.isPresent()) {
                E originEntity = updateEntityByDto(founded.get(), request);
                entity = getCrudService().save(entityPreUpdateAction(originEntity));
            } else {
                entity = getCrudService().save(toEntity(request));
            }
        }
        return toDto(entity);
    }


    @DeleteMapping(value = {"{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        getCrudService().delete(id);
    }


    @GetMapping(value = {"{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String get(@PathVariable Long id, Model model) {
        Optional<E> founded = getCrudService().findOne(id);
        model.addAttribute(getModelParameter(), founded.map(this::toDto)
                .orElseThrow(DataNotFoundException::new));
        return getModelParameter();
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String findAll(Pageable pageable, Model model) {
        Page<E> entityPage = this.getCrudService().findAll(pageable);
        Page<D> dtos = entityPage.map(this::toDto);
        model.addAttribute(getModelParameter() + PAGE_POSTFIX, dtos);
        return getModelParameter() + PAGE_POSTFIX;
    }

    protected void checkDtoForUpdate(D dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Input is null");
        }
    }

    protected E entityPreUpdateAction(E updatedEntity) {
        return updatedEntity;
    }
}
