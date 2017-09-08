package ru.text.nastya.domain.services.impl;

import ru.text.nastya.PostFilter;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.PostRepository;
import ru.text.nastya.domain.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends FilteredEntityServiceImpl<Post, PostFilter> implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    protected PersistedEntityRepository<Post> getRepository() {
        return postRepository;
    }

}
