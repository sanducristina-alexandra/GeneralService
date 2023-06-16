package GeneralService;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineServicesCommunicationService {

    @Autowired
    private MqttClient mqttClient;

    public void sendRequest(Request request) throws MqttException {
        if (!mqttClient.isConnected())
            mqttClient.connect();

        String message = String.valueOf(request.getRequestValue());

        if (request.getRequestType().equals(RequestType.WINDOW_CONTROL_SERVICE_REQUEST) ||
                request.getRequestType().equals(RequestType.MUSIC_CONTROL_SERVICE_REQUEST) ||
                request.getRequestType().equals(RequestType.CLIMATIZATION_SERVICE_REQUEST) ||
                request.getRequestType().equals(RequestType.TRIP_HISTORY_SERVICE_REQUEST)) {
            mqttClient.publish(request.getRequestType().toString(), new MqttMessage(message.getBytes()));
        }
    }
}
