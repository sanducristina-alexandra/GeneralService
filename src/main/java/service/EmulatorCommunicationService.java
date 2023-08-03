package service;

import models.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmulatorCommunicationService {

    @Autowired
    private MqttClient mqttClient;

    private static final Logger LOGGER = LogManager.getLogger(EmulatorCommunicationService.class.getName());

    public String receiveData(String data) throws Exception {
        String serviceName = data.split("\n")[0];

        if (!mqttClient.isConnected())
            mqttClient.connect();

        String message = data.replaceFirst(".*?\n", "");
        mqttClient.publish(Topic.WINDOW_CONTROL_SERVICE_REQUEST.toString()
                , new MqttMessage(message.getBytes()));

        return data;
    }
}
