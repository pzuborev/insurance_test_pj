package org.demo.entity;

import org.demo.dto.TokenDto;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "tokens")
public class Token {
    @ManyToOne
    @JoinColumn(name = "username")
    private MyUser user;

    @Column(name = "token")
    private String token;

    @Column(name = "lastused")
    private Date lastUsed;

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

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

}
