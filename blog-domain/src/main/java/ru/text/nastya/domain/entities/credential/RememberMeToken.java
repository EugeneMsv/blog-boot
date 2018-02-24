package ru.text.nastya.domain.entities.credential;

import ru.text.nastya.domain.entities.base.Identity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "remember_token", indexes = {
        @Index(name = "remember_token_series_idx", columnList = "series"),
        @Index(name = "remember_token_user_name_idx", columnList = "user_name")})
public class RememberMeToken extends Identity {

    private static final long serialVersionUID = 1;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "series", nullable = false)
    private String series;

    @Column(name = "token_value")
    private String tokenValue;

    @Column(name = "last_used")
    private LocalDateTime lastUsed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public LocalDateTime getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        this.lastUsed = lastUsed;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RememberMeToken && super.equals(o);
    }
}
