package database.util;

import database.DAO.GenericDAO;
import database.DAO.GenericDAOHibernate;
import database.POJO.Country;


/**
 * Created by damian on 03.04.17.
 */
public class HibernateUtil {
    public static void main(String[] args){
        GenericDAO<Country,Integer> dao = new GenericDAOHibernate<Country,Integer>(Country.class);
        Country country = new Country();
        country.setName("Poland");
        Integer result = dao.create(country);
        System.out.println("WYNIK TO : " + result);
        dao.closeSession();
    }

    public static void addLanguages(){

    }
}
