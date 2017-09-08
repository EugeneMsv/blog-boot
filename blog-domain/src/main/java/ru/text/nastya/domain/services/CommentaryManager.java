package ru.text.nastya.domain.services;

import ru.text.nastya.domain.entities.Commentary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentaryManager {

    Commentary addToPostRegister(Long postRegisterId, Commentary commentary);

    void removeFromPostRegister(Long postRegisterId, Long commentaryId);

    Page<Commentary> findAllFromPostRegister(Long postRegisterId, Pageable pageable);

}
