package org.demo.entity.security;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;

@Entity(name = "tokens")
public class MyToken {
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

    public MyToken(MyUser user, String token) {
        this.user = user;
        this.token = token;
        this.lastUsed = new Date(Calendar.getInstance().getTimeInMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyToken)) return false;

        MyToken token1 = (MyToken) o;

        return getToken().equals(token1.getToken());

    }

    @Override
    public int hashCode() {
        return getToken().hashCode();
    }
}
