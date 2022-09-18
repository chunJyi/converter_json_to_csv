package org.converter;

import org.converter.flatter.Flatter;
import org.converter.writer.CSVWriter;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        /*
         *  Parse a JSON String and convert it to CSV
         */
//        List<Map<String, String>> flatJson = Flatter.jsonFlatter(jsonString());
//        String csvString = CSVWriter.getCSV(flatJson);
//        CSVWriter.writeToFile(csvString, "files/sample_string.csv");



//        /*
//         *  Parse a JSON File and convert it to CSV
//         */
//        List<Map<String, String>> flatJson = Flatter.parseJson(new File("files/simple.json"), "UTF-8");
//        String csvString = CSVWriter.getCSV(flatJson);
//        CSVWriter.writeToFile(csvString, "files/sample.csv");

        /*
         *  Parse a Large JSON File and convert it to CSV
         */
//        List<Map<String, String>> flatJson = Flatter.parseJson(new File("files/large-file.json"), "UTF-8");
//        Set<String> header = CSVWriter.collectOrderHeaders(flatJson);
//        CSVWriter.writeLargeFile(flatJson, ";", "files/sample_largeFile.csv", header);
    }





    private static String jsonString() {
        return  "[" +
                "    {" +
                "        \"studentName\": \"Foo\"," +
                "        \"Age\": \"12\"," +
                "        \"subjects\": [" +
                "            {" +
                "                \"name\": \"English\"," +
                "                \"marks\": \"40\"" +
                "            }," +
                "            {" +
                "                \"name\": \"History\"," +
                "                \"marks\": \"50\"" +
                "            }" +
                "        ]" +
                "    }," +
                "    {" +
                "        \"studentName\": \"Aee\"," +
                "        \"Age\": \"12\"," +
                "        \"subjects\": [" +
                "            {" +
                "                \"name\": \"English\"," +
                "                \"marks\": \"50\"" +
                "            }," +
                "            {" +
                "                \"name\": \"History\"," +
                "                \"marks\": \"55\"" +
                "            }" +
                "        ]" +
                "    }" +
                "]";
    }
}
