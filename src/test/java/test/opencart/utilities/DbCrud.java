package test.opencart.utilities;

import org.testng.Assert;
import test.opencart.objects.Customer;

import java.sql.*;
import java.util.ArrayList;


public class DbCrud extends DbConnector {
    /* Variables used for SQL statements */
    private static final String CUSTOMER_ID = "customer_id";
    private static final String CUSTOMER_FIRSTNAME = "firstname";
    private static final String CUSTOMER_LASTNAME = "lastname";
    private static final String CUSTOMER_EMAIL = "email";
    private static final String CUSTOMER_TELEPHONE = "telephone";
    private static final String CUSTOMER_SUBSCRIBED_TO_NEWSLETTER = "newsletter";
    private static final String CUSTOMER_DATE_ADDED = "date_added";
    public ArrayList<Customer> customersList = new ArrayList<>();
    private int customer_id = 0;

    public void prepareCreateDbCustomerStatement() {

    }
    /* Create new customer to the oc_customer table */
    public void createDbCustomer() {

        Connection connection = null;
        try {
            connection = connect();
            System.out.println("__________________________Create Customer___________________________________");
            MyFileWriter.writeToLog("__________________________Create Customer___________________________________");

            String sql = "INSERT INTO oc_customer (firstname, lastname, email, telephone, password, newsletter, customer_group_id, language_id, fax, salt," +
                    "custom_field, ip, status, safe, token, code, date_added)" +
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            /* Using a prepared statement to insert all needed data to the table */
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                String firstname = "Ivan";
                String lastname = "Ivanov";
                String email = "ivanov@test.test";
                String phone = "0887446682";
                String password = "4dfe6e220d16e7b633cfdd92bcc8050b"; // ivanov
                int newsletter = 1;
                int customer_group_id = 1;
                int language_id = 1;
                String fax = "";
                String salt = "A6Gjwe2oM";
                String custom_field = "";
                String ip = "127.0.0.1";
                int status = 1;
                int safe = 0;
                String token = "";
                String code = "";
                Timestamp date_added = new Timestamp(System.currentTimeMillis());

                /* Setting the customer parameters */
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastname);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, phone);
                preparedStatement.setString(5, password);
                preparedStatement.setInt(6, newsletter);
                preparedStatement.setInt(7, customer_group_id);
                preparedStatement.setInt(8, language_id);
                preparedStatement.setString(9, fax);
                preparedStatement.setString(10, salt);
                preparedStatement.setString(11, custom_field);
                preparedStatement.setString(12, ip);
                preparedStatement.setInt(13, status);
                preparedStatement.setInt(14, safe);
                preparedStatement.setString(15, token);
                preparedStatement.setString(16, code);
                preparedStatement.setTimestamp(17, date_added);

                /* Insert data to the oc_customer table */
                preparedStatement.executeUpdate();

                /* Get generated customer_id and date_added from the oc_customer table and create Customer object */
                ResultSet resultSet = null;
                try (PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT customer_id, date_added FROM oc_customer WHERE email = ?")) {
                    /* Set the email as a parameter */
                    preparedStatement2.setString(1, email);

                    /* Execute the query */
                    resultSet = preparedStatement2.executeQuery();

                    /* Check if the result set has data */
                    if (resultSet.next()) {
                        /* Retrieve data from the result set */
                        customer_id = resultSet.getInt(CUSTOMER_ID);
                        String dateAdded = resultSet.getString(CUSTOMER_DATE_ADDED);

                        /* Create a Customer object and add it to the list */
                        Customer customer = new Customer(customer_id, firstname, lastname, email, phone, newsletter, dateAdded);
                        customersList.add(customer);

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

    /* Read customers from the oc_customer table */
    public void readDbCustomers() {
        Connection connection = null;

        String firstname = null;
        String lastname = null;
        String email = null;
        String phone = null;
        int newsletter = 0;
        String date_added = null;

        ResultSet resultSet = null;

        try {
            connection = connect();
            Statement statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT customer_id, firstname, lastname, email, telephone, newsletter, date_added, address_id FROM oc_customer;");

            System.out.println("__________________________Database Response_________________________________");
            MyFileWriter.writeToLog("__________________________Database Response___________________________________");

            /* Get customer DB columns */
            while (resultSet.next()) {
                customer_id = resultSet.getInt(CUSTOMER_ID);
                firstname = resultSet.getString(CUSTOMER_FIRSTNAME);
                lastname = resultSet.getString(CUSTOMER_LASTNAME);
                email = resultSet.getString(CUSTOMER_EMAIL);
                phone = resultSet.getString(CUSTOMER_TELEPHONE);
                newsletter = resultSet.getInt(CUSTOMER_SUBSCRIBED_TO_NEWSLETTER);
                date_added = String.valueOf(resultSet.getString(CUSTOMER_DATE_ADDED));

                /* Create new Customer object and add data from the DB */
                Customer customer = new Customer(customer_id, firstname, lastname, email, phone, newsletter, date_added);

                /* Add Customer object to array */
                customersList.add(customer);

                String data = "Customer ID:" + customer_id +
                        " | First Name:" + firstname +
                        " | Last Name:" + lastname +
                        " | Email:" + email +
                        " | Telephone:" + phone +
                        " | Newsletter:" + newsletter +
                        " | Date Added:" + date_added;

                System.out.println(data);
                MyFileWriter.writeToLog(data);
            }

            System.out.println("____________________________________________________________________________");
            MyFileWriter.writeToLog("____________________________________________________________________________");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                disconnect(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /* Update customer phone number in oc_customer table */
    public void updateCustomerPhone(int customer_id, String phone) {
        Connection connection = null;

        try {
            connection = connect();
            System.out.println("__________________________Updating Values___________________________________");
            MyFileWriter.writeToLog("__________________________Updating Values___________________________________");

            String sql = "UPDATE oc_customer SET telephone = ? WHERE customer_id = ?";

            /* Using a prepared statement to handle missing zero for the first character */
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                /* Setting the phone and customer_id as parameters */
                preparedStatement.setString(1, phone);
                preparedStatement.setInt(2, customer_id);

                /* Execute the query */
                preparedStatement.executeUpdate();
            }
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

    /* Phone number validator */
    public void testCustomerPhone(Customer customer_id, String phone) {
        /* Validate the inserted data is the same with Database data */
        Assert.assertEquals(customer_id.getPhone(), phone, "The phone number in DB is different!");
    }

    /* Delete customer from oc_customer table by his email address */
    public void deleteCustomer(String email) {
        Connection connection = null;
        try {
            connection = connect();
            System.out.println("__________________________Deleting Customer_________________________________");
            MyFileWriter.writeToLog("__________________________Deleting Customer_________________________________");

            String sql = "DELETE FROM oc_customer WHERE email = ? ";

            /* Using a prepared statement to use passing data */
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                /* Setting the email as parameter */
                preparedStatement.setString(1, email);

                /* Execute the query */
                preparedStatement.executeUpdate();
            }
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
