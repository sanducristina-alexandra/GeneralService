package utils.filereaders;

import org.apache.logging.log4j.LogManager;
import utils.CustomFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Logger;

public class CsvFileReader implements CustomFileReader {

    private static final Logger LOGGER = LogManager.getLogger(CsvFileReader.class);

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
            LOGGER.error("An error occurred while reading the file: {}\n{}", file.getPath(), exception);
            try {
                if (file.createNewFile()) {
                    LOGGER.info("Created the file at " + file.getPath());
                }
            } catch (IOException e) {
                LOGGER.error("An error occurred while creating the file: {}\\n{}", file.getPath(), exception);
            }
        }
        return list;
    }
}
