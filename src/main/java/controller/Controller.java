package controller;

import database.DataBaseManager;
import database.ObjectDao;
import email.EmailSender;
import maps.MapImageGenerator;
import onlineservices.models.ClimatizationReport;
import onlineservices.models.TripReport;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ClimatizationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import service.TripReportService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@RestController
public class Controller {

    @Autowired
    private ClimatizationReportService climatizationReportService;
    @Autowired
    private TripReportService tripReportService;
    @Autowired
    private ObjectDao objectDao;

    @PostMapping("/receive_reports_from_climatization_service")
    public String receiveReportFromClimatizationService(@RequestBody ClimatizationReport report) {
        objectDao.saveClimatizationReport(report);
        return "Report received successfully!";
    }

    @PostMapping("/receive_reports_from_gps_service")
    public String receiveReportsFromGpsService(@RequestBody TripReport report) {
        objectDao.saveTripReport(report);
        return "Report received successfully!";
    }

    @PostMapping("/receive_sos_data_from_gps_service")
    public String receiveSOSData(@RequestBody Map<String, Object> sosData) {
        String lastCoordinates = (String) sosData.get("lastCoordinates");

        Long lastCoordinatesDateTimestamp = (Long) sosData.get("lastCoordinatesDate");
        Date lastCoordinatesDate = new Date(lastCoordinatesDateTimestamp);

        EmailSender.sendEmail("RaduCorneliu.Iancu@harman.com", lastCoordinates, lastCoordinatesDate.toString());
        return "SOS data received successfully.";
    }

    @GetMapping("/get_last_trip")
    public String processData() {
        List<String> lastCoordinates = objectDao.getLastCompletedTripCoordinates();
        if (lastCoordinates.size() >= 2)
            return MapImageGenerator.getDirectionsUrl(lastCoordinates);
        else
            return null;
    }

    @GetMapping("/get_last_climatization_report")
    @Async
    public String getLastClimatizationReport() throws Exception {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        CompletableFuture<String> futureReport = new CompletableFuture<>();
        executor.schedule(() -> {
            String report = objectDao.getLastClimatizationReport().toString();
            futureReport.complete(report);
        }, 6, TimeUnit.SECONDS);

        executor.shutdown();

        try {
            return futureReport.join();
        } catch (CompletionException e) {
            throw new Exception(e.getCause());
        }
    }

    @GetMapping("/get_last_trip_report")
    @Async
    public String getLastTripReport() throws Exception {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        CompletableFuture<String> futureReport = new CompletableFuture<>();
        executor.schedule(() -> {
            String report = objectDao.getLastTripReport().toString();
            futureReport.complete(report);
        }, 6, TimeUnit.SECONDS);

        executor.shutdown();

        try {
            return futureReport.join();
        } catch (CompletionException e) {
            throw new Exception(e.getCause());
        }
    }

    @PostMapping("/receive_target_temperature_from_climatization_service")
    public String receiveTemperature(@RequestBody String targetTemperature) {
        try {
            int targetTemperatureValue = Integer.parseInt(targetTemperature);
            objectDao.saveTargetTemperature(targetTemperatureValue);
            return "Recieved target temperature value: " + targetTemperatureValue;
        } catch (NumberFormatException e) {
            return "Invalid temperature value recieved.";
        }
    }

    @GetMapping("/get_target_temperature")
    public String getTargetTemperature() throws Exception{
        return String.valueOf(objectDao.getTargetTemperature());
    }

}
