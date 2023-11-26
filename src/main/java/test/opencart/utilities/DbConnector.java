package test.opencart.utilities;

import java.sql.*;

public class DbConnector {

    /* Database data */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_OC_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /* Connect with the database */
    public Connection connect() throws SQLException {
        MyFileWriter.writeToLog("Connecting to DB...");
        System.out.println("Connecting to DB...");
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    // Disconnect from database */
    public void disconnect(Connection connection) throws SQLException {
        MyFileWriter.writeToLog("Disconnecting from DB...");
        System.out.println("Disconnecting from DB...");
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
