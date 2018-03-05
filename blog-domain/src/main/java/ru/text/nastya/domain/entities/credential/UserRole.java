package ru.text.nastya.domain.entities.credential;

import ru.text.nastya.domain.entities.base.Dictionary;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "user_role", indexes =
        {@Index(name = "user_role_code_idx", columnList = "code", unique = true)})
public class UserRole extends Dictionary {

    private static final long serialVersionUID = 1L;

    public UserRole(String code, String name) {
        super(code, name);
    }

    public UserRole() {
    }

    public UserRole(String code) {
        super(code);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof UserRole && super.equals(o);
    }
}
