package ru.text.nastya.domain.services.manager.impl;

import org.apache.commons.lang3.Validate;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.repositories.PostRegisterRepository;
import ru.text.nastya.domain.repositories.PostRepository;
import ru.text.nastya.domain.services.manager.PostManager;
import ru.text.nastya.exception.DataNotFoundException;

import java.util.Optional;

@Service
public class PostManagerImpl implements PostManager {

    private final PostRepository postRepository;

    private final PostRegisterRepository postRegisterRepository;

    @Autowired
    public PostManagerImpl(PostRepository postRepository,
                           PostRegisterRepository postRegisterRepository) {
        this.postRepository = postRepository;
        this.postRegisterRepository = postRegisterRepository;
    }

    @Transactional
    @Override
    public Post addPost(@NotEmpty String postRegisterUuid, Post post) {
        Validate.notNull(postRegisterUuid, "Post register uuid must be set");
        Validate.notNull(post, "Post object must be set");

        return postRegisterRepository.findOne(postRegisterUuid)
                .map(register -> {
                    post.setPostRegister(register);
                    return postRepository.save(post);
                }).orElseThrow(() -> new DataNotFoundException("Not found post register with uuid = " + postRegisterUuid));
    }

    @Transactional
    @Override
    public void removePost(String postRegisterUuid) {
        Validate.notNull(postRegisterUuid, "Post register uuid must be set");
        Optional<Post> postOpt = postRepository.findByPostRegisterUuid(postRegisterUuid);
        if (postOpt.isPresent()) {
            postRepository.delete(postOpt.get().getUuid());
        } else {
            throw new DataNotFoundException("Not found post for register with uuid = " + postRegisterUuid);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Post> getPost(String postRegisterUuid) {
        Validate.notNull(postRegisterUuid, "Post register uuid must be set");
        return postRepository.findByPostRegisterUuid(postRegisterUuid);
    }
}
