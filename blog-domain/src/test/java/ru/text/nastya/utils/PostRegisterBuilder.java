package ru.text.nastya.utils;

import ru.text.nastya.domain.entities.Post;
import ru.text.nastya.domain.entities.PostRegister;
import ru.text.nastya.dto.PostDto;
import ru.text.nastya.dto.PostRegisterDto;

import java.time.LocalDateTime;

public class PostRegisterBuilder {

    private PostRegister postRegister;

    private PostRegisterDto postRegisterDto;

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

    public PostRegisterBuilder post(Post post) {
        postRegister.setPost(post);
        return this;
    }

    public PostRegisterBuilder post(PostDto post) {
        postRegisterDto.setPost(post);
        return this;
    }

    public PostRegister buildEntity() {
        return postRegister;
    }

    public PostRegisterDto buildDto() {
        return postRegisterDto;
    }

}
