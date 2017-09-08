package ru.text.nastya.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.text.nastya.TagFilter;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.domain.services.FilteredEntityService;
import ru.text.nastya.domain.services.TagService;
import ru.text.nastya.dto.TagDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.TagMapper;
import ru.text.nastya.web.controllers.base.AbstractFilterController;

@RestController
@RequestMapping("/tag/filter")
public class TagFilterController extends AbstractFilterController<Tag, TagDto, TagFilter> {

    private final TagService tagService;

    private final TagMapper tagMapper;

    @Autowired
    public TagFilterController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @Override
    protected FilteredEntityService<Tag, TagFilter> getFilteredEntityService() {
        return tagService;
    }

    @Override
    protected EntityMapper<Tag, TagDto> getEntityMapper() {
        return tagMapper;
    }
}
