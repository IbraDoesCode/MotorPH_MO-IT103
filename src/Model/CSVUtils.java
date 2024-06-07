package Model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CSVUtils {

    public static List<String[]> retrieveCSVData(String filepath) throws IOException, CsvException {
        List<String[]> data = new ArrayList<String[]>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filepath))) {
            String[] row;

            while ((row = csvReader.readNext()) != null) {
                data.add(row);
            }
        }
        return data.subList(1, data.size());
    }

    public static void writeDataToCSV(String filepath, List<String[]> data, String[] headers) throws IOException, CsvException {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filepath))) {
            csvWriter.writeNext(headers);
            csvWriter.writeAll(data);
        }
    }

    public static boolean fileExists(String filepath) {
        File file = new File(filepath);
        return file.exists();
    }
}
