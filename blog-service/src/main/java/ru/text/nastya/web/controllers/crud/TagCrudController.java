package ru.text.nastya.web.controllers.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.domain.services.crud.CrudService;
import ru.text.nastya.domain.services.crud.TagCrudService;
import ru.text.nastya.dto.TagDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.TagMapper;
import ru.text.nastya.web.controllers.base.AbstractCrudController;

@RestController
@RequestMapping("/tag")
public class TagCrudController extends AbstractCrudController<Tag, TagDto> {

    private final TagCrudService tagCrudService;

    private final TagMapper tagMapper;

    @Autowired
    public TagCrudController(TagCrudService tagCrudService, TagMapper tagMapper) {
        this.tagCrudService = tagCrudService;
        this.tagMapper = tagMapper;
    }

    @Override
    protected CrudService<Tag> getCrudService() {
        return tagCrudService;
    }

    @Override
    protected EntityMapper<Tag, TagDto> getEntityMapper() {
        return tagMapper;
    }
}
