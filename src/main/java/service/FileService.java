package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import utils.FormatConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private static final Tika TIKA = new Tika();

    private static final Logger LOGGER = LogManager.getLogger(FileService.class.getName());

    public String uploadImage(byte[] imageBytes, String contentDisposition) throws IOException {
        String fileType = TIKA.detect(imageBytes);
        if (!fileType.startsWith("image/")) {
            LOGGER.info("Invalid file format");
            return "Invalid file format";
        }
        String fileName = contentDisposition.substring(contentDisposition.lastIndexOf("=") + 1).trim();
        saveFileFromBytes(fileName, imageBytes);
        LOGGER.info("Image uploaded successfully");
        return "Image uploaded successfully";
    }

    public String deleteImage(String imageName) throws IOException {
        Path path = Paths.get(getFileDestination(imageName));
        boolean isDeleted = Files.deleteIfExists(path);
        if (isDeleted) {
            LOGGER.info("Image deleted successfully");
            return "Image deleted successfully";
        } else {
            LOGGER.info("Image not found");
            return "Image not found";
        }
    }

    public String uploadCsv(String acceptHeader, byte[] fileBytes, String contentDisposition) throws IOException {
        if (acceptHeader != null && acceptHeader.contains("text/csv")) {
            String fileName = contentDisposition.substring(contentDisposition.lastIndexOf("=") + 1).trim();
            saveFileFromBytes(fileName, fileBytes);
            LOGGER.info("CSV file uploaded successfully");
            return "CSV file uploaded successfully";
        } else {
            if (acceptHeader != null && acceptHeader.contains("text/plain")) {
                String fileName = contentDisposition.substring(contentDisposition.lastIndexOf("=") + 1).trim()
                        .replace(".txt", ".csv");
                FormatConverter.convertTxtToCSV(fileBytes, getFileDestination(fileName));
                LOGGER.info("Text file converted to CSV and uploaded successfully");
                return "Text file converted to CSV and uploaded successfully";
            }
        }
        LOGGER.info("Invalid file format");
        return "Invalid file format";
    }

    private String getFileDestination(String fileName) {
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

    public String deleteCsv(String csvName) throws IOException {
        Path path = Paths.get(getFileDestination(csvName));
        boolean isDeleted = Files.deleteIfExists(path);
        if (isDeleted) {
            LOGGER.info("Csv file deleted successfully");
            return "Csv file deleted successfully";
        } else {
            LOGGER.info("Csv file not found");
            return "Csv file not found";
        }
    }

    public String uploadTxt(String acceptHeader, byte[] fileBytes, String contentDisposition) throws IOException {
        if (acceptHeader != null && acceptHeader.contains("text/plain")) {
            String fileName = contentDisposition.substring(contentDisposition.lastIndexOf("=") + 1).trim();
            saveFileFromBytes(fileName, fileBytes);
            LOGGER.info("Text file uploaded successfully");
            return "Text file uploaded successfully";
        } else {
            String fileName = contentDisposition.substring(contentDisposition.lastIndexOf("=") + 1).trim()
                    .replace(".csv", ".txt");
            FormatConverter.convertCsvToTxt(fileBytes, getFileDestination(fileName));
            LOGGER.info("CSV file converted to text file and uploaded successfully");
            return "CSV file converted to text file and uploaded successfully";
        }
    }

    public String deleteTxt(String txtName) throws IOException {
        Path path = Paths.get(getFileDestination(txtName));
        boolean isDeleted = Files.deleteIfExists(path);
        if (isDeleted) {
            LOGGER.info("Text file deleted successfully");
            return "Text file deleted successfully";
        } else {
            LOGGER.info("Text file not found");
            return "Text file not found";
        }
    }
}