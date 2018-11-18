package ru.text.nastya.domain.services.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.text.nastya.domain.entities.Commentary;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.entities.PostRegister;

import java.util.Optional;

public interface PostRegisterCrudService extends CrudService<PostRegister> {

    Commentary addCommentary(String postRegisterUuid, Commentary commentary);

    void removeCommentary(String postRegisterUuid, String commentaryUuid);

    Page<Commentary> findAllCommentaries(String postRegisterUuid, Pageable pageable);

    Post addPost(String postRegisterUuid, Post post);

    void removePost(String postRegisterUuid);

    Optional<Post> getPost(String postRegisterUuid);
}
