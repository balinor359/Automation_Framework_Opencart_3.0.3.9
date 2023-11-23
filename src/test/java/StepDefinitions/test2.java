package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class test2 {
    @Given("the user is on home page")
    public void the_user_is_on_home_page() {
        System.out.println("The user is on login page 1");
    }
    @When("the user enters credentials")
    public void the_user_enters_credentials() {
        System.out.println("The user is on login page 2");
    }
    @When("hits h submit")
    public void hits_h_submit() {
        System.out.println("The user is on login page 3");
    }
    @Then("the user should be d logged in successfully")
    public void the_user_should_be_d_logged_in_successfully() {
        System.out.println("The user is on login page 4");
    }
}
