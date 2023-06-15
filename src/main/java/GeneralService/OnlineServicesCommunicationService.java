package GeneralService;

import GeneralService.RequestHandlers.WindowControlRequestHandler;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineServicesCommunicationService {

    @Autowired
    WindowControlRequestHandler windowControlRequestHandler;

    public void sendRequest(Request request) throws MqttException {
        if (RequestType.WINDOW_CONTROL_SERVICE_REQUEST.equals(request.getRequestType()))
            windowControlRequestHandler.sendRequest(request);
    }
}
