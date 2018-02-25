package ru.text.nastya.domain.services.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.UserGroupRepository;
import ru.text.nastya.domain.services.crud.UserGroupCrudService;

@Service
public class UserGroupCrudServiceImpl extends AbstractCrudServiceImpl<UserGroup> implements UserGroupCrudService {

    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupCrudServiceImpl(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    protected PersistedEntityRepository<UserGroup> getRepository() {
        return userGroupRepository;
    }
}
