package ru.text.nastya.domain.services.manager;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import ru.text.nastya.domain.entities.Post;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Validated
public interface PostManager {

    @Validated
    Post addPost(@NotEmpty String postRegisterUuid, @Valid @NotNull Post post);

    @Validated
    void removePost(@NotEmpty String postRegisterUuid);

    @Validated
    Optional<Post> getPost(@NotEmpty String postRegisterUuid);
}
