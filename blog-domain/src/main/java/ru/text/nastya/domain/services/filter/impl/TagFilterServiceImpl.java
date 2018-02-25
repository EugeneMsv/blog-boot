package ru.text.nastya.domain.services.filter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.TagRepository;
import ru.text.nastya.domain.services.filter.TagFilterService;
import ru.text.nastya.filters.TagFilter;
import ru.text.nastya.profiling.Profiling;


@Profiling(showArgs = true, timeRecord = true, showOutput = true)
@Service
public class TagFilterServiceImpl extends FilteredEntityServiceImpl<Tag, TagFilter> implements TagFilterService {

    private final TagRepository tagRepository;

    @Autowired
    public TagFilterServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    protected PersistedEntityRepository<Tag> getRepository() {
        return tagRepository;
    }
}
