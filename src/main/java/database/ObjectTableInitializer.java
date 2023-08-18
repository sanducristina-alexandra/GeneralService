package database;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class ObjectTableInitializer {
    @PostConstruct
    public void createTables() {
        String url = "jdbc:sqlite:GeneralService.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                createClimatizationReportTable(conn);
                createTripReportTable(conn);
                createTargetTemperatureTable(conn);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTargetTemperatureTable(Connection conn) {
        try (Statement statement = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS TargetTemperature ("
                    + "TemperatureValue INTEGER"
                    + ");";
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTripReportTable(Connection conn) {
        try (Statement statement = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS TripReports ("
                    + "Id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "StartTripDate TEXT,"
                    + "EndTripDate TEXT,"
                    + "Status TEXT,"
                    + "SosEmailSent BOOLEAN,"
                    + "TripCoordinates TEXT"
                    + ");";
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createClimatizationReportTable(Connection conn) throws SQLException {
        try (Statement statement = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS ClimatizationReports ("
                    + "Id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "Date TEXT,"
                    + "Power INTEGER,"
                    + "ActionCode INTEGER"
                    + ");";
            statement.execute(createTableSQL);
        }
    }


}
