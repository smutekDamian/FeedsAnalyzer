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
    private Session session;

    public GenericDAOHibernate(Class<T> type){
        this.type = type;
        session = getSession();
        session.beginTransaction();
    }

    @SuppressWarnings("unchecked")
    public PK create(T newInstance) {
        PK result = (PK) session.save(newInstance);
        return result;
    }

    public T read(PK id) {
        T result = session.get(type,id);
        return result;
    }

    public void update(T transientObject) {
        session.update(transientObject);
    }

    public void delete(T persistentObject) {
        session.delete(persistentObject);
    }

    public Session getSession()
    {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.openSession();
    }

    public void closeSession(){
        sessionFactory.close();
        session.getTransaction().commit();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
