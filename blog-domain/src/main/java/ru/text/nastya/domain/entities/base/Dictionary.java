package ru.text.nastya.domain.entities.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class Dictionary extends Identity {

    @Column(name = "code", nullable = false, unique = true)
    protected String code;

    @Column(name = "name")
    protected String name;

    public Dictionary() {
    }

    public Dictionary(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Dictionary(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Dictionary)) return false;
        if (!super.equals(o)) return false;
        Dictionary that = (Dictionary) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{code='");
        sb.append(code).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
