package ru.text.nastya.domain.services.action.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.domain.entities.credential.UserRole;
import ru.text.nastya.domain.repositories.UserRoleRepository;
import ru.text.nastya.domain.services.action.PreSaveInterceptor;
import ru.text.nastya.exception.DataNotFoundException;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class UserGroupPreSaveInterceptor implements PreSaveInterceptor<UserGroup> {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserGroupPreSaveInterceptor(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void execute(UserGroup target) {
        List<UserRole> roles = target.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            IntStream.range(0, roles.size())
                    .filter(i -> roles.get(i) != null && roles.get(i).getUuid() == null)
                    .forEach(i -> {
                        String code = roles.get(i).getCode();
                        UserRole userRole = userRoleRepository.findByCodeIgnoreCase(code)
                                .orElseThrow(() -> new DataNotFoundException("Not found user role with code=" + code));
                        roles.set(i, userRole);
                    });
        }
    }
}
