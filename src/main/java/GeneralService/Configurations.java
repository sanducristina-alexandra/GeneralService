package GeneralService;

import onlineservices.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.WindowControl.WindowControlService;
import utils.filereaders.CsvFileReader;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Configurations {
    private static final Logger LOGGER = LogManager.getLogger(Configurations.class.getName());
    @Autowired
    private WindowControlService windowControlService;
    @Bean
    public WindowControlService windowControlService() {
        return new WindowControlService();
    }

    public List<String> getActivatedServicesNames() {
        CsvFileReader fileReader = new CsvFileReader();
        return fileReader.readFile(new File(".\\src\\main\\resources\\ActivatedServices.csv"));
    }

    @Bean
    public List<Service> activatedServices() {
        List<Service> activatedServices = new ArrayList<>();
        List<String> activatedServicesNames = getActivatedServicesNames();
        for (String serviceName : activatedServicesNames) {
            if (serviceName.equals(WindowControlService.class.getSimpleName())) {
                activatedServices.add(windowControlService);
                LOGGER.info(serviceName + " is in the activated services list.");
            } else {
                LOGGER.info(serviceName + " is not in the activated services list.");
            }
        }
        return activatedServices;
    }
}
