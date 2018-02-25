package ru.text.nastya.domain.services.crud;

import ru.text.nastya.domain.entities.credential.User;

import java.util.Optional;

public interface UserCrudService extends CrudService<User> {

    Optional<User> findBySsoId(String ssoId);

    void deleteBySsoId(String ssoId);
}
