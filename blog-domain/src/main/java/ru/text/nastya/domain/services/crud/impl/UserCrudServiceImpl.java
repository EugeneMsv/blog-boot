package ru.text.nastya.domain.services.crud.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.text.nastya.domain.entities.credential.User;
import ru.text.nastya.domain.entities.credential.UserGroup;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.UserGroupRepository;
import ru.text.nastya.domain.repositories.UserRepository;
import ru.text.nastya.domain.services.crud.UserCrudService;
import ru.text.nastya.exception.DataNotFoundException;
import ru.text.nastya.util.ValidationMessages;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class UserCrudServiceImpl extends AbstractCrudServiceImpl<User> implements UserCrudService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserGroupRepository userGroupRepository;

    @Autowired
    public UserCrudServiceImpl(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               UserGroupRepository userGroupRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    protected PersistedEntityRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    protected User preSaveAction(User target) {
        target.setPassword(passwordEncoder.encode(target.getPassword()));
        List<UserGroup> groups = target.getGroups();
        if (!CollectionUtils.isEmpty(groups)) {
            long linkedGroups = IntStream.range(0, groups.size())
                    .filter(i -> groups.get(i) != null && groups.get(i).getUuid() == null)
                    .peek(i -> {
                        String code = groups.get(i).getCode();
                        UserGroup userGroup = userGroupRepository.findByCodeIgnoreCase(code)
                                .orElseThrow(() -> new DataNotFoundException("Not found user group with code=" + code));
                        groups.set(i, userGroup);
                    }).count();
        }
        return target;
    }

    @Override
    public Optional<User> findBySsoId(String ssoId) {
        Validate.notEmpty(ssoId, ValidationMessages.NOT_EMPTY, "ssoId");
        return userRepository.findBySsoId(ssoId);
    }

    @Override
    public void deleteBySsoId(String ssoId) {
        Validate.notEmpty(ssoId, ValidationMessages.NOT_EMPTY, "ssoId");
        if (userRepository.existsBySsoId(ssoId)) {
            userRepository.deleteBySsoId(ssoId);
        }
    }
}
