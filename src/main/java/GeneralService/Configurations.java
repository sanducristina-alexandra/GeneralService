package GeneralService;

import onlineservices.OnlineService;
import onlineservices.services.WindowControl.WindowControlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.CustomFileReaderFactory;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Configurations {
    private static final Logger LOGGER = LogManager.getLogger(Configurations.class.getName());
    private final CustomFileReaderFactory fileReaderFactory = new CustomFileReaderFactory();

    @Bean
    public WindowControlService windowControlService() {
        return new WindowControlService();
    }

    public List<String> getActivatedServicesNames() {
        return fileReaderFactory.getFileReader("csv").readFile(new File(".\\src\\main\\resources\\ActivatedServices.csv"));
    }

    @Bean
    public List<OnlineService> activatedServices() {
        List<OnlineService> activatedServices = new ArrayList<>();
        for (String serviceName : getActivatedServicesNames()) {
            if (serviceName.equals(WindowControlService.class.getSimpleName())) {
                activatedServices.add(windowControlService());
                LOGGER.info(serviceName + " is in the activated services list.");
            } else {
                LOGGER.info(serviceName + " is not in the activated services list.");
            }
        }
        return activatedServices;
    }

    @Bean
    public List<Integer> validCarUserIds() {
        List<Integer> validCarUserIds = new ArrayList<>();
        File file = new File(".\\src\\main\\resources\\valid-car-user-ids.csv");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                LOGGER.error("An error occurred while creating the file: {}\\n{}", file.getPath(), exception);
            }
        }
        List<String> extractedValidCarUserIdsAsStrings = fileReaderFactory.getFileReader("csv").readFile(file);
        if (extractedValidCarUserIdsAsStrings.isEmpty()) {
            LOGGER.info("The list of valid IDs is empty.");
        } else {
            for (String string : extractedValidCarUserIdsAsStrings) {
                if (isInteger(string)) {
                    validCarUserIds.add(Integer.parseInt(string));
                    LOGGER.info(string + " is in the valid IDs list.");
                }
            }
        }
        return validCarUserIds;
    }

    public boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
