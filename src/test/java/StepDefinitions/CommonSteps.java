package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.utilities.DbCrud;

public class CommonSteps {

    @When("I check the state of database table oc_customer")
    public void i_check_the_state_of_database_table_oc_customer() {
        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @When("I send POST HTTP request")
    public void i_send_post_http_request() {
        DbCrud dbCrud = new DbCrud();
        dbCrud.createDbCustomer();
    }

    @Then("I receive a Response")
    public void i_receive_a_response() {
        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @When("I send GET HTTP request")
    public void i_send_get_http_request() {
        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @Given("I Read database customer table")
    public void i_read_database_customer_table() {
        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @When("I send UPDATE HTTP request")
    public void i_send_update_http_request() {
        DbCrud dbCrud = new DbCrud();
        dbCrud.updateCustomerPhone(3, "0888888888");
    }

    @When("I send DELETE HTTP request")
    public void i_send_delete_http_request() {
        DbCrud dbCrud = new DbCrud();
        dbCrud.deleteCustomer("ivanov@test.test");
    }
}
