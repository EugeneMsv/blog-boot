package ru.text.nastya.domain.entities.credential;

public enum UserStatus {
    ACTIVE("Активный"),
    BANNED("Заблокирован"),
    NON_ACTIVE("Не активен");

    private String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
