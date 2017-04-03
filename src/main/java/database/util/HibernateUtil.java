package database.util;

import database.DAO.GenericDAO;
import database.DAO.GenericDAOHibernate;
import database.POJO.Countries;
import org.hibernate.SessionFactory;


/**
 * Created by damian on 03.04.17.
 */
public class HibernateUtil {
    private static SessionFactory factory;
    public static void main(String[] args){
        GenericDAO<Countries,Integer> dao = new GenericDAOHibernate<Countries,Integer>(Countries.class);
        Countries country = new Countries();
        country.setName("Poland");
        Integer result = dao.create(country);
        System.out.println("WYNIK TO : " + result);
        dao.closeSession();
    }
}
