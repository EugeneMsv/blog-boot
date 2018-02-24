package ru.text.nastya.domain.entities;


import ru.text.nastya.domain.entities.base.Identity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Зависимая сущность от {@link PostRegister}, но можно создать регистр одновременно с Post
 */
@Entity
@Table(name = "post")
public class Post extends Identity {

    private static final long serialVersionUID = -8786471757326098213L;

    @Column(name = "code", unique = true, length = 100)
    private String code;

    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "text", nullable = false, columnDefinition = "TEXT")
    private String text;

    @ManyToMany
    private List<Tag> tags;

    @Column(name = "author", nullable = false)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private PostState state;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "postRegister_id", nullable = false)
    private PostRegister postRegister;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tag> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public PostState getState() {
        return state;
    }

    public void setState(PostState state) {
        this.state = state;
    }

    public PostRegister getPostRegister() {
        return postRegister;
    }

    public void setPostRegister(PostRegister postRegister) {
        this.postRegister = postRegister;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && o instanceof Post && getId().equals(((Identity) o).getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
