package GeneralService;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class OnlineServicesCommunicationService {

    @Autowired
    private MqttClient mqttClient;

    public void sendRequest(Request request) throws MqttException {
        if (!mqttClient.isConnected())
            mqttClient.connect();

        if (Arrays.asList(RequestType.values()).contains(request.getRequestType())) {
            String message = String.valueOf(request.getRequestValue());
            mqttClient.publish(request.getRequestType().toString(), new MqttMessage(message.getBytes()));
        }
    }
}
