package ru.text.nastya.domain.services.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.credential.UserRole;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.UserRoleRepository;
import ru.text.nastya.domain.services.crud.UserRoleCrudService;

@Service
public class UserRoleCrudServiceImpl extends AbstractCrudServiceImpl<UserRole> implements UserRoleCrudService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleCrudServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    protected PersistedEntityRepository<UserRole> getRepository() {
        return userRoleRepository;
    }
}
