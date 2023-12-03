package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class ContactForm extends TestUtilities {
    @When("the user navigate to contact page")
    public void the_user_navigate_to_contact_page() {
        System.out.println("Inside Step - the user navigate to contact page");
        MyFileWriter.writeToLog("Inside Step - the user navigate to contact page");

    }
    @When("fill <Your Name> field as {string}")
    public void fill_your_name_field_as(String string) {
        System.out.println("Inside Step - fill <Your Name> field as " + string);
        MyFileWriter.writeToLog("Inside Step - fill <Your Name> field as " + string);

    }
    @When("fill <E-Mail Address> as {string}")
    public void fill_e_mail_address_as(String string) {
        System.out.println("Inside Step - fill <E-Mail Address> as " + string);
        MyFileWriter.writeToLog("Inside Step - fill <E-Mail Address> as " + string);

    }
    @When("fill <Enquiry> as {string}")
    public void fill_enquiry_as(String string) {
        System.out.println("Inside Step - fill <Enquiry> as " + string);
        MyFileWriter.writeToLog("Inside Step - fill <Enquiry> as " + string);

    }
    @When("click on <Submit> button")
    public void click_on_submit_button() {
        System.out.println("Inside Step - click on <Submit> button");
        MyFileWriter.writeToLog("Inside Step - click on <Submit> button");

    }
    @Then("the user should see {string} message.")
    public void the_user_should_see_message(String string) {
        System.out.println("Inside Step - the user should see \"" + string + "\" message.");
        MyFileWriter.writeToLog("Inside Step - the user should see \"" + string + "\" message.");

    }
}
