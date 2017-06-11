package csv.reader;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReaderCsvFiles {

    private ReaderCsvFiles() {
    }

    public static Map<String,String> readTwoFilesAndReturnTagsWithCountries(String tagsFilePath, String countriesFilePath){
        Map<String,String> result = new HashMap<String, String>();
        try {
            List<String> countries = readAtPosition(countriesFilePath, 1, ' ');
            for (String country: countries){
                String foundTag = findTagFittingToCountry(country, tagsFilePath);
                if (foundTag != null){
                    result.put(foundTag, country);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> readAtPosition(String filepath, int position, char separator) throws IOException {
        List<String> result = null;
        FileReader fileReader = new FileReader(filepath);
        try {
            CSVReader reader = new CSVReader(fileReader, separator);
            result = new ArrayList<String>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    result.add(nextLine[position]);
                } catch (ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileReader.close();
        }
        return result;
    }

    public static Map<String, String> readAtTwoPosition(String filePath, int firstPosition, int secondPosition, char separator) throws IOException {
        Map<String, String> result = null;
        FileReader fileReader = new FileReader(filePath);
        try {
            CSVReader reader = new CSVReader(fileReader, separator);
            result = new HashMap<String, String>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                result.put(nextLine[firstPosition], nextLine[secondPosition]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileReader.close();
        }
        return result;
    }

    private static String findTagFittingToCountry(String country, String filePath) throws IOException {
        FileReader fileReader = new FileReader(filePath);
        try {
            CSVReader reader = new CSVReader(fileReader, '\t');
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
        }finally {
            fileReader.close();
        }
        return null;
    }
}
