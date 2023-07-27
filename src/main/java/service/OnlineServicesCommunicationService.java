package service;

import models.Request;
import models.RequestType;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OnlineServicesCommunicationService {

    @Autowired
    private MqttClient mqttClient;

    public List<String> validCarUserIds = new ArrayList<>(Arrays.asList("50075", "15080"));

    public boolean sendRequest(Request request) throws MqttException {
        if (!validCarUserIds.contains(request.getUserId())) return false;

        if (!mqttClient.isConnected())
            mqttClient.connect();

        if (Arrays.asList(RequestType.values()).contains(request.getRequestType())) {
            String message = String.valueOf(request.getRequestValue());
            mqttClient.publish(request.getRequestType().toString(), new MqttMessage(message.getBytes()));
            return true;
        } else {
            return false;
        }
    }
}
