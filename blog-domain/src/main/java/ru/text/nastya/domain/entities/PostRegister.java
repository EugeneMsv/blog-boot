package ru.text.nastya.domain.entities;


import ru.text.nastya.domain.entities.base.Identity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Основная сущность
 */
@Entity
@Table(name = "post_register")
public class PostRegister extends Identity {

    private static final long serialVersionUID = 1L;

    @Column(name = "preview")
    private String preview;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "views")
    private Long views;

    @Column(name = "comments_num")
    private Long commentsNum;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Lob
    @Column(name = "meta_info", columnDefinition = "TEXT")
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

    @Override
    protected boolean proxyCheck(Object o) {
        return o instanceof PostRegister;
    }
}
