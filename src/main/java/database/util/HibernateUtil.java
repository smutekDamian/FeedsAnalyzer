package database.util;

import csv.reader.ReaderCsv;
import csv.writer.WriterCsv;
import database.DAO.GenericDAO;
import database.DAO.GenericDAOHibernate;
import database.POJO.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Created by damian on 03.04.17.
 */
public class HibernateUtil {
    public static void main(String[] args){
//        addLanguagesToDB();
//        addCountriesToDB();
//        addNewspapersToDB();
//        addTagsToDb();
//        addFeedsDataToDB();
//        addPressReleasesToDB();
        addPressReleasesTagsData();

    }

    public static void addLanguagesToDB(){
        String[] languages = {
                "English",
                "Spanish",
                "French",
        };
        GenericDAO<Language,Integer> dao = new GenericDAOHibernate<Language, Integer>((Language.class));
        for (String language: languages) {
            Language lg = new Language();
            lg.setName(language);
            dao.create(lg);
        }
        dao.closeSession();
    }

    public static void addNewspapersToDB(){
        String[] newspapersNames = {
                "South China Morning Post",
                "Le Monde",
                "The Times of India",
                "El Universal",
                "The New York Times"
        };
        String[] newspapersCountry = {
                "China",
                "France",
                "India",
                "Mexico",
                "United States of America"
        };
        String[] newspapersLanguage = {
                "English",
                "French",
                "English",
                "Spanish",
                "English"
        };
        GenericDAO<Newspaper,Integer> dao = new GenericDAOHibernate<Newspaper, Integer>(Newspaper.class);
        for (int i = 0; i < newspapersNames.length; i++){
            String countryIdQuery = "select ID from Country where name = \'" + newspapersCountry[i] +"\'";
            String languageIdQuery = "select ID from Language where name = \'" + newspapersLanguage[i]+"\'";
            List countryIds = dao.executeQuery(countryIdQuery);
            List languagesIds = dao.executeQuery(languageIdQuery);
            if (countryIds.size() != 1 || languagesIds.size() != 1){
                continue;
            }
            Newspaper newspaper = new Newspaper();
            newspaper.setName(newspapersNames[i]);
            newspaper.setCountryID(new Country((Integer) countryIds.get(0)));
            newspaper.setLanguageID(new Language((Integer) languagesIds.get(0)));
            dao.create(newspaper);
        }
        dao.closeSession();
    }

    public static void addCountriesToDB(){
        String filePath = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/tagsAndCountries.csv";
        List<String> countries = ReaderCsv.readAtPosition(filePath, 1);
        GenericDAO<Country,Integer> dao = new GenericDAOHibernate<Country, Integer>(Country.class);
        for (int i = 0; i < countries.size(); i++){
            Country addingCountry = new Country();
            addingCountry.setName(countries.get(i));
            System.out.println(countries.get(i));
            dao.create(addingCountry);
            if (i % 20 == 0){
                dao.flushAndClear();
            }
        }
        dao.closeSession();
    }


    public static void createTagsAndCountriesFile(){
        String resultFilePath = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/tagsAndCountries.csv";
        String tagsFilePath = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/Dico_Country_Free.csv";
        String countriesFilePath = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/countries.csv";
        Map<String,String> tagsAndCountries = ReaderCsv.readTwoFilesAndReturnTagsWithCountries(tagsFilePath,countriesFilePath);
        WriterCsv.writeTagsAndCountries(resultFilePath, tagsAndCountries);
    }

