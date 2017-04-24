package rss.reader;


import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import csv.writer.Feed;
import csv.writer.FeedCSVWriter;

import java.io.IOException;
import java.net.URL;

/**
 * Created by damian on 16.03.17.
 */
public class RssReader extends FeedCSVWriter {
    private SyndFeedInput input;

    public RssReader() {
        this.input = new SyndFeedInput();
    }

    public void readAndWriteToFile(Feed[] feeds){
        for (Feed feed: feeds){
            System.out.println(feed.getName() + " parsing");
            try {
                String filename = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/Feeds/" + feed.getName() +".csv";
                SyndFeed syndFeed = input.build(new XmlReader(new URL(feed.getRssUrl())));
                FeedCSVWriter.writeFeed(filename, syndFeed,feed.getName());
            } catch (FeedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
