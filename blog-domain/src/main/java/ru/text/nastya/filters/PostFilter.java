package ru.text.nastya;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Фильтр для поиска постов
 */
public class PostFilter implements Filter {

    private String author;

    private String title;

    private LocalDateTime from;

    private LocalDateTime to;

    private List<String> tags;


    /**
     * Автор
     */
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Название
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Дата начала периода
     */
    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    /**
     * Дата конца периода
     */
    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    /**
     * Коды тегов
     */
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
