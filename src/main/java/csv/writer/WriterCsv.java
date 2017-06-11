package csv.writer;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class WriterCsv {
    public static void writeTagsAndCountries(String filename, Map<String,String> tagsAndCountries){
        Iterator it = tagsAndCountries.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            write(filename, (String)pair.getKey(), (String)pair.getValue());
            it.remove();
        }

    }
    public static void write(String filename, String... args ){
        CSVWriter writer;
        try {
            File file = new File(filename);
            File parentDir = file.getParentFile();
            if (parentDir != null){
                parentDir.mkdirs();
            }
            writer = new CSVWriter(new FileWriter(filename, true), '\t', CSVWriter.DEFAULT_QUOTE_CHARACTER);
            String[] textToWrite = new String[args.length];
            System.arraycopy(args, 0, textToWrite, 0, args.length);
            writer.writeNext(textToWrite);
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}
