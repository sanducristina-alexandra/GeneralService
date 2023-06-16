package GeneralService;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    OnlineServicesCommunicationService onlineServicesCommunicationService;
    public List<String> validCarUserIds = new ArrayList<>(Arrays.asList("50075", "15080"));

    @PostMapping("/request")
    public Boolean processRequest(@RequestBody Request request) throws MqttException {
       if(validCarUserIds.contains(request.getUserId())){
           onlineServicesCommunicationService.sendRequest(request);
           return true;
       };
       return false;
    }

}
