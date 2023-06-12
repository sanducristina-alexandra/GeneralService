package GeneralService;

import onlineservices.OnlineService;
import onlineservices.services.WindowControl.WindowControlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import utils.filereaders.CsvFileReader;


import java.io.File;
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
        CsvFileReader fileReader = new CsvFileReader();
        return fileReader.readFile(new File(".\\src\\main\\resources\\ActivatedServices.csv"));
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
}