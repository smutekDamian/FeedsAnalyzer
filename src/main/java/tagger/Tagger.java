package tagger;

import csv.reader.ReaderCsv;
import csv.writer.WriterCsv;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by damian on 22.04.17.
 */
public class Tagger {
    public static void main(String[] args) {
        Map<String, String> tagsMap = ReaderCsv.readAtTwoPosition("/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/Dico_Country_Free.csv",
                1, 2, '\t');
        tagFile("/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/Dico_Country_Free.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/Feeds/en_CHN_mopost_int.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/Feeds/tagTest.csv");

    }

    public static void tagFile(String tagsFilePath, String sourceFilePath, String destinationFilePath){
        List<String> feeds = ReaderCsv.readAtPosition(sourceFilePath, 0, ' ');
        List<String> times = ReaderCsv.readAtPosition(sourceFilePath, 1, ' ');
        List<String> titles = ReaderCsv.readAtPosition(sourceFilePath, 2, ' ');
        List<String> descriptions = ReaderCsv.readAtPosition(sourceFilePath, 3, ' ');
        List<String> tags = ReaderCsv.readAtPosition(tagsFilePath, 1, '\t');
        List<String> tagsKeys = ReaderCsv.readAtPosition(tagsFilePath, 2, '\t');

        for (int i = 0; i < titles.size(); i++) {
            List<String> usedTags = new LinkedList<String>();
            for (int j = 0; j < tags.size(); j++){
                if (!usedTags.contains(tags.get(j))){
                    String tagKey = tagsKeys.get(j);
                    if ((titles.get(i).contains(tagKey)) || (descriptions.get(i).contains(tagKey))){
                        WriterCsv.write(destinationFilePath, feeds.get(i), times.get(i), titles.get(i), descriptions.get(i), tags.get(j));
                        usedTags.add(tags.get(j));
                    }
                }
            }
        }
    }
}
