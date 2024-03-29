package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class ShoppingCartQtyUpdate extends TestUtilities {

    @When("insert new value {string} in product quantity field and click update button")
    public void insert_new_value_in_product_quantity_field_and_click_update_button(String string) {
        System.out.println("Inside Step - insert new value \"" + string + "\" in product quantity field and click update button");
        MyFileWriter.writeToLog("Inside Step - insert new value \"" + string + "\" in product quantity field and click update button");

    }
    @Then("the product data in the shopping cart page must change")
    public void the_product_data_in_the_shopping_cart_page_must_change() {
        System.out.println("Inside Step - the product data in the shopping cart page must change");
        MyFileWriter.writeToLog("Inside Step - the product data in the shopping cart page must change");

        TestUtilities.simpleWait(2000);
    }

}
