package ru.text.nastya.domain.services.manager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.text.nastya.domain.entities.Commentary;

public interface CommentaryManager {

    Commentary addToPostRegister(String postRegisterUuid, Commentary commentary);

    void removeFromPostRegister(String postRegisterUuid, String commentaryUuid);

    Page<Commentary> findAllFromPostRegister(String postRegisterUuid, Pageable pageable);

}
