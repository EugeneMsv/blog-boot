package ru.text.nastya.dto;


import ru.text.nastya.dto.base.IdentityDto;

import java.time.LocalDateTime;

public class CommentaryDto extends IdentityDto {

    private static final long serialVersionUID = -1161321911939994330L;

    private String author;

    // TODO: 17.08.2017 будет проблема с десериализацией в dto для jackson
    private LocalDateTime createdTime;

    private String message;

    private String email;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
