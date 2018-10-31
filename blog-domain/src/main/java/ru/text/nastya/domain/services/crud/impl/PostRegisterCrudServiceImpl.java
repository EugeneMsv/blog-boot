package ru.text.nastya.domain.services.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.Commentary;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.PostRegisterRepository;
import ru.text.nastya.domain.services.crud.PostRegisterCrudService;
import ru.text.nastya.domain.services.manager.CommentaryManager;
import ru.text.nastya.domain.services.manager.PostManager;

import java.util.Optional;

@Service
public class PostRegisterCrudServiceImpl extends AbstractCrudServiceImpl<PostRegister>
        implements PostRegisterCrudService {

    private final PostRegisterRepository postRegisterRepository;

    private final CommentaryManager commentaryManager;

    private final PostManager postManager;

    @Autowired
    public PostRegisterCrudServiceImpl(PostRegisterRepository postRegisterRepository,
                                       CommentaryManager commentaryManager,
                                       PostManager postManager) {
        this.postRegisterRepository = postRegisterRepository;
        this.commentaryManager = commentaryManager;
        this.postManager = postManager;
    }

    @Override
    protected PersistedEntityRepository<PostRegister> getRepository() {
        return postRegisterRepository;
    }


    @Override
    public Commentary addCommentary(String postRegisterUuid, Commentary commentary) {
        return commentaryManager.addToPostRegister(postRegisterUuid, commentary);
    }

    @Override
    public void removeCommentary(String postRegisterUuid, String commentaryId) {
        commentaryManager.removeFromPostRegister(postRegisterUuid, commentaryId);
    }

    @Override
    public Page<Commentary> findAllCommentaries(String postRegisterUuid, Pageable pageable) {
        return commentaryManager.findAllFromPostRegister(postRegisterUuid, pageable);
    }

    @Override
    public Post addPost(String postRegisterUuid, Post post) {
        return postManager.addPost(postRegisterUuid, post);
    }

    @Override
    public void removePost(String postRegisterUuid) {
        postManager.removePost(postRegisterUuid);
    }

    @Override
    public Optional<Post> getPost(String postRegisterUuid) {
        return postManager.getPost(postRegisterUuid);
    }
}
