package ru.text.nastya.domain.services.crud;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.text.nastya.domain.entities.Commentary;
import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.domain.services.crud.CrudService;

public interface PostRegisterCrudService extends CrudService<PostRegister> {

    Commentary addCommentary(Long postRegisterId, Commentary commentary);

    void removeCommentary(Long postRegisterId, Long commentaryId);

    Page<Commentary> findAllCommentaries(Long postRegisterId, Pageable pageable);

    Post addPost(Long postRegisterId, Post post);

    void removePost(Long postRegisterId);

    Post getPost(Long postRegisterId);
}
