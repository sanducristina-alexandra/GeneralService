package services;

import models.TripReportEntity;
import onlineservices.models.TripReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repositories.TripReportRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripReportService {
    @Autowired
    private TripReportRepository tripReportRepository;

    public TripReportEntity saveTripReportEntity(TripReport entity) {
        TripReportEntity tripReportEntity = new TripReportEntity();
        tripReportEntity.setTripCoordinates(entity.getTripCoordinates());
        tripReportEntity.setStartTripDate(entity.getStartTripDate());
        tripReportEntity.setEndTripDate(entity.getEndTripDate());
        tripReportEntity.setStatus(entity.getStatus().toString());
        tripReportEntity.setSosEmailSent(entity.isSosEmailSent());
        return tripReportRepository.save(tripReportEntity);
    }

    public List<String> getLastCompletedTripCoordinates() {
        Pageable pageable = PageRequest.of(0, 1);
        List<TripReportEntity> tripReports = tripReportRepository.findLastCompletedTrip(pageable);

        if (tripReports.isEmpty()) {
            return null;
        }

        TripReportEntity lastTrip = tripReports.get(0);
        List<String> lastCoordinates = lastTrip.getTripCoordinates();
        List<String> formattedCoordinates = new ArrayList<>();
        for (int i = 0; i < lastCoordinates.size(); i += 2) {
            if (i + 1 < lastCoordinates.size()) {
                String lat = lastCoordinates.get(i).trim();
                String lng = lastCoordinates.get(i + 1).trim();
                formattedCoordinates.add(lat + "," + lng);
            }
        }
        return formattedCoordinates;
    }

    public TripReportEntity getLastTripReport() {
        Pageable pageable = PageRequest.of(0, 1);
        List<TripReportEntity> reports = tripReportRepository.findLastTripReport(pageable);

        if (reports.isEmpty()) {
            return null;
        }

        TripReportEntity reportEntity = reports.get(0);
        TripReportEntity report = new TripReportEntity();
        report.setId(reportEntity.getId());
        report.setStartTripDate(reportEntity.getStartTripDate());
        report.setEndTripDate(reportEntity.getEndTripDate());
        report.setStatus(reportEntity.getStatus());
        report.setSosEmailSent(reportEntity.isSosEmailSent());
        report.setTripCoordinates(reportEntity.getTripCoordinates());
        return report;
    }
}
