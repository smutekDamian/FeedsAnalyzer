package rss;


import csv.writer.Feed;
import rss.reader.RssReader;


/**
 * Created by damian on 16.03.17.
 */
public class Main {
    public static void main(String[] args){
        Feed[] feeds = {
                new Feed("fr_FRA_lmonde_int","http://rss.lemonde.fr/c/205/f/3052/index.rss"),
                new Feed("en_CHN_mopost_int", "http://www.scmp.com/rss/91/feed"),
//                new Feed( "en_GBR_dailyt_int","http://www.telegraph.co.uk/news/rss.xml"),
                new Feed("en_IND_tindia_int" ,"http://timesofindia.indiatimes.com/rssfeeds/1221656.cms"),
                new Feed("es_MEX_univer_int", "http://archivo.eluniversal.com.mx/rss/mundo.xml"),
                new Feed("en_USA_nytime_int", "http://rss.nytimes.com/services/xml/rss/nyt/World.xml")
        };
        RssReader reader = new RssReader();
        reader.readAndWriteToFile(feeds);
    }
}
