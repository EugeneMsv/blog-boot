package ru.text.nastya.domain.services.manager;

import ru.text.nastya.domain.entities.Post;

import java.util.Optional;

public interface PostManager {

    Post addPost(String postRegisterUuid, Post post);

    void removePost(String postRegisterUuid);

    Optional<Post> getPost(String postRegisterUuid);
}
