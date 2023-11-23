package test.opencart.utilities;

import java.sql.*;

public class DbCrud extends DbConnector{
    public void createUser(String email) {

        Connection connection = null;
        try {
            connection = connect();
            // Perform the insert operation using PreparedStatement
            // ...
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void readUser(String sql) {
        Connection connection = null;
        try {
            connection = connect();
            // Perform the select operation using PreparedStatement
            // ...
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Retrieve data from each column and print it
                int id = resultSet.getInt("customer_id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                // Retrieve other columns as needed

                System.out.println("Customer ID: " + id + ", First Name: " + firstname + ", Last Name: " + lastname + ", Email: " + email );
                // Print other columns as needed
            }
//            ResultSet rs = statement.executeQuery(sql);
//            //Retrieving the ResultSetMetadata object
//            ResultSetMetaData rsMetaData = rs.getMetaData();
//            System.out.println("List of column names in the current table: ");
//            //Retrieving the list of column names
//            int count = rsMetaData.getColumnCount();
//            for(int i = 1; i<=count; i++) {
//                System.out.print(rsMetaData.getColumnName(i) + ", ");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUser(String email) {
        Connection connection = null;
        try {
            connection = connect();
            // Perform the update operation using PreparedStatement
            // ...
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUser(int userId) {
        Connection connection = null;
        try {
            connection = connect();
            // Perform the delete operation using PreparedStatement
            // ...
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
