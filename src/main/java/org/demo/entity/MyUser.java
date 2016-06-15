package org.demo.entity;

import org.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class MyUser {
    @Autowired
    private UserRoleService userRoleService;

    @Id
    @Column (name = "username")
    String username;
    @Column(name = "password")
    String password;

    @ManyToMany
    @JoinTable(name="userrolelink", joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name="userroleid"))
    Set<UserRole> userRole;

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

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
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

    public MyUser(String username, String password, String userRole) {
        setUsername(username);
        setPassword(password);
        HashSet<UserRole> userRoles = new HashSet<>(1);
        userRoles.add(userRoleService.getUserRoleByName(userRole));
        setUserRole(userRoles);
    }
}
