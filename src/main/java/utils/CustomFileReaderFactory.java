package utils;

import com.google.common.collect.ImmutableMap;
import utils.filereaders.CsvFileReader;

public class CustomFileReaderFactory {
    private static final ImmutableMap<FileType, CustomFileReader> CUSTOM_FILE_READER = ImmutableMap.<FileType, CustomFileReader>builder()
            .put(FileType.CSV, new CsvFileReader())
            .build();

    public static CustomFileReader getFileReader(FileType fileType) {
        CustomFileReader fileReader = CUSTOM_FILE_READER.get(fileType);
        if (fileReader != null) {
            return fileReader;
        } else {
            throw new IllegalArgumentException("The extension type is not viable.");
        }
    }
}
