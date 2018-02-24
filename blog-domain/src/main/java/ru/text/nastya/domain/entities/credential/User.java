package ru.text.nastya.domain.entities.credential;

import ru.text.nastya.domain.entities.base.Identity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_cred")
public class User extends Identity {

    private static final long serialVersionUID = 1L;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<UserGroup> groups;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<UserGroup> getGroups() {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof User && super.equals(o);
    }
}
