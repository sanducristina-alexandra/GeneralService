package models;

import utils.ListToStringConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trip_report")
public class TripReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "trip_coordinates", nullable = false)
    @Convert(converter = ListToStringConverter.class)
    private List<String> tripCoordinates;
    @Column(name = "start_trip_date", nullable = false)
    private Date startTripDate;
    @Column(name = "end_trip_date", nullable = false)
    private Date endTripDate;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "sos_email_sent", nullable = false)
    private boolean sosEmailSent;

    public TripReportEntity() {
    }

    public List<String> getTripCoordinates() {
        return tripCoordinates;
    }

    public void setTripCoordinates(List<String> tripCoordinates) {
        this.tripCoordinates = tripCoordinates;
    }

    public Date getStartTripDate() {
        return startTripDate;
    }

    public void setStartTripDate(Date startTripDate) {
        this.startTripDate = startTripDate;
    }

    public Date getEndTripDate() {
        return endTripDate;
    }

    public void setEndTripDate(Date endTripDate) {
        this.endTripDate = endTripDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSosEmailSent() {
        return sosEmailSent;
    }

    public void setSosEmailSent(boolean sosEmailSent) {
        this.sosEmailSent = sosEmailSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ReportId: " + id + "\n" +
                " status: " + status + "\n" +
                " startTripDate: " + startTripDate + "\n" +
                " endTripDate: " + endTripDate + "\n" +
                " coordinates: " + tripCoordinates + "\n" +
                " sosEmailSent: " + sosEmailSent;
    }
}
