package GeneralService;

import onlineservices.services.WindowControl.WindowControlService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

@Service
public class OnlineServicesCommunicationService {

    private static final String MQTT_BROKER_URL = "tcp://broker.emqx.io:1883";
    private static final String MQTT_CLIENT_ID = "OnlineServicesCommunicationService";
    private static final String MQTT_WINDOW_SERVICE_TOPIC = "WindowControlService";
    private MqttClient windowControlServiceClient;
    private WindowControlService windowControlService;

    public OnlineServicesCommunicationService() {
    }

    public void sendRequest(Request request) {
        if (RequestType.WINDOW_CONTROL_SERVICE_REQUEST.equals(request.getRequestType())) {
            sendWindowControlServiceRequest(request);
        }
    }

    private void sendWindowControlServiceRequest(Request request) {
        if (windowControlServiceClient == null)
            openMqttConnection();

        String message = String.valueOf(request.getRequestValue());
        try {
            if (windowControlServiceClient.isConnected()) {
                windowControlServiceClient.publish(MQTT_WINDOW_SERVICE_TOPIC, new MqttMessage(message.getBytes()));
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void openMqttConnection() {
        windowControlService = new WindowControlService();
        windowControlService.onCreate();

        try {
            windowControlServiceClient = new MqttClient(MQTT_BROKER_URL, MQTT_CLIENT_ID, new MemoryPersistence());
            windowControlServiceClient.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
