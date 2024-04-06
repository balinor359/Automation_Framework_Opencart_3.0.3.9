package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.RegistrationPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

import java.util.List;
import java.util.Map;

public class CompleteOrderAsGuest extends TestUtilities {
    @When("the user add {string} product to shopping cart from homepage")
    public void the_user_add_product_to_shopping_cart_from_homepage(String string) {
        System.out.println("Inside Step - the user add \"" + string + "\" product to shopping cart from homepage");
        MyFileWriter.writeToLog("Inside Step - the user add  \"" + string + "\" product to shopping cart from homepage");
    }
    @When("click on <Checkout> button in header")
    public void click_on_checkout_button_in_header() {
        System.out.println("Inside Step - click on <Checkout> button in header");
        MyFileWriter.writeToLog("Inside Step - click on <Checkout> button in header");
    }
    @When("select <Guest Checkout> in checkout page")
    public void select_guest_checkout_in_checkout_page() {
        System.out.println("Inside Step - select <Guest Checkout> in checkout page");
        MyFileWriter.writeToLog("Inside Step - select <Guest Checkout> in checkout page");
    }
    @When("go through all checkout steps filling all required fields with valid credentials as follows:")
    public void go_through_all_checkout_steps_filling_all_required_fields_with_valid_credentials_as_follows(io.cucumber.datatable.DataTable dataTable) {
        System.out.println("Inside Step - go through all checkout steps filling all required fields with valid credentials");
        MyFileWriter.writeToLog("Inside Step - go through all checkout steps filling all required fields with valid credentials");

        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);

        String firstName = credentials.get(0).get("firstName");
        String lastName = credentials.get(0).get("lastName");
        String email = credentials.get(0).get("email");
        String telephone = credentials.get(0).get("telephone");
        String address = credentials.get(0).get("address");
        String city = credentials.get(0).get("city");
        String postCode = credentials.get(0).get("postCode");
        String country = credentials.get(0).get("country");
        String region = credentials.get(0).get("region");
        String comment = credentials.get(0).get("comment");

//        RegistrationPage registrationPage = new RegistrationPage(TestUtilities.driver);
//        checkoutPage.fillCheckoutForm(firstName, lastName, email, telephone, address, city, postCode, country, region);


    }
    @When("click on <Confirm Order> button")
    public void click_on_confirm_order_button() {
        System.out.println("Inside Step - click on <Confirm Order> button");
        MyFileWriter.writeToLog("Inside Step - click on <Confirm Order> button");
    }
    @Then("the user should be redirected to success page and see {string} message.")
    public void the_user_should_be_redirected_to_success_page_and_see_message(String string) {
        System.out.println("Inside Step - the user should be redirected to success page and see \"" + string + "\" message.");
        MyFileWriter.writeToLog("Inside Step - the user should be redirected to success page and see \"" + string + "\" message.");
    }
}
