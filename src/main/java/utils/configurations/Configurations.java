package utils.configurations;

import com.google.common.collect.ImmutableMap;
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
    private final ImmutableMap<String, Service> allServices = ImmutableMap.of(
            WindowControlService.class.getName(), new WindowControlService()
    );

    public List<String> getActivatedServicesNames() {
        CsvFileReader fileReader = new CsvFileReader();
        return fileReader.readFile(new File(".\\src\\main\\resources\\ActivatedServices.csv"));
    }

    @Bean(name = "activeServices")
    public List<Service> getActivatedServices() {
        List<Service> activatedServices = new ArrayList<>();
        List<String> activatesServicesNames = getActivatedServicesNames();
        for (String serviceName : activatesServicesNames) {
            Service activatedService = allServices.get(serviceName);
            activatedServices.add(activatedService);
        }
        return activatedServices;
    }
}
