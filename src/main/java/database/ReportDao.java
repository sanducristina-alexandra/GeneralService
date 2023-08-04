package database;

import onlineservices.models.ClimatizationReport;
import onlineservices.models.Status;
import onlineservices.models.TripReport;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<String> getLastCompletedTripCoordinates() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT TripCoordinates FROM TripReports WHERE Status = 'COMPLETE' ORDER BY Id DESC LIMIT 1")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                List<String> lastCoordinates =
                        Arrays.asList(resultSet.getString("TripCoordinates").split(","));

                ArrayList<String> formattedCoordinates = new ArrayList<>();
                for(int i=0; i<lastCoordinates.size(); i+=2) {
                    String lat = lastCoordinates.get(i).trim();
                    String lng = lastCoordinates.get(i+1).trim();
                    formattedCoordinates.add(lat + "," + lng);
                }
                return formattedCoordinates;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public TripReport getLastTripReport() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM TripReports ORDER BY Id DESC LIMIT 1")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                TripReport report = new TripReport();
                report.setStartTripDate(DATE_FORMAT.parse(resultSet.getString("StartTripDate")));
                report.setEndTripDate(DATE_FORMAT.parse(resultSet.getString("EndTripDate")));
                report.setStatus(Status.valueOf(resultSet.getString("Status")));
                report.setSosEmailSent(resultSet.getBoolean("SosEmailSent"));
                report.setTripCoordinates(Arrays.asList(resultSet.getString("TripCoordinates").split(",")));

                return report;
            }

        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

