package ru.text.nastya.domain.entities;

public enum PostState {
    NEW("Новый"),
    EDITION("Редактирование"),
    DELETED("Удален");

    private String description;

    PostState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
