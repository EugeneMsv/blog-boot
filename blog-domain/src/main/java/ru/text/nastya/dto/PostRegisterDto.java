package ru.text.nastya.dto;


import ru.text.nastya.dto.base.IdentityDto;

import java.time.LocalDateTime;

public class PostRegisterDto extends IdentityDto {

    private static final long serialVersionUID = -6241819091243049677L;

    private String preview;

    private Long likes;

    private Long views;

    private Long commentsNum;

    private LocalDateTime createdTime;

    private String metaInfo;

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Long commentsNum) {
        this.commentsNum = commentsNum;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
    }
}
