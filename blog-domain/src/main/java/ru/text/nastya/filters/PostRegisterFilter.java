package ru.text.nastya.filters;

import java.time.LocalDateTime;

/**
 * Фильтр для поиска пост регистров
 */
public class PostRegisterFilter implements Filter {

    private static final long serialVersionUID = 2L;

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

    public PostRegisterFilter withPreview(String preview) {
        this.preview = preview;
        return this;
    }

    public PostRegisterFilter withLikes(Long likes) {
        this.preview = preview;
        return this;
    }

    public PostRegisterFilter withViews(Long views) {
        this.preview = preview;
        return this;
    }

    public PostRegisterFilter withCommentsNum(Long commentsNum) {
        this.preview = preview;
        return this;
    }

    public PostRegisterFilter withCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostRegisterFilter{");
        sb.append("preview='").append(preview).append('\'');
        sb.append(", likes=").append(likes);
        sb.append(", views=").append(views);
        sb.append(", commentsNum=").append(commentsNum);
        sb.append(", createdTime=").append(createdTime);
        sb.append('}');
        return sb.toString();
    }
}