    public static void addTagsToDb(){
        String filePath = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/tagsAndCountries.csv";
        List<String> countries = ReaderCsv.readAtPosition(filePath, 1);
        List<String> tags = ReaderCsv.readAtPosition(filePath, 0);
        GenericDAO<TAG,Integer> dao = new GenericDAOHibernate<TAG,Integer>(TAG.class);
        for (int i = 0; i < tags.size(); i++){
            String countryIdQuery = "select ID from Country where name = \'" + countries.get(i) +"\'";
            TAG tag = new TAG();
            tag.setName(tags.get(i));
            List countriesIDs = dao.executeQuery(countryIdQuery);
            if (countriesIDs.size() != 1){
                continue;
            }
            tag.setCountryID(new Country((Integer) countriesIDs.get(0), countries.get(i)));
            dao.create(tag);
            if (i % 20 == 0){
                dao.flushAndClear();
            }
        }
        dao.closeSession();
    }
    public static void addFeedsDataToDB(){
        String intSection = "International";
        String[] feedsNames = {
                "fr_FRA_lmonde_int",
                "en_CHN_mopost_int",
                "en_IND_tindia_int",
                "es_MEX_univer_int",
                "en_USA_nytime_int"
        };
        String[] newspapersNames = {
                "Le Monde",
                "South China Morning Post",
                "The Times of India",
                "El Universal",
                "The New York Times"
        };

        GenericDAO<Feed,Integer> dao = new GenericDAOHibernate<Feed, Integer>(Feed.class);
        for (int i = 0; i < feedsNames.length; i++){
            String queryForNewspapers = "from Newspaper where name = \'" + newspapersNames[i] +"\'";
            List newspapers = dao.executeQuery(queryForNewspapers);
            if (newspapers.size() != 1){
                continue;
            }
            Feed addingFeed = new Feed();
            addingFeed.setName(feedsNames[i]);
            addingFeed.setNewspaperID((Newspaper) newspapers.get(0));
            addingFeed.setSection(intSection);
            dao.create(addingFeed);
            if (i % 20 == 0){
                dao.flushAndClear();
            }
        }
        dao.closeSession();
    }
    public static void addPressReleasesToDB(){
        String[] filesPaths = {
//                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/en_CHN_mopost_int/rss_unique_tagged.csv",
//                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/fr_FRA_lmonde_int/rss_unique_tagged.csv",
//                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/en_IND_tindia_int/rss_unique_tagged.csv",
//                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/es_MEX_univer_int/rss_unique_tagged.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/en_USA_nytime_int/rss_unique_tagged.csv"
        };
        for (String filePath: filesPaths){
            List<String> feedsNames = ReaderCsv.readAtPosition(filePath,1);
            List<String> dates = ReaderCsv.readAtPosition(filePath, 2);
            List<String> titles = ReaderCsv.readAtPosition(filePath, 3);
            List<String> contents = ReaderCsv.readAtPosition(filePath, 4);
            GenericDAO<PressRelease,Integer> dao = new GenericDAOHibernate<PressRelease, Integer>(PressRelease.class);
            for (int  i = 0; i < feedsNames.size(); i++){
                String queryForFeed = "from Feed where name = \'" + feedsNames.get(i) + "\'";
                List feeds = dao.executeQuery(queryForFeed);
                if (feeds.size() != 1){
                    continue;
                }
                PressRelease pressRelease = new PressRelease();
                pressRelease.setFeedID((Feed) feeds.get(0));
                pressRelease.setTitle(titles.get(i));
                pressRelease.setDate(convertStringToDate(dates.get(i)));
                pressRelease.setContent(contents.get(i));
                dao.create(pressRelease);
                if (i%20 == 0){
                    dao.flushAndClear();
                }
            }
            dao.closeSession();
        }


    }
    public static void addPressReleasesTagsData(){
        String[] filesPaths = {
//                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/en_CHN_mopost_int/rss_unique_tagged.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/fr_FRA_lmonde_int/rss_unique_tagged.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/en_IND_tindia_int/rss_unique_tagged.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/es_MEX_univer_int/rss_unique_tagged.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/en_USA_nytime_int/rss_unique_tagged.csv"
        };
        for (String filePath: filesPaths) {
            List<String> titles = ReaderCsv.readAtPosition(filePath, 3);
            List<String> tags = ReaderCsv.readAtPosition(filePath, 5);
            GenericDAO<PressReleasesTag, Integer> dao = new GenericDAOHibernate<PressReleasesTag, Integer>(PressReleasesTag.class);
            for (int i = 0; i < titles.size(); i++) {
                if (tags.get(i).equals("") || titles.get(i).contains("\'")){
                    continue;
                }
                String queryForPressRelease = "from PressRelease where title = \'" + titles.get(i) + "\'";
                String queryForTag = "from TAG where name = \'" + tags.get(i) + "\'";
                List pressReleases = dao.executeQuery(queryForPressRelease);
                List tagsList = dao.executeQuery(queryForTag);
                if (pressReleases.size() != 1 || tagsList.size() != 1) {
                    continue;
                }
                PressRelease pressRelease = (PressRelease) pressReleases.get(0);
                TAG tag = (TAG) tagsList.get(0);
                PressReleasesTag pressReleasesTag = new PressReleasesTag();
                pressReleasesTag.setPressReleaseID(pressRelease);
                pressReleasesTag.setTagID(tag);
                dao.create(pressReleasesTag);
                if (i % 20 == 0) {
                    dao.flushAndClear();
                }
            }
            dao.closeSession();
        }

    }
    public static Date convertStringToDate(String date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        Date resultDate = null;
        try {
            resultDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }
}
