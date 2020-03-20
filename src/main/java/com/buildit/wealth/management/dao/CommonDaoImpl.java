package com.buildit.wealth.management.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("commonDao")
public abstract class CommonDaoImpl implements CommonDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    protected Session getCurrentSession() {

        return sessionFactory.getCurrentSession();
    }
    
    protected Object insert(final Object obj) {

        final Session session = getCurrentSession();
        session.persist(obj);
        session.flush();
        
        return obj;
    }

    protected Object update(final Object obj) {

        final Session session = getCurrentSession();
        final Object updatedObj = session.merge(obj);
        session.flush();
        return updatedObj;
    }

    protected void delete(final Object obj) {

        final Session session = getCurrentSession();
        session.remove(obj);
        session.flush();
    }
}
