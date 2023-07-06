package service;

import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import utils.ConvertTxtToCsv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private static final Tika TIKA = new Tika();


    public String uploadImage(byte[] imageBytes, String contentDisposition) throws IOException {
        String fileType = TIKA.detect(imageBytes);
        if (!fileType.startsWith("image/")) {
            return "Invalid file format";
        }
        String fileName = contentDisposition.substring(contentDisposition.lastIndexOf("=") + 1).trim();
        saveFileFromBytes(fileName, imageBytes);
        return "Image uploaded successfully";
    }

    public String deleteImage(String imageName) throws IOException {
        Path path = Paths.get(getFileDestination(imageName));
        System.out.println(path);
        boolean isDeleted = Files.deleteIfExists(path);
        if (isDeleted) {
            return "Image deleted successfully";
        } else {
            return "Image not found";
        }
    }

    public String uploadCsv(String acceptHeader, byte[] fileBytes, String contentDisposition) throws IOException {
        if (acceptHeader != null && acceptHeader.contains("text/csv")) {
            String fileName = contentDisposition.substring(contentDisposition.lastIndexOf("=") + 1).trim();
            saveFileFromBytes(fileName, fileBytes);
            return "CSV file uploaded successfully";
        } else {
            if (acceptHeader != null && acceptHeader.contains("text/plain")) {
                ConvertTxtToCsv.convertTxtToCSV(fileBytes, getFileDestination("test_convert.csv"));
                return "Text file converted to CSV and uploaded successfully";
            }
        }
        return "Invalid file format";
    }

    private String getFileDestination(String fileName) throws IOException {
        String fileFormat = fileName.substring(fileName.length() - 3);
        String relativePath = "/src/main/resources/" + fileFormat + "/" + fileName;
        return ROOT_DIRECTORY + relativePath;
    }

    private void saveFileFromBytes(String fileName, byte[] fileContent) throws IOException {
        Path directoryPath = Paths.get(getFileDestination(fileName)).getParent();
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }
        Path filePath = Paths.get(getFileDestination(fileName));
        Files.write(filePath, fileContent);
    }
}