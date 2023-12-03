package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.HomePage;
import test.opencart.utilities.DbCrud;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class CommonSteps extends TestUtilities {

    /* Declaring string variables for the current steps */
    private static final int CUSTOMER_ID = 3;
    private static final String CUSTOMER_PHONE_NUMBER = "0888888888";
    private static final String CUSTOMER_EMAIL = "ivanov@test.test";

    @Before
    public void setUpBeforeTest() {
        setUp();
    }

    @After
    public void tearDownAfterTest() {
        tearDown();
    }

    @Given("the user is on home page")
    public void the_user_is_on_home_page() {
        System.out.println("Inside Step - the user is on home page");
        MyFileWriter.writeToLog("Inside Step - the user is on home page");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.homepageValidator();
    }

    @When("I check the state of database table oc_customer")
    public void i_check_the_state_of_database_table_oc_customer() {
        System.out.println("Inside Step - I check the state of database table oc_customer");
        MyFileWriter.writeToLog("Inside Step - I check the state of database table oc_customer");

        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @When("I send POST HTTP request")
    public void i_send_post_http_request() {
        System.out.println("Inside Step - I send POST HTTP request");
        MyFileWriter.writeToLog("Inside Step - I send POST HTTP request");

        DbCrud dbCrud = new DbCrud();
        dbCrud.createDbCustomer();
    }

    @Then("I receive a Response")
    public void i_receive_a_response() {
        System.out.println("Inside Step - I receive a Response");
        MyFileWriter.writeToLog("Inside Step - I receive a Response");

        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @When("I send GET HTTP request")
    public void i_send_get_http_request() {
        System.out.println("Inside Step - I send GET HTTP request");
        MyFileWriter.writeToLog("Inside Step - I send GET HTTP request");

        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @Given("I Read database customer table")
    public void i_read_database_customer_table() {
        System.out.println("Inside Step - I Read database customer table");
        MyFileWriter.writeToLog("Inside Step - I Read database customer table");

        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @When("I send UPDATE HTTP request")
    public void i_send_update_http_request() {
        System.out.println("Inside Step - I send UPDATE HTTP request");
        MyFileWriter.writeToLog("Inside Step - I send UPDATE HTTP request");

        DbCrud dbCrud = new DbCrud();
        dbCrud.updateCustomerPhone(CUSTOMER_ID, CUSTOMER_PHONE_NUMBER);
    }

    @When("I send DELETE HTTP request")
    public void i_send_delete_http_request() {
        System.out.println("Inside Step - I send DELETE HTTP request");
        MyFileWriter.writeToLog("Inside Step - I send DELETE HTTP request");

        DbCrud dbCrud = new DbCrud();
        dbCrud.deleteCustomer(CUSTOMER_EMAIL);
    }
}
