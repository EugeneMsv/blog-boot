package ru.text.nastya.domain.repositories;

import ru.text.nastya.domain.entities.Post;

import java.util.Optional;

public interface PostRepository extends PersistedEntityRepository<Post> {

    Optional<Post> findByPostRegisterUuid(String uuid);

}
