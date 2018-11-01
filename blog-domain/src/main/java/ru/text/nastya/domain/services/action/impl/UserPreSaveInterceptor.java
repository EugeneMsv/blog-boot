package ru.text.nastya.domain.services.action.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.text.nastya.domain.entities.credential.User;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.domain.repositories.UserGroupRepository;
import ru.text.nastya.domain.services.action.PreSaveInterceptor;
import ru.text.nastya.exception.DataNotFoundException;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class UserPreSaveInterceptor implements PreSaveInterceptor<User> {

    private final PasswordEncoder passwordEncoder;

    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserPreSaveInterceptor(PasswordEncoder passwordEncoder, UserGroupRepository userGroupRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void execute(User target) {
        target.setPassword(passwordEncoder.encode(target.getPassword()));
        List<UserGroup> groups = target.getGroups();
        if (!CollectionUtils.isEmpty(groups)) {
            IntStream.range(0, groups.size())
                    .filter(i -> {
                        UserGroup userGroup = groups.get(i);
                        return userGroup != null && userGroup.getCode() != null;
                    })
                    .forEach(i -> {
                        String groupCode = groups.get(i).getCode();
                        UserGroup userGroup = userGroupRepository.findByCodeIgnoreCase(groupCode)
                                .orElseThrow(() -> new DataNotFoundException("Not found user group with code=" + groupCode));
                        groups.set(i, userGroup);
                    });
        }
    }
}
