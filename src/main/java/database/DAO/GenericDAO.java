package database.DAO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by damian on 04.04.17.
 */
public interface GenericDAO <T, PK extends Serializable> {

    PK create(T newInstance);
    T read(PK id);
    void update(T transientObject);
    void delete(T persistentObject);
    void closeSession();
    List executeQuery(String query);
    void flushAndClear();
}
