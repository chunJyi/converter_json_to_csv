package org.converter.writer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class CSVWriter {

    public static String getCSV(List<Map<String, String>> flatJson) {
       return getCSV(flatJson,",");
    }

    public static String getCSV(List<Map<String, String>> flatJson,String separator) {
        Set<String> headers = collectHeaders(flatJson);
        String csvString=StringUtils.join(headers,separator)+ "\n";

        for (Map<String,String> map:flatJson) {
            csvString = csvString +  getSeperatedColumns(headers, map, separator)+ "\n";
        }
        return csvString;
    }

    private static String getSeperatedColumns(Set<String> headers, Map<String, String> map, String separator) {

        List<String > items = new ArrayList<>();
        for (String header:headers) {
            String value = map.get(header) == null ? "" : map.get(header).replaceAll("[\\,\\;\\r\\n\\t\\s]+", " ");
            items.add(value);
        }
        return StringUtils.join(items.toArray(),separator);
    }
        private static Set<String> collectHeaders(List<Map<String, String>> flatJson) {
        Set<String> headers = new LinkedHashSet<>();
        for (Map<String,String> map:flatJson) {
                headers.addAll(map.keySet());
        }
        return headers;
    }

    public static Set<String> collectOrderHeaders(List<Map<String, String>> flatJson) {
            Set<String> headers = new TreeSet<String>();
            for (Map<String, String> map : flatJson) {
                headers.addAll(map.keySet());
            }
            return headers;
    }


    public static void writeToFile(String csvString, String s) {
            try {
                FileUtils.write(new File(s), csvString);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

    }

    public static void writeLargeFile(List<Map<String, String>> flatJson, String separator, String fileName, Set<String> headers){
        String csvString;
        csvString = StringUtils.join(headers.toArray(), separator) + "\n";
        File file = new File(fileName);

        try {
            // ISO8859_1 char code to Latin alphabet
            FileUtils.write(file, csvString, "ISO8859_1");

            for (Map<String, String> map : flatJson) {
                csvString = "";
                csvString = getSeperatedColumns(headers, map, separator) + "\n";
                Files.write(Paths.get(fileName), csvString.getBytes("ISO8859_1"), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
        }
    }




}
