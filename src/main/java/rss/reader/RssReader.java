package rss.reader;


import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import csv.writer.DownloadedFeed;
import csv.writer.FeedCSVWriter;

import java.io.IOException;
import java.net.URL;


public class RssReader extends FeedCSVWriter {
    private final SyndFeedInput input;

    public RssReader() {
        this.input = new SyndFeedInput();
    }

    public void readAndWriteToFile(DownloadedFeed[] downloadedFeeds){
        for (DownloadedFeed downloadedFeed : downloadedFeeds){
            System.out.println(downloadedFeed.getName() + " parsing");
            try {
                String filename = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/Feeds/" + downloadedFeed.getName() +".csv";
                SyndFeed syndFeed = input.build(new XmlReader(new URL(downloadedFeed.getRssUrl())));
                FeedCSVWriter.writeFeed(filename, syndFeed, downloadedFeed.getName());
            } catch (FeedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
