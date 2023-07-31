package service;

import models.Request;
import models.Topic;
import onlineservices.OnlineService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OnlineServicesCommunicationService {

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    public List<String> validCarUserIds;

    @Autowired
    public List<OnlineService> activatedServices;

    public boolean sendRequest(Request request) throws MqttException {
        if (!validCarUserIds.contains(request.getUserId())) return false;

        if (!mqttClient.isConnected())
            mqttClient.connect();

        if (Arrays.asList(Topic.values()).contains(request.getTopic())) {
            String message = String.valueOf(request.getRequestValue());
            mqttClient.publish(request.getTopic().toString(), new MqttMessage(message.getBytes()));
            return true;
        } else {
            return false;
        }
    }
}
