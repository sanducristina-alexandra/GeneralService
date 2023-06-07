package GeneralService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.filereaders.CsvFileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvFileReaderTest {
    private final CsvFileReader csvFileReader = new CsvFileReader();

    @Test
    void correctTest() throws IOException {
        Path filePath = Files.createTempFile("test", ".csv");
        String fileData = "1,2,3,4,5,6,7,wddw,qwd";
        Files.write(filePath, fileData.getBytes(), StandardOpenOption.WRITE);
        List<String> list = csvFileReader.readFile(new File(filePath.toUri()));
        List<String> listExpected = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "wddw", "qwd"));
        Assertions.assertEquals(listExpected, list);
        Files.delete(filePath);
    }

}
