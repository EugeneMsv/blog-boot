package ru.text.nastya.domain.services.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.Commentary;
import ru.text.nastya.domain.repositories.CommentaryRepository;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.services.crud.impl.AbstractCrudServiceImpl;
import ru.text.nastya.domain.services.manager.CommentaryManager;

@Service
public class CommentaryManagerImpl extends AbstractCrudServiceImpl<Commentary> implements CommentaryManager {

    private final CommentaryRepository commentaryRepository;

    @Autowired
    public CommentaryManagerImpl(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    @Override
    protected PersistedEntityRepository<Commentary> getRepository() {
        return commentaryRepository;
    }

    @Override
    public Commentary addToPostRegister(String postRegisterUuid, Commentary commentary) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeFromPostRegister(String postRegisterUuid, String commentaryUuid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Commentary> findAllFromPostRegister(String postRegisterUuid, Pageable pageable) {
        throw new UnsupportedOperationException();
    }
}
