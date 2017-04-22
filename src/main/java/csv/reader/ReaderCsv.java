package csv.reader;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by damian on 04.04.17.
 */
public class ReaderCsv {

    public static Map<String,String> readTwoFilesAndReturnTagsWithCountries(String tagsFilePath, String countriesFilePath){
        Map<String,String> result = new HashMap<String, String>();
        List<String> countries = readAtPosition(countriesFilePath, 1, ' ');
        for (String country: countries){
            String foundTag = findTagFittingToCountry(country, tagsFilePath);
            if (foundTag != null){
                result.put(foundTag, country);
            }
        }
        return result;
    }

    public static List<String> readAtPosition(String filepath, int position, char separator){
        List<String> result = null;
        try {
            CSVReader reader = new CSVReader(new FileReader(filepath), separator);
            result = new ArrayList<String>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    result.add(nextLine[position]);
                } catch (ArrayIndexOutOfBoundsException e){
                    continue;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Map<String, String> readAtTwoPosition(String filePath, int firstPosition, int secondPosition, char separator){
        Map<String, String> result = null;
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath), separator);
            result = new HashMap<String, String>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                result.put(nextLine[firstPosition], nextLine[secondPosition]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String findTagFittingToCountry(String country, String filepath){
        try {
            CSVReader reader = new CSVReader(new FileReader(filepath), '\t');
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[2].equals(country)){
                    return nextLine[1];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
