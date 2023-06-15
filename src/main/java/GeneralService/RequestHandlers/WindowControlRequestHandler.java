package GeneralService.RequestHandlers;

import GeneralService.Request;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WindowControlRequestHandler implements RequestHandler {

    private static final String MQTT_WINDOW_SERVICE_TOPIC = "WindowControlService";
    @Autowired
    private MqttClient mqttClient;

    public WindowControlRequestHandler() {
    }

    @Override
    public void sendRequest(Request request) throws MqttException {
        if (!mqttClient.isConnected())
            mqttClient.connect();

        String message = String.valueOf(request.getRequestValue());
        mqttClient.publish(MQTT_WINDOW_SERVICE_TOPIC, new MqttMessage(message.getBytes()));
    }

}
