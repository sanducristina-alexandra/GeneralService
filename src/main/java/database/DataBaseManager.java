package database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DataBaseManager {
    private static final Logger LOGGER = LogManager.getLogger(DataBaseManager.class.getName());

    @PostConstruct
    public void initializeDatabase() {
        String url = "jdbc:sqlite:GeneralService.db";

        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    LOGGER.info("The driver name is " + meta.getDriverName());
                    LOGGER.info("A new database has been created.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
