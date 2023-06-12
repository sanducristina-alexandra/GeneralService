package GeneralService;

import onlineservices.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class OnlineServiceHandler {
    @Autowired
    private List<OnlineService> activatedServices;

    @PostConstruct
    private void activateServices() {
        activatedServices.forEach(OnlineService::onCreate);
    }

    @PreDestroy
    private void deactivateServices() {
        activatedServices.forEach(OnlineService::onDestroy);
    }
}
