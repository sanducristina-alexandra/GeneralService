package utils;

import utils.filereaders.CsvFileReader;

public class CustomFileReaderFactory {
    public CustomFileReader getFileReader(String extension) {
        if (extension.equals("csv")) {
            return new CsvFileReader();
        } else {
            throw new IllegalArgumentException("The extension type is not viable.");
        }
    }
}
