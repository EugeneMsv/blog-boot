package ru.text.nastya.domain.services.crud;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import ru.text.nastya.domain.entities.credential.User;

import java.util.Optional;

@Validated
public interface UserCrudService extends CrudService<User> {

    @Validated
    Optional<User> findBySsoId(@NotEmpty String ssoId);

    @Validated
    void deleteBySsoId(@NotEmpty String ssoId);
}
