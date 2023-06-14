package GeneralService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@RestController
public class Controller {

    OnlineServicesCommunicationService onlineServicesCommunicationService = new OnlineServicesCommunicationService();
    public List<String> validCarUserIds = new ArrayList<>(Arrays.asList("50075", "15080"));

    @PostMapping("/request")
    public Boolean processRequest(@RequestBody Request request) {
       if(validCarUserIds.contains(request.getUserId())){
           onlineServicesCommunicationService.sendRequest(request);
           return true;
       };
       return false;
    }

}
