package ru.text.nastya.domain.services.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.text.nastya.domain.entities.Tag;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.TagRepository;
import ru.text.nastya.domain.services.crud.TagCrudService;

@Validated
@Service
public class TagCrudServiceImpl extends AbstractCrudServiceImpl<Tag> implements TagCrudService {

    private final TagRepository tagRepository;

    @Autowired
    public TagCrudServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    protected PersistedEntityRepository<Tag> getRepository() {
        return tagRepository;
    }
}
