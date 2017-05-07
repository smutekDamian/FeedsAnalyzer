package database.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.loader.custom.sql.SQLQueryParser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by damian on 04.04.17.
 */
public class GenericDAOHibernate <T, PK extends Serializable> implements GenericDAO<T,PK> {

    private SessionFactory sessionFactory;
    private Class<T> type;
    private Session session;

    public GenericDAOHibernate() {
        session = getSession();
        session.beginTransaction();
    }

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

    public List executeQuery(String query){
        return session.createQuery(query).getResultList();
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
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public void flushAndClear(){
        session.flush();
        session.clear();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
