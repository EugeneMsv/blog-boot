package ru.text.nastya.domain.services.crud.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.credential.User;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.UserRepository;
import ru.text.nastya.domain.services.crud.UserCrudService;
import ru.text.nastya.util.ValidationMessages;

import java.util.Optional;

@Service
public class UserCrudServiceImpl extends AbstractCrudServiceImpl<User> implements UserCrudService {

    private final UserRepository userRepository;

    @Autowired
    public UserCrudServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected PersistedEntityRepository<User> getRepository() {
        return userRepository;
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
