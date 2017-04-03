package database.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

/**
 * Created by damian on 04.04.17.
 */
public class GenericDAOHibernate <T, PK extends Serializable> implements GenericDAO<T,PK> {

    private SessionFactory sessionFactory;
    private Class<T> type;

    public GenericDAOHibernate(Class<T> type){
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public PK create(T newInstance) {
        return (PK) getSession().save(newInstance);
    }

    public T read(PK id) {
        return (T) getSession().get(type,id);
    }

    public void update(T transientObject) {
        getSession().update(transientObject);
    }

    public void delete(T persistentObject) {
        getSession().delete(persistentObject);
    }

    public Session getSession()
    {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
