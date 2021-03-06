package ru.text.nastya.domain.repositories;


import ru.text.nastya.domain.entities.Commentary;

import java.util.Optional;

public interface CommentaryRepository extends PersistedEntityRepository<Commentary> {

    Optional<Commentary> findByPostRegisterUuidAndUuid(String postRegisterUuid, Long uuid);
}
