package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import test.opencart.objects.Customer;
import test.opencart.utilities.DbConnector;
import test.opencart.utilities.DbCrud;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;


public class Steps {

    @Given("the user is on login page")
    public void the_user_is_on_login_page() {
        System.out.println("The user is on login page");
    }
    @When("the user enters valid credentials")
    public void the_user_enters_valid_credentials() {
        System.out.println("Entered username and password");

        DbCrud dbCrud = new DbCrud();

        dbCrud.readDbCustomers();
        // create customer to the DB
        dbCrud.createDbCustomer();

//        dbCrud.readDbCustomers();
        Assert.assertEquals(dbCrud.customersList.get(3).getLastname(), "Ivanov", "The lastname in DB is different!");

        // read customers from DB
        dbCrud.readDbCustomers();


        //update customer phone in DB
        dbCrud.updateCustomerPhone(3,"111111");
        // Assert for testing user phone in DB
//        System.out.println("dbCrud.customersList.get(3) " + dbCrud.customersList.get(3));
//        dbCrud.testCustomerPhone(dbCrud.customersList.get(3),"000000");

        dbCrud.readDbCustomers();


        //delete customer from DB
        dbCrud.deleteCustomer("ivanov@test.test");
        dbCrud.readDbCustomers();

        // Clear Customers list //todo Да се изместят накрая в TestUtilities при затваряне на драйвъра
        dbCrud.customersList.clear();
        System.out.println("customersList " + dbCrud.customersList);

    }
    @When("hits submit")
    public void hits_submit() {
        System.out.println("Clicked on submit button");
    }
    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        System.out.println("Yeah I am logged in");

    }
}
