package controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.EmulatorCommunicationService;
import service.OnlineServicesCommunicationService;
import models.Request;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class Controller {

    @Autowired
    private OnlineServicesCommunicationService onlineServicesCommunicationService;
    @Autowired
    EmulatorCommunicationService emulatorCommunicationService;

    @PostMapping("/request")
    public Boolean processRequest(@RequestBody Request request) throws MqttException {
        return onlineServicesCommunicationService.sendRequest(request);
    }

    @PostMapping("/receive_data_from_emulator")
    public String receiveDataFromEmulator(@RequestBody String data) throws Exception {
        return emulatorCommunicationService.receiveData(data);
    }
}
