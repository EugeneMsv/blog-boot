package ru.text.nastya.domain.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.TagFilter;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.TagRepository;
import ru.text.nastya.domain.services.TagService;
import ru.text.nastya.profiling.Profiling;


@Profiling(showArgs = true, timeRecord = true, showOutput = true)
@Service
public class TagServiceImpl extends FilteredEntityServiceImpl<Tag, TagFilter> implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    protected PersistedEntityRepository<Tag> getRepository() {
        return tagRepository;
    }
}
