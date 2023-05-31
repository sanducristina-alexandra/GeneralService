package GeneralService;

import onlineservices.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.stream.Collectors;

public class OnlineServiceHandler {
    @Autowired
    private List<Service> services;

    @Autowired
    public OnlineServiceHandler(List<String> services,ConversionService conversionService) {
        this.services = services.stream()
                        .map(service->conversionService.convert(service,Service.class))
                        .collect(Collectors.toList());
    }
}
