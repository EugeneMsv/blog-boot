package ru.text.nastya.domain.services;

import ru.text.nastya.PostRegisterFilter;
import ru.text.nastya.domain.entities.Commentary;
import ru.text.nastya.domain.entities.PostRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRegisterService extends FilteredEntityService<PostRegister, PostRegisterFilter> {

    Commentary addToCommentary(Long postRegisterId, Commentary commentary);

    void removeCommentary(Long postRegisterId, Long commentaryId);

    Page<Commentary> findAllCommentaries(Long postRegisterId, Pageable pageable);
}
