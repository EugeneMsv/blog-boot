package ru.text.nastya.domain.services.filter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.UserGroupRepository;
import ru.text.nastya.domain.services.filter.UserGroupFilterService;
import ru.text.nastya.filters.UserGroupFilter;

@Service
public class UserGroupFilterServiceImpl extends FilteredEntityServiceImpl<UserGroup, UserGroupFilter>
        implements UserGroupFilterService {

    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupFilterServiceImpl(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    protected PersistedEntityRepository<UserGroup> getRepository() {
        return userGroupRepository;
    }
}
