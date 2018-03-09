package ru.text.nastya.domain.entities.credential;

import ru.text.nastya.domain.entities.base.Dictionary;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_group", indexes =
        {@Index(name = "user_group_code_idx", columnList = "code", unique = true)})
public class UserGroup extends Dictionary {

    private static final long serialVersionUID = 1L;

    public UserGroup() {
    }

    public UserGroup(String code, String name) {
        super(code, name);
    }

    public UserGroup(String code) {
        super(code);
    }

    @OneToMany
    @JoinTable(
            name = "user_group_user_role",
            joinColumns = @JoinColumn(name = "user_group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_role_id")
    )
    private List<UserRole> roles;

    public List<UserRole> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof UserGroup && super.equals(o);
    }
}
