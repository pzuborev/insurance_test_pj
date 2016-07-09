package org.demo.entity.security;

import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.util.Set;

@Entity(name = "users")
public class MyUser implements Entity {

    @Id
    @Column (name = "username")
    String username;
    @Column(name = "password")
    String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="userrolelink", joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name="userroleid"))
    Set<MyUserRole> myUserRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<MyUserRole> getMyUserRole() {
        return myUserRole;
    }

    public void setMyUserRole(Set<MyUserRole> myUserRole) {
        this.myUserRole = myUserRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyUser)) return false;

        MyUser myUser = (MyUser) o;

        return getUsername().equals(myUser.getUsername());

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;

        return result;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    public MyUser(String username, String password, Set<MyUserRole> myUserRoles) {
        setUsername(username);
        setPassword(password);
        setMyUserRole(myUserRoles);
    }

    public MyUser() {
    }

    @Override
    public String name() {
        return "User";
    }
}
