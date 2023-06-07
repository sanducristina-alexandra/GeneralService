package utils.configurations;

import onlineservices.Service;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.WindowControl.WindowControlService;
import utils.filereaders.CsvFileReader;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Configurations {

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
            if (serviceName.equals(WindowControlService.class.getName())) {
                activatedServices.add(windowControlService());
            }
        }
        return activatedServices;
    }
}
