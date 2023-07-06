package controller;

import service.OnlineServicesCommunicationService;
import classes.Request;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.FileService;

import java.io.IOException;

@RestController
public class Controller implements IController {

    @Autowired
    private OnlineServicesCommunicationService onlineServicesCommunicationService;
    @Autowired
    FileService fileService;

    @PostMapping("/request")
    public Boolean processRequest(@RequestBody Request request) throws MqttException {
        return onlineServicesCommunicationService.sendRequest(request);
    }

    @Override
    @PostMapping("/upload_image")
    public String uploadImage(@RequestBody byte[] imageBytes,
                              @RequestHeader("Content-Disposition") String contentDisposition) throws IOException {
        return fileService.uploadImage(imageBytes, contentDisposition);
    }

    @Override
    @DeleteMapping("/delete_image/{imageName:.+}")
    public String deleteImage(@PathVariable String imageName) throws IOException {
        return fileService.deleteImage(imageName);
    }

    @Override
    @PostMapping("/upload_csv")
    public String uploadCsv(@RequestBody byte[] fileBytes, @RequestHeader("Accept") String acceptHeader,
                            @RequestHeader("Content-Disposition") String contentDisposition) throws IOException {
        return fileService.uploadCsv(acceptHeader, fileBytes, contentDisposition);
    }
}
