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
    public Commentary addCommentary(Long postRegisterId, Commentary commentary) {
        return commentaryManager.addToPostRegister(postRegisterId, commentary);
    }

    @Override
    public void removeCommentary(Long postRegisterId, Long commentaryId) {
        commentaryManager.removeFromPostRegister(postRegisterId, commentaryId);
    }

    @Override
    public Page<Commentary> findAllCommentaries(Long postRegisterId, Pageable pageable) {
        return commentaryManager.findAllFromPostRegister(postRegisterId, pageable);
    }

    @Override
    public Post addPost(Long postRegisterId, Post post) {
        return postManager.addPost(postRegisterId, post);
    }

    @Override
    public void removePost(Long postRegisterId) {
        postManager.removePost(postRegisterId);
    }

    @Override
    public Post getPost(Long postRegisterId) {
        return postManager.getPost(postRegisterId);
    }
}
