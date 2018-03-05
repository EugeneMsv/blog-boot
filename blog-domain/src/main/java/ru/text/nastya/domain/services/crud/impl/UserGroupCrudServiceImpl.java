package ru.text.nastya.domain.services.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.domain.entities.credential.UserRole;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.UserGroupRepository;
import ru.text.nastya.domain.repositories.UserRoleRepository;
import ru.text.nastya.domain.services.crud.UserGroupCrudService;
import ru.text.nastya.exception.DataNotFoundException;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class UserGroupCrudServiceImpl extends AbstractCrudServiceImpl<UserGroup> implements UserGroupCrudService {

    private final UserGroupRepository userGroupRepository;

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserGroupCrudServiceImpl(UserGroupRepository userGroupRepository,
                                    UserRoleRepository userRoleRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    protected PersistedEntityRepository<UserGroup> getRepository() {
        return userGroupRepository;
    }

    @Override
    protected UserGroup preSaveAction(UserGroup target) {
        List<UserRole> roles = target.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            long linkedRoles = IntStream.range(0, roles.size())
                    .filter(i -> roles.get(i) != null && roles.get(i).getId() == null)
                    .peek(i -> {
                        String code = roles.get(i).getCode();
                        UserRole userRole = userRoleRepository.findByCodeIgnoreCase(code)
                                .orElseThrow(() -> new DataNotFoundException("Not found user role with code=" + code));
                        roles.set(i, userRole);
                    }).count();
        }
        return target;
    }
}
