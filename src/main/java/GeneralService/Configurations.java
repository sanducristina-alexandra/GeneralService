package GeneralService;

import onlineservices.OnlineService;
import onlineservices.services.WindowControl.WindowControlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.filereaders.CsvFileReader;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Configurations {
    private static final Logger LOGGER = LogManager.getLogger(Configurations.class.getName());


    @Bean
    public WindowControlService windowControlService() {
        return new WindowControlService();
    }

    public List<String> getActivatedServicesNames() {
        return new CsvFileReader().readFile(new File(".\\src\\main\\resources\\ActivatedServices.csv"));
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
        List<String> validCarUserIdsString = new CsvFileReader().readFile(file);
        if (validCarUserIdsString.isEmpty()) {
            LOGGER.info("The list of valid IDs is empty.");
        } else {
            for (String string : validCarUserIdsString) {
                validCarUserIds.add(Integer.parseInt(string));
                LOGGER.info(string + " is in the valid IDs list.");
            }
        }
        return validCarUserIds;
    }
}
