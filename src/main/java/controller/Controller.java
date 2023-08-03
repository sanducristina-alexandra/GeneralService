package controller;

import database.ReportDao;
import email.EmailSender;
import onlineservices.models.ClimatizationReport;
import onlineservices.models.TripReport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ClimatizationReportService;
import service.EmulatorCommunicationService;
import service.OnlineServicesCommunicationService;
import models.Request;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import service.TripReportService;

import java.util.Date;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private OnlineServicesCommunicationService onlineServicesCommunicationService;
    @Autowired
    private EmulatorCommunicationService emulatorCommunicationService;
    @Autowired
    private ClimatizationReportService climatizationReportService;
    @Autowired
    private TripReportService tripReportService;
    @Autowired
    private ReportDao reportDao;

    @PostMapping("/request")
    public Boolean processRequest(@RequestBody Request request) throws MqttException {
        return onlineServicesCommunicationService.sendRequest(request);
    }

    @PostMapping("/receive_data_from_emulator")
    public String receiveDataFromEmulator(@RequestBody String data) throws Exception {
        return emulatorCommunicationService.receiveData(data);
    }

    @PostMapping("/receive_reports_from_climatization_service")
    public String receiveReportFromClimatizationService(@RequestBody ClimatizationReport report) {
        reportDao.saveClimatizationReport(report);
        return "Report received successfully!";
    }

    @PostMapping("/receive_reports_from_gps_service")
    public String receiveReportsFromGpsService(@RequestBody TripReport report) throws Exception {
        reportDao.saveTripReport(report);
        return "Report received successfully!";
    }

    @PostMapping("/receive_sos_data_from_gps_service")
    public String receiveSOSData(@RequestBody Map<String, Object> sosData) {
        String lastCoordinates = (String) sosData.get("lastCoordinates");

        // Get the timestamp as a Long and create a Date object
        Long lastCoordinatesDateTimestamp = (Long) sosData.get("lastCoordinatesDate");
        Date lastCoordinatesDate = new Date(lastCoordinatesDateTimestamp);

        String emailBody = "Hello," +
                "\n" +
                "Your car requires urgent assistance." +
                "\n" +
                "Last known location: " + lastCoordinates +
                "\n" +
                "Last report received at:" + lastCoordinatesDate +
                "\n" +
                "Kind regards," +
                "\n" +
                "Car.";
        EmailSender.sendEmail("RaduCorneliu.Iancu@harman.com", "SOS", emailBody);
        System.out.println("Received SOS data:");
        System.out.println("Last coordinates: " + lastCoordinates);
        System.out.println("Last coordinates date: " + lastCoordinatesDate);
        return "SOS data received successfully.";
    }

}
