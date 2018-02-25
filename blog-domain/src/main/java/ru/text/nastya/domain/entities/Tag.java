package ru.text.nastya.domain.entities;


import ru.text.nastya.domain.entities.base.Dictionary;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "tag", indexes =
        {@Index(name = "tag_code_idx", columnList = "code", unique = true)})
public class Tag extends Dictionary {

    private static final long serialVersionUID = 1L;

    public Tag() {
    }

    public Tag(String code, String name) {
        super(code, name);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Tag && super.equals(o);
    }
}
