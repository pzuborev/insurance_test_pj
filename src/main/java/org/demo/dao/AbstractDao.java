package org.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    public abstract Class<T> getEntityType();

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void persist(Object entity) {
        getSession().persist(entity);
    }

    public void delete(Object entity) {
        getSession().delete(entity);
    }

    public void update(Object entity) {
        getSession().update(entity);
    }

    public void  flush () {
        getSession().flush();
    }

    public T get(Class c, Serializable id) {
        return (T) getSession().get(c, id);
    }

    public List<T> getAll () {
        return getSession().createCriteria(getEntityType()).list();
    }
}