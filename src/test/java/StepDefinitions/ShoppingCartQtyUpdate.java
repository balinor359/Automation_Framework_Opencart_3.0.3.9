package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.CartPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class ShoppingCartQtyUpdate extends TestUtilities {

    @When("select {string} product and insert new value {string} in product quantity field and click update button")
    public void select_product_and_insert_new_value_in_product_quantity_field_and_click_update_button(String string, String string2) {
        System.out.println("Inside Step - select \"" + string + "\" product and insert new value \"" + string2 + "\" in product quantity field and click update button");
        MyFileWriter.writeToLog("Inside Step - select  \"" + string + "\"  product and insert new value \"" + string2 + "\" in product quantity field and click update button");

        CartPage cartPage = new CartPage(TestUtilities.driver);
        cartPage.cartPageValidator();

        cartPage.updateProductQtyInCart(string, string2);
        cartPage.validateSuccessMessageForModifiedCart();

    }

    @Then("user should see {string} product total price be multiplied by {int}")
    public void user_should_see_product_total_price_be_multiplied_by(String string, int number) {
        System.out.println("Inside Step - user should see \"" + string + "\" product total price be multiplied by \"" + number);
        MyFileWriter.writeToLog("Inside Step - user should see  \"" + string + "\" product total price be multiplied by \"" + number);

        CartPage cartPage = new CartPage(TestUtilities.driver);
        cartPage.validateProductQtyInCart(string, number);

    }

}
