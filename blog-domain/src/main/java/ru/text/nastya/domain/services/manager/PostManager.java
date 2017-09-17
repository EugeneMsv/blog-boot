package ru.text.nastya.domain.services.manager;

import ru.text.nastya.domain.entities.Post;

public interface PostManager {

    Post addPost(Long postRegisterId, Post post);

    void removePost(Long postRegisterId);

    Post getPost(Long postRegisterId);
}
