package org.demo.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "userroles")
public class InsUserRole {
    @Id
    @Column()
    String id;
    @Column(name = "name")
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InsUserRole() {
    }

    public InsUserRole(String name) {
        this.name = name;
    }

    public InsUserRole(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InsUserRole)) return false;

        InsUserRole that = (InsUserRole) o;

        return getName().equals(that.getName());

    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
