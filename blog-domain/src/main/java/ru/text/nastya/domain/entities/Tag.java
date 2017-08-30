package ru.text.nastya.domain.entities;


import ru.text.nastya.domain.entities.base.Identity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag extends Identity {

    private static final long serialVersionUID = -8593531398845228665L;

    @Column(name = "code", unique = true, nullable = false, length = 20)
    private String code;

    @Column(name = "description")
    private String description;

    public Tag() {
    }

    public Tag(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return o != null && o instanceof Tag && getId().equals(((Identity) o).getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
