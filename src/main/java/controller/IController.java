package controller;

import models.Request;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public interface IController {

    @PostMapping("/request")
    Boolean processRequest(@RequestBody Request request) throws MqttException;

    @PostMapping("/upload_image")
    public String uploadImage(@RequestBody byte[] imageBytes,
                              @RequestHeader("Content-Disposition") String contentDisposition) throws IOException;

    @DeleteMapping("/delete_image/{imageName:.+}")
    public String deleteImage(@PathVariable String imageName) throws IOException;

    @PostMapping("/upload_csv")
    String uploadCsv(@RequestBody byte[] fileBytes, @RequestHeader("Accept") String acceptHeader,
                     @RequestHeader("Content-Disposition") String contentDisposition) throws IOException;

    @DeleteMapping("/delete_csv/{csvName:.+}")
    public String deleteCsv(@PathVariable String csvName) throws IOException;

    @PostMapping("/upload_txt")
    String uploadTxt(@RequestBody byte[] fileBytes, @RequestHeader("Accept") String acceptHeader,
                     @RequestHeader("Content-Disposition") String contentDisposition) throws IOException;

    @DeleteMapping("/delete_txt/{txtName:.+}")
    public String deleteTxt(@PathVariable String txtName) throws IOException;

    @PostMapping("/receive_data_from_emulator")
    public String receiveDataFromEmulator(@RequestBody String data);
}
