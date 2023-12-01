package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import test.opencart.pom.*;
import test.opencart.utilities.DbCrud;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;
import test.opencart.utilities.UserBuilder;

import java.util.List;
import java.util.Map;

public class Registration extends TestUtilities {
    @Before
    public void beforeScenario() {
        /* Call the setUp method to initialize the driver before the scenario*/
        setUp();

        System.out.println("Registration - feature testing!");
        MyFileWriter.writeToLog("Registration - feature testing!");
    }

    @After
    public void afterScenario() {
        // Teardown code after the scenario
        tearDown();
    }
    @Given("the user is on home page")
    public void the_user_is_on_home_page() {

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.homepageValidator();
        homePage.topLinksValidator();
        homePage.clickOnRegisterLink();
    }
    @When("the user enters registration page")
    public void the_user_enters_registration_page() {
        RegistrationPage registrationPage = new RegistrationPage(TestUtilities.driver);
        registrationPage.registrationPageValidator();

    }
    @When("insert valid credentials as follows:")
    public void insert_valid_credentials_as_follows(io.cucumber.datatable.DataTable dataTable){
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);

        String firstName = credentials.get(0).get("firstName");
        String lastName = credentials.get(0).get("lastName");
        String email = credentials.get(0).get("email");
        String telephone = credentials.get(0).get("telephone");
        String password = credentials.get(0).get("password");

        RegistrationPage registrationPage = new RegistrationPage(TestUtilities.driver);
        registrationPage.fillRegistrationForm(firstName, lastName, email, telephone, password);
    }
    @When("click on Continue button")
    public void click_on_button() {
        RegistrationPage registrationPage = new RegistrationPage(TestUtilities.driver);

        RegistrationSuccessPage registrationSuccessPage = registrationPage.clickOnContinueButton();
        registrationSuccessPage.registrationSuccessPageValidator();
        registrationSuccessPage.clickOnContinueButton();

    }
    @Then("the user should been registered and redirect to its profile")
    public void the_user_should_been_registrated_and_redirect_to_its_profile() {
        AccountPage accountPage = new AccountPage(TestUtilities.driver);
        accountPage.accountPageValidator();

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickOnHomepageLink();

    }
}
