package se.hm.backend;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import se.hm.dtos.ListResult;

/**
 * This is a so-called DAO, meaning Data Access Object. It encapsulates all
 * database-communications. The central object in this class is its'
 * {@link SessionFactory}, which is the object managing the currents' thread
 * session. It manages batching of requests, dealing with multiple users and
 * database transactions, etc.
 * 
 */
@Repository
@Component
public class Dao {

    @Autowired
    private SessionFactory sessionFactory;

    public Dao() {
    }

    public Dao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Transactional
    public synchronized void save(Object obj) {
        sessionFactory.getCurrentSession().save(obj);
    }
    
    @Transactional
    public synchronized List<ListResult> getAll() {
        @SuppressWarnings("unchecked")
        List<ListResult> listResults = (List<ListResult>)sessionFactory
            .getCurrentSession()
            .createQuery("from ListResult")
            .list();
        return listResults;
    }
    
}
