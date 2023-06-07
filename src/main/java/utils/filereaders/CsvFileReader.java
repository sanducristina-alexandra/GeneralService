package utils.filereaders;

import utils.CustomFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFileReader implements CustomFileReader {

    @Override
    public List<String> readFile(File file) {
        List<String> list = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                list.addAll(Arrays.asList(info));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return list;
    }
}
