package utils;

import utils.filereaders.CsvFileReader;

import java.util.HashMap;
import java.util.Map;

public class CustomFileReaderFactory {
    private static final HashMap<FileType, CustomFileReader> CUSTOM_FILE_READER = new HashMap<>(Map.of(
            FileType.CSV, new CsvFileReader()
    ));

    public static CustomFileReader getFileReader(FileType fileType) {
        CustomFileReader fileReader = CUSTOM_FILE_READER.get(fileType);
        if (fileReader != null) {
            return fileReader;
        } else {
            throw new IllegalArgumentException("The extension type is not viable.");
        }
    }
}
