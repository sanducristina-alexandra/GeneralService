package GeneralService;

import onlineservices.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class OnlineServiceHandler {
    @Autowired
    private List<Service> activatedServices;
}
