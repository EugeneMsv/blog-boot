package ru.text.nastya.domain.entities.credential;

import ru.text.nastya.domain.entities.base.Identity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_cred", indexes = {
        @Index(name = "user_cred_sso_idx", columnList = "sso_id", unique = true)})
public class User extends Identity {

    private static final long serialVersionUID = 1L;

    @Column(name = "sso_id", nullable = false, unique = true)
    private String ssoId;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @OneToMany
    @JoinTable(
            name = "user_cred_user_group",
            joinColumns = @JoinColumn(name = "user_cred_id"),
            inverseJoinColumns = @JoinColumn(name = "user_group_id")
    )
    private List<UserGroup> groups;

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
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
    protected boolean proxyCheck(Object o) {
        return o instanceof User;
    }
}
