package GeneralService;

import onlineservices.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@org.springframework.stereotype.Service
public class OnlineServiceHandler {
    @Autowired
    @Qualifier("activeServices")
    private List<Service> services;

}
