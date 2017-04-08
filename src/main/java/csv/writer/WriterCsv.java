package csv.writer;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by damian on 04.04.17.
 */
public class WriterCsv {
    public static void writeTagsAndCountries(String filename, Map<String,String> tagsAndCountries){
        Iterator it = tagsAndCountries.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            write(filename, (String)pair.getKey(), (String)pair.getValue());
            it.remove();
        }

    }
    private static void write(String filename, String... args ){
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(filename, true), '\t', CSVWriter.NO_QUOTE_CHARACTER);
            String[] textToWrite = new String[args.length];
            for (int i = 0; i < args.length; i++){
                textToWrite[i] = args[i];
            }
            writer.writeNext(textToWrite);
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
