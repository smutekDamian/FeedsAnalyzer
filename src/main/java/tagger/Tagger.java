package tagger;

import csv.reader.ReaderCsv;
import csv.writer.WriterCsv;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Tagger {
    private static final Character feedFileSeparator = ' ';
    private static final Character tagFileSeparator = '\t';
    private static final String secondFeedsFolderPaths = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB";
    private static final String firstFeedsFolderPaths = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Geomedia_extract_AGENDA";
    private static final String countryTagFile = "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/Dico_Country_Free.csv";
    public static void main(String[] args) {
        Map<String, String> tagsMap = ReaderCsv.readAtTwoPosition("/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/Dico_Country_Free.csv",
                1, 2, '\t');
        tagFileByCountry("/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Sample_GeomediaDB/Dico_Country_Free.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/Feeds/en_CHN_mopost_int.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/Feeds/tagTest.csv");
        tagFileByOrganization("/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/orgs.csv",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/1/Geomedia_extract_AGENDA",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/TaggedFeeds/OrgTag");
        tagFileByOrganization("/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/orgs_short.csv",
                firstFeedsFolderPaths,
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/TaggedFeeds/OrgTag/SHORT");
        tagFileByCountry(countryTagFile, "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/Feeds",
                "/home/damian/Pulpit/Studia 16-17/Semestr 6/IO/TaggedFeeds/NewFeeds/CountryTag" );

    }

    private static void tagFileByCountry(String tagsFilePath, String sourceFolderPath, String destinationFolderPath){
        File file = new File(sourceFolderPath);
        File[] files = file.listFiles();
        assert files != null;
        for (File f: files){
            if (f.isFile()) {
                String sourceFilePath = f.getAbsolutePath();
                String destinationFilePath = destinationFolderPath + "/" + f.getName();
                System.out.println(sourceFilePath + " is tagging");
                tagFile(tagsFilePath, sourceFilePath, destinationFilePath, feedFileSeparator, tagFileSeparator);
            }
        }



    }
    private static void tagFileByOrganization(String tagsFilePath, String sourceFolderPath, String destinationfolderPath){
        File file = new File(sourceFolderPath);
        String[] directories = file.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return new File(dir, name).isDirectory();
            }
        });
        assert directories != null;
        for (String dir: directories){
            String sourceFilePath = sourceFolderPath + "/" + dir + "/rss_unique.csv";
            String destinationFilePath = destinationfolderPath + "/" + dir + "/rss_org_tagged.csv";
            System.out.println(sourceFilePath + " is tagging");
            tagFile(tagsFilePath, sourceFilePath, destinationFilePath, feedFileSeparator, tagFileSeparator);
        }

    }
    private static void tagFile(String tagsFilePath, String sourceFilePath, String destinationFilePath,
                                Character feedFileSeparator, Character tagFileSeparator){
        List<String> feeds = ReaderCsv.readAtPosition(sourceFilePath, 0, feedFileSeparator);
        List<String> times = ReaderCsv.readAtPosition(sourceFilePath, 1, feedFileSeparator);
        List<String> titles = ReaderCsv.readAtPosition(sourceFilePath, 2, feedFileSeparator);
        List<String> descriptions = ReaderCsv.readAtPosition(sourceFilePath, 3, feedFileSeparator);
        List<String> tags = ReaderCsv.readAtPosition(tagsFilePath, 1, tagFileSeparator);
        List<String> tagsKeys = ReaderCsv.readAtPosition(tagsFilePath,  2, tagFileSeparator);

        for (int i = 0; i < titles.size(); i++) {
            List<String> usedTags = new LinkedList<String>();
            for (int j = 0; j < tags.size(); j++){
                if (!usedTags.contains(tags.get(j))){
                    String tagKey = tagsKeys.get(j);
                    try {
                        if (i < descriptions.size()) {
                            if ((titles.get(i).contains(tagKey)) || (descriptions.get(i).contains(tagKey))){
                                WriterCsv.write(destinationFilePath, feeds.get(i), times.get(i), titles.get(i), descriptions.get(i), tags.get(j));
                                usedTags.add(tags.get(j));
                            }
                        }else {
                            if ((titles.get(i).contains(tagKey))){
                                WriterCsv.write(destinationFilePath, feeds.get(i), times.get(i), titles.get(i), descriptions.get(i), tags.get(j));
                                usedTags.add(tags.get(j));
                            }
                        }
                    }catch (IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
