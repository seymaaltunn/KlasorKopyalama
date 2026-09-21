package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private static final String URL =
            "jdbc:sqlserver://DESKTOP-H38OK0E;"
                    + "databaseName=HukukTestDB;"
                    + "integratedSecurity=true;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {

        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}