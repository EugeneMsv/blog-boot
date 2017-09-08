package ru.text.nastya.domain.services.impl;

import ru.text.nastya.PostRegisterFilter;
import ru.text.nastya.domain.entities.Commentary;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.PostRegisterRepository;
import ru.text.nastya.domain.services.CommentaryManager;
import ru.text.nastya.domain.services.PostRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostRegisterServiceImpl extends FilteredEntityServiceImpl<PostRegister, PostRegisterFilter>
        implements PostRegisterService {

    private final PostRegisterRepository postRegisterRepository;

    private final CommentaryManager commentaryManager;

    @Autowired
    public PostRegisterServiceImpl(PostRegisterRepository postRegisterRepository,
                                   CommentaryManager commentaryManager) {
        this.postRegisterRepository = postRegisterRepository;
        this.commentaryManager = commentaryManager;
    }

    @Override
    protected PersistedEntityRepository<PostRegister> getRepository() {
        return postRegisterRepository;
    }


    @Override
    public Commentary addToCommentary(Long postRegisterId, Commentary commentary) {
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
}
