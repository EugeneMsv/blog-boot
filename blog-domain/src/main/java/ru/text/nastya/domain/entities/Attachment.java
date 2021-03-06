package ru.text.nastya.domain.entities;


import ru.text.nastya.domain.entities.base.Identity;

import javax.persistence.*;

/**
 * Зависимая сущность от {@link Post}
 */
@Entity
@Table(name = "attachment")
public class Attachment extends Identity {

    private static final long serialVersionUID = 1L;

    @Lob
    @Column(name = "file", length = 500000)
    private byte[] file;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    protected boolean proxyCheck(Object o) {
        return o instanceof Attachment;
    }
}
