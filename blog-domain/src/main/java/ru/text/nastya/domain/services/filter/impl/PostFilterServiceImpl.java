package ru.text.nastya.domain.services.filter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.PostRepository;
import ru.text.nastya.domain.services.filter.PostFilterService;
import ru.text.nastya.filters.PostFilter;

@Service
public class PostFilterServiceImpl extends FilteredEntityServiceImpl<Post, PostFilter> implements PostFilterService {

    private final PostRepository postRepository;

    @Autowired
    public PostFilterServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    protected PersistedEntityRepository<Post> getRepository() {
        return postRepository;
    }

}
