package StepDefinitions;

import io.cucumber.java.en.When;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class CompleteOrderAsLoggedUser extends TestUtilities {

    @When("the user add {string} product to shopping cart from product page")
    public void user_add_product_to_shopping_cart_from_product_page(String string) {
        System.out.println("Inside Step - the user add \"" + string + "\" product to shopping cart from product page");
        MyFileWriter.writeToLog("Inside Step - the user add \"" + string + "\" product to shopping cart from product page");

    }
    @When("go through all checkout steps")
    public void go_through_all_checkout_steps() {
        System.out.println("Inside Step - o through all checkout steps");
        MyFileWriter.writeToLog("Inside Step - o through all checkout steps");

    }
    @When("fill all required fields with valid credentials")
    public void fill_all_required_fields_with_valid_credentials() {
        System.out.println("Inside Step - fill all required fields with valid credentials");
        MyFileWriter.writeToLog("Inside Step - fill all required fields with valid credentials");

    }
}
