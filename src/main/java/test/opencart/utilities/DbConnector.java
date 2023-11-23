package test.opencart.utilities;

import java.sql.*;

public class DbConnector {
    public static void main(String[] args) {
//        DBConnect dbConnect = new DBConnect();

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_OC_db", "root", "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from oc_customer");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
