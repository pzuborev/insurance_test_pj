package org.demo.service;

import org.demo.dao.AbstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import java.util.List;

public abstract class AbstractService <T, L extends AbstractDao> {
    @Autowired
    private L dao;

    @Transactional(readOnly = true)
    public List<T> getAll() {
        return dao.getAll();
    }
}
