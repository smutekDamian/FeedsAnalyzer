package analysis;

import csv.writer.WriterCsv;
import database.DAO.GenericDAO;
import database.DAO.GenericDAOHibernate;

import java.util.Iterator;
import java.util.List;

/**
 * Created by damian on 06.05.17.
 */
public class AnalysisService {

    private static final String FILEPATH_BASE = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/Analysis/";

    public static void main(String[] args) {
        creatingFileWithQuantityOfTagsForEveryNewspaper();
    }

    private static void creatingFileWithQuantityOfTagsForEveryNewspaper(){
        String folderName = "TagsForNewspapers/";
        GenericDAO dao = new GenericDAOHibernate();
        String query = "SELECT f.name, n.name, t.name,  count(t.name) FROM PressReleasesTag as prt\n" +
                "  INNER JOIN PressRelease as pr ON prt.pressReleaseID = pr.ID\n" +
                "  INNER JOIN TAG as t ON  prt.tagID = t.ID\n" +
                "  INNER JOIN Feed as f ON pr.feedID = f.ID\n" +
                "  INNER JOIN Newspaper as n ON f.newspaperID = n.ID\n" +
                "  GROUP BY n.name, t.name";
        List queryResult = dao.executeQuery(query);
        for (Object aQueryResult : queryResult) {
            Object[] queryObject = (Object[]) aQueryResult;
            System.out.println(queryObject[0] + "\t" + queryObject[1] + "\t" + queryObject[2] +  "\t" + queryObject[3]);
            String feedName = (String) queryObject[0];
            String filePath = FILEPATH_BASE + folderName + feedName + ".csv";
            WriterCsv.write(filePath, (String)queryObject[0], (String)queryObject[1], (String)queryObject[2], String.valueOf(queryObject[3]));
        }
        dao.closeSession();
    }

}
