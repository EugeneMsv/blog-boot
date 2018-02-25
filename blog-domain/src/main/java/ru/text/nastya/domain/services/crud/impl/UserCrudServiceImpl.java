package ru.text.nastya.domain.services.crud.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.text.nastya.domain.entities.credential.User;
import ru.text.nastya.domain.repositories.PersistedEntityRepository;
import ru.text.nastya.domain.repositories.UserRepository;
import ru.text.nastya.domain.services.crud.UserCrudService;

import java.util.Optional;

@Service
public class UserCrudServiceImpl extends AbstractCrudServiceImpl<User> implements UserCrudService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserCrudServiceImpl(UserRepository userRepository,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected PersistedEntityRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public User save(User target) {
        Validate.notNull(target, "save: User mustn't be empty");
        target.setPassword(passwordEncoder.encode(target.getPassword()));
        return super.save(target);
    }

    @Override
    public Optional<User> findBySsoId(String ssoId) {
        Validate.notEmpty(ssoId, "findBySsoId: SsoId mustn't be empty");
        return userRepository.findBySsoId(ssoId);
    }

    @Override
    public void deleteBySsoId(String ssoId) {
        Validate.notEmpty(ssoId, "deleteBySsoId: SsoId mustn't be empty");
        if (userRepository.existsBySsoId(ssoId)) {
            userRepository.deleteBySsoId(ssoId);
        }
    }
}
