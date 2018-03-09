package ru.text.nastya.web.controllers.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.domain.services.filter.FilteredEntityService;
import ru.text.nastya.domain.services.filter.TagFilterService;
import ru.text.nastya.dto.TagDto;
import ru.text.nastya.dto.mapper.EntityMapper;
import ru.text.nastya.dto.mapper.TagMapper;
import ru.text.nastya.filters.TagFilter;
import ru.text.nastya.web.controllers.base.AbstractFilterController;

@RestController
@RequestMapping("/tag/filter")
public class TagFilterController extends AbstractFilterController<Tag, TagDto, TagFilter> {

    private final TagFilterService tagFilterService;

    private final TagMapper tagMapper;

    @Autowired
    public TagFilterController(TagFilterService tagFilterService, TagMapper tagMapper) {
        this.tagFilterService = tagFilterService;
        this.tagMapper = tagMapper;
    }

    @Override
    protected FilteredEntityService<Tag, TagFilter> getFilteredEntityService() {
        return tagFilterService;
    }

    @Override
    protected EntityMapper<Tag, TagDto> getEntityMapper() {
        return tagMapper;
    }
}
