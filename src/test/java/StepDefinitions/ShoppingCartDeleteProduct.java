package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.CartPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class ShoppingCartDeleteProduct extends TestUtilities {
    @When("user click on {string} product delete button")
    public void user_click_on_product_delete_button(String string) {
        System.out.println("Inside Step - user click on \"" + string + "\" product delete button");
        MyFileWriter.writeToLog("Inside Step - user click on \"" + string + "\" product delete button");

        CartPage cartPage = new CartPage(TestUtilities.driver);
        cartPage.cartPageValidator();
        cartPage.removeProductFromCart(string);

    }

    @Then("the user should see {string} message on the Shopping Cart page.")
    public void the_user_should_see_message_on_the_shopping_cart_page(String string) {
        System.out.println("Inside Step - the user should see \"" + string + "\" message on the Shopping Cart page.");
        MyFileWriter.writeToLog("Inside Step - the user should see \"" + string + "\" message on the Shopping Cart page.");

        CartPage cartPage = new CartPage(TestUtilities.driver);
        cartPage.emptyCartPageValidator(string);
    }
}
