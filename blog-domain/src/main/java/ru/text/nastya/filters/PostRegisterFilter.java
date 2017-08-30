package ru.text.nastya.filters;

import java.time.LocalDateTime;

/**
 * Фильтр для поиска пост регистров
 */
public class PostRegisterFilter implements Filter {

    private String preview;

    private Long likes;

    private Long views;

    private Long commentsNum;

    private LocalDateTime createdTime;

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
}
