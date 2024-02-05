package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.*;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

import java.util.List;
import java.util.Map;

public class Registration extends TestUtilities {

    @When("the user enters registration page")
    public void the_user_enters_registration_page() {
        System.out.println("Inside Step - the user enters registration page");
        MyFileWriter.writeToLog("Inside Step - the user enters registration page");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.topLinksValidator();

        RegistrationPage registrationPage = homePage.clickOnRegisterLink();
        registrationPage.registrationPageValidator();

    }

    @When("insert valid credentials as follows:")
    public void insert_valid_credentials_as_follows(io.cucumber.datatable.DataTable dataTable) {
        System.out.println("Inside Step - insert valid credentials");
        MyFileWriter.writeToLog("Inside Step - insert valid credentials");

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
        System.out.println("Inside Step - click on Continue button");
        MyFileWriter.writeToLog("Inside Step - click on Continue button");

        RegistrationPage registrationPage = new RegistrationPage(TestUtilities.driver);

        RegistrationSuccessPage registrationSuccessPage = registrationPage.clickOnContinueButton();
        registrationSuccessPage.registrationSuccessPageValidator();
        registrationSuccessPage.clickOnContinueButton();

    }

    @Then("the user should been registered and redirect to its profile")
    public void the_user_should_been_registered_and_redirect_to_its_profile() {
        System.out.println("Inside Step - the user should been registered and redirect to its profile");
        MyFileWriter.writeToLog("Inside Step - the user should been registered and redirect to its profile");

        AccountPage accountPage = new AccountPage(TestUtilities.driver);
        accountPage.accountPageValidator();

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickOnHomepageLink();

    }
}
