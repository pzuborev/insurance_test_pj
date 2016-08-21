package org.demo.entity.security;

import javax.persistence.*;
import java.lang.annotation.Annotation;
import java.util.Set;

@Entity(name = "users")
public class InsUser implements Entity {

    @Id
    @Column (name = "username")
    String username;
    @Column(name = "password")
    String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="userrolelink", joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name="userroleid"))
    Set<InsUserRole> insUserRole;

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

    public Set<InsUserRole> getInsUserRole() {
        return insUserRole;
    }

    public void setInsUserRole(Set<InsUserRole> insUserRole) {
        this.insUserRole = insUserRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsUser)) return false;

        InsUser insUser = (InsUser) o;

        return getUsername().equals(insUser.getUsername());

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

    public InsUser(String username, String password, Set<InsUserRole> insUserRoles) {
        setUsername(username);
        setPassword(password);
        setInsUserRole(insUserRoles);
    }

    public InsUser() {
    }

    @Override
    public String name() {
        return "User";
    }
}
