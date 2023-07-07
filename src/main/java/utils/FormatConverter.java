package utils;

import java.io.*;

public class FormatConverter {

    public static void convertTxtToCSV(byte[] inputFileBytes, String outputFile) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputFileBytes)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split("\\s+");
                writer.write(String.join(",", values));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertCsvToTxt(byte[] inputFileBytes, String outputFile) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(inputFileBytes), "UTF-8"));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                writer.write(String.join(" ", values));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
