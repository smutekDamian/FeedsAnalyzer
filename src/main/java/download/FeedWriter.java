package download;

import au.com.bytecode.opencsv.CSVWriter;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class FeedWriter {

    public static void writeFeeds(DownloadedFeed[] downloadedFeeds){
        for (DownloadedFeed feed: downloadedFeeds){
            String filename = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/Feeds/" + feed.getName() +".csv";
            writeFeed(filename, feed.getSyndFeed(), feed.getName());
        }
    }

    protected static void writeFeed(String filename, SyndFeed syndFeed, String feedTitle){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(filename, true), ' ');
            List entries = syndFeed.getEntries();
            for (Object entry1 : entries) {
                final SyndEntry entry = (SyndEntry) entry1;
                writer.writeNext((feedTitle + "#" + dateFormat.format(entry.getPublishedDate()) + "#" + entry.getTitle() +
                        "#" + entry.getDescription().getValue()).split("#"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
