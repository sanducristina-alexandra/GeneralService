package GeneralService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    OnlineServicesCommunicationService onlineServicesCommunicationService;
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
