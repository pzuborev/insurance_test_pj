package org.demo.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "tokens")
public class Token {
    @ManyToOne
    @JoinColumn(name = "username")
    private MyUser user;

    @Id
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Token)) return false;

        Token token1 = (Token) o;

        return getToken().equals(token1.getToken());

    }

    @Override
    public int hashCode() {
        return getToken().hashCode();
    }
}
