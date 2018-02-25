package ru.text.nastya.domain.services.filter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.credential.UserRole;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.UserRoleRepository;
import ru.text.nastya.domain.services.filter.UserRoleFilterService;
import ru.text.nastya.filters.UserRoleFilter;

@Service
public class UserRoleFilterServiceImpl extends FilteredEntityServiceImpl<UserRole, UserRoleFilter>
        implements UserRoleFilterService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleFilterServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    protected PersistedEntityRepository<UserRole> getRepository() {
        return userRoleRepository;
    }
}
