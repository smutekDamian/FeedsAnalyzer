package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by damian on 03.04.17.
 */
public class HibernateUtil {
    private static SessionFactory factory;
    public static void main(String[] args){
        Configuration configuration = new Configuration().configure();
        factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        System.out.println("Hibernate working");
        session.getTransaction().commit();
        session.close();
        return;
    }
}
