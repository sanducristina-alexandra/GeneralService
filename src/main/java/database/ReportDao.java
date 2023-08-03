package database;

import onlineservices.models.ClimatizationReport;
import onlineservices.models.TripReport;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

@Component
public class ReportDao {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String DATABASE_URL = "jdbc:sqlite:GeneralService.db";

    public void saveClimatizationReport(ClimatizationReport report) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO ClimatizationReports (Id, Date, Power, ActionCode) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setShort(1, report.getId());
            preparedStatement.setString(2, DATE_FORMAT.format(report.getDate()));
            preparedStatement.setShort(3, report.getPower());
            preparedStatement.setShort(4, report.getActionCode());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTripReport(TripReport report) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO TripReports (StartTripDate, EndTripDate, Status, SosEmailSent, TripCoordinates) " +
                             "VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, DATE_FORMAT.format(report.getStartTripDate()));
            preparedStatement.setString(2, DATE_FORMAT.format(report.getEndTripDate()));
            preparedStatement.setString(3, report.getStatus().name());
            preparedStatement.setBoolean(4, report.isSosEmailSent());
            preparedStatement.setString(5, String.join(",", report.getTripCoordinates()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
