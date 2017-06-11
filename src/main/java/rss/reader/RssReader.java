package rss.reader;


import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import download.DownloadedFeed;
import download.FeedWriter;

import java.io.IOException;
import java.net.URL;


public class RssReader extends FeedWriter {
    private final SyndFeedInput input;

    public RssReader() {
        this.input = new SyndFeedInput();
    }

    public void readAndWriteToFile(DownloadedFeed[] downloadedFeeds){
        for (DownloadedFeed downloadedFeed : downloadedFeeds){
            System.out.println(downloadedFeed.getName() + " parsing");
            try {
                SyndFeed syndFeed = input.build(new XmlReader(new URL(downloadedFeed.getRssUrl())));
                downloadedFeed.setSyndFeed(syndFeed);
            } catch (FeedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
