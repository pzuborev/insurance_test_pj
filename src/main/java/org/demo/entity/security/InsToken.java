package org.demo.entity.security;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity(name = "tokens")
public class InsToken {
    @Column(name="username")
    private String username;

    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "lastused")
    private Date lastUsed;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        this.lastUsed = lastUsed;
    }

    public InsToken(String username, String token) {
        this.username = username;
        this.token = token;
        this.lastUsed = new Date(Calendar.getInstance().getTimeInMillis());
    }

    public InsToken() {
    }

    public InsToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsToken)) return false;

        InsToken token1 = (InsToken) o;

        return getToken().equals(token1.getToken());

    }

    @Override
    public int hashCode() {
        return getToken().hashCode();
    }
}
