package org.demo.entity.insurance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "insurancepolicy")
public class Policy {
    @Id
    @Column(name = "id")
    private int id;

}
