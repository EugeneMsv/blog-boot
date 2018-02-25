package ru.text.nastya.domain.repositories;


import ru.text.nastya.domain.entities.credential.User;

import java.util.Optional;

public interface UserRepository extends PersistedEntityRepository<User> {

    Optional<User> findBySsoId(String ssoId);

    void deleteBySsoId(String ssoId);

    boolean existsBySsoId(String ssoId);
}
