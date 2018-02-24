package ru.text.nastya.domain.entities.credential;

import ru.text.nastya.domain.entities.base.Dictionary;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_group")
public class UserGroup extends Dictionary {

    private static final long serialVersionUID = 1L;

    @OneToMany
    @JoinColumn(name = "user_group_id")
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
