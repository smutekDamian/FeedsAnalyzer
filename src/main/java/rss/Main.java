package rss;


import csv.writer.DownloadedFeed;
import rss.reader.RssReader;


/**
 * Created by damian on 16.03.17.
 */
public class Main {
    public static void main(String[] args){
        DownloadedFeed[] downloadedFeeds = {
                new DownloadedFeed("fr_FRA_lmonde_int","http://rss.lemonde.fr/c/205/f/3052/index.rss"),
                new DownloadedFeed("en_CHN_mopost_int", "http://www.scmp.com/rss/91/feed"),
//                new DownloadedFeed( "en_GBR_dailyt_int","http://www.telegraph.co.uk/news/rss.xml"), !
                new DownloadedFeed("en_IND_tindia_int" ,"http://timesofindia.indiatimes.com/rssfeeds/1221656.cms"),
                new DownloadedFeed("es_MEX_univer_int", "http://archivo.eluniversal.com.mx/rss/mundo.xml"),
                new DownloadedFeed("en_USA_nytime_int", "http://rss.nytimes.com/services/xml/rss/nyt/World.xml"),
//                new DownloadedFeed("en_AUS_hersun_int", "http://www.heraldsun.com.au/news/world/rss"), !
                new DownloadedFeed("en_CAN_starca_int", "http://www.thestar.com/feeds.articles.news.world.rss"),
//                new DownloadedFeed("en_CHN_chinad_int", "http://www.chinadaily.com.cn/rss/world_rss.xml"), !
                new DownloadedFeed("en_IND_hindti_int", "http://www.hindustantimes.com/rss/world/rssfeed.xml"),
//                new DownloadedFeed("en_MLT_tmalta_int", "https://www.timesofmalta.com/rss/news"),
//                new DownloadedFeed("en_MYS_starmy_int", "http://www.thestar.com.my/rss/news/world/"), !
                new DownloadedFeed("en_NZL_nzhera_int", "http://rss.nzherald.co.nz/rss/xml/nzhrsscid_001503711.xml"),
                new DownloadedFeed("en_SGP_twoday_int", "http://www.todayonline.com/feed/world"),
//                new DownloadedFeed("en_USA_wapost_int", "http://feeds.washingtonpost.com/rss/world"), !
                new DownloadedFeed("en_ZWE_chroni_int", "http://www.chronicle.co.zw/feed/"),
//                new DownloadedFeed("es_ARG_nacion_int", "http://contenidos.lanacion.com.ar/herramientas/rss-origen=2"), !
//                new DownloadedFeed("es_BOL_larazo_int", "http://www.la-razon.com/rss/latest/?contentType=NWS"), !
                new DownloadedFeed("es_BOL_patria_int", "http://lapatriaenlinea.com/rss/Internacional.xml"),
                new DownloadedFeed("es_ESP_catalu_int", "http://www.elperiodico.com/es/rss/internacional/rss.xml"),
                new DownloadedFeed("es_ESP_elpais_int", "http://ep00.epimg.net/rss/internacional/portada.xml"),
                new DownloadedFeed("fr_BEL_derheu_int", "http://www.dhnet.be/rss/section/actu.xml"),
                new DownloadedFeed("fr_BEL_lesoir_int", "http://www.lesoir.be/feed/Actualit%C3%A9/Fil%20Info/destination_principale_block"),
                new DownloadedFeed("fr_CAN_jmontr_int", "http://www.journaldemontreal.com/actualite/rss.xml"),
                new DownloadedFeed("fr_DZA_elwata_int", "http://www.elwatan.com/international/rss.xml"),
//                new DownloadedFeed("fr_DZA_xpress_int", "http://www.lexpressiondz.com/feed/internationale/index.1.rss")!
        };
        RssReader reader = new RssReader();
        reader.readAndWriteToFile(downloadedFeeds);
    }
}
