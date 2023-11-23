package test.opencart.utilities;

import java.sql.*;

public class DbConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_OC_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection connect() throws SQLException {
        System.out.println("Connecting to DB...");
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

    public void disconnect(Connection connection) throws SQLException {
        System.out.println("Disconnecting from DB...");
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

//    public static void main(String[] args) {
////        DBConnect dbConnect = new DBConnect();
//
//        try {
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_OC_db", "root", "");
//
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("select * from oc_customer");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("email"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
