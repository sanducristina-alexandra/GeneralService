package handlers;

import onlineservices.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OnlineServiceHandler {
    @Autowired
    private List<OnlineService> activatedServices;

}
