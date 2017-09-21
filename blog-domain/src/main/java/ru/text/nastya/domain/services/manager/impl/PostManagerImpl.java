package ru.text.nastya.domain.services.manager.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.entities.PostRegister;
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
    public Post addPost(Long postRegisterId, Post post) {
        Validate.notNull(postRegisterId, "Post register id must be set");
        Validate.notNull(post, "Post object must be set");

        Optional<PostRegister> registerOpt = postRegisterRepository.findOne(postRegisterId);

        return registerOpt.map(register -> {
            post.setPostRegister(register);
            return postRepository.save(post);
        }).orElseThrow(() -> new DataNotFoundException("Not found post register with id = " + postRegisterId));
    }

    @Transactional
    @Override
    public void removePost(Long postRegisterId) {
        Validate.notNull(postRegisterId, "Post register id must be set");
        Optional<Post> postOpt = postRepository.findByPostRegisterId(postRegisterId);
        if (postOpt.isPresent()) {
            postRepository.delete(postOpt.get().getId());
        } else {
            throw new DataNotFoundException("Not found post for register with id = " + postRegisterId);
        }
    }

    @Transactional(readOnly=true)
    @Override
    public Post getPost(Long postRegisterId) {
        Validate.notNull(postRegisterId, "Post register id must be set");
        Optional<Post> postOpt = postRepository.findByPostRegisterId(postRegisterId);
        return postOpt.orElse(null);
    }
}
