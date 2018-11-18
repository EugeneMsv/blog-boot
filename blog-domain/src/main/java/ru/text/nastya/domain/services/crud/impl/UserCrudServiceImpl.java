package ru.text.nastya.domain.services.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.credential.User;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.UserRepository;
import ru.text.nastya.domain.services.crud.UserCrudService;

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
        return userRepository.findBySsoId(ssoId);
    }

    @Override
    public void deleteBySsoId(String ssoId) {
        if (userRepository.existsBySsoId(ssoId)) {
            userRepository.deleteBySsoId(ssoId);
        }
    }
}
