package ru.text.nastya.utils;

import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.dto.PostRegisterDto;

import java.time.LocalDateTime;

import static ru.text.nastya.utils.DomainEntityBuilder.buildRandomString;
import static ru.text.nastya.utils.DomainEntityBuilder.rand;

public class PostRegisterBuilder {

    private PostRegister postRegister;

    private PostRegisterDto postRegisterDto;

    public PostRegisterDto randomDto() {
        return dto()
                .general(rand.nextLong(), rand.nextLong(), rand.nextLong())
                .info(buildRandomString(), LocalDateTime.now())
                .meta(buildRandomString())
                .buildDto();
    }

    public PostRegister random() {
        return entity()
                .general(rand.nextLong(), rand.nextLong(), rand.nextLong())
                .info(buildRandomString(), LocalDateTime.now())
                .meta(buildRandomString())
                .buildEntity();
    }

    public PostRegisterBuilder entity() {
        this.postRegister = new PostRegister();
        return this;
    }

    public PostRegisterBuilder dto() {
        this.postRegisterDto = new PostRegisterDto();
        return this;
    }

    public PostRegisterBuilder info(String preview, LocalDateTime createdTime) {
        if (postRegister != null) {
            postRegister.setPreview(preview);
            postRegister.setCreatedTime(createdTime);
        } else {
            postRegisterDto.setPreview(preview);
            postRegisterDto.setCreatedTime(createdTime);
        }
        return this;
    }

    public PostRegisterBuilder meta(String metaInfo) {
        if (postRegister != null) {
            postRegister.setMetaInfo(metaInfo);
        } else {
            postRegisterDto.setMetaInfo(metaInfo);
        }
        return this;
    }

    public PostRegisterBuilder general(Long likes, Long views, Long commentsNum) {
        if (postRegister != null) {
            postRegister.setLikes(likes);
            postRegister.setViews(views);
            postRegister.setCommentsNum(commentsNum);
        } else {
            postRegisterDto.setLikes(likes);
            postRegisterDto.setViews(views);
            postRegisterDto.setCommentsNum(commentsNum);
        }
        return this;
    }

    public PostRegister buildEntity() {
        return postRegister;
    }

    public PostRegisterDto buildDto() {
        return postRegisterDto;
    }

}
