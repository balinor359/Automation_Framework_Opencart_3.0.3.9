package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.CartPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class ShoppingCartDeleteProduct extends TestUtilities {
    @When("user click delete button")
    public void user_click_delete_button() {
        System.out.println("Inside Step - user click delete button");
        MyFileWriter.writeToLog("Inside Step - user click delete button");

        CartPage cartPage = new CartPage(TestUtilities.driver);
        cartPage.cartPageValidator();
        cartPage.removeProductFromCart("MacBook");

    }

    @Then("the user should see {string} message on the Shopping Cart page.")
    public void the_user_should_see_message_on_the_shopping_cart_page(String string) {
        System.out.println("Inside Step - the user should see \"" + string + "\" message on the Shopping Cart page.");
        MyFileWriter.writeToLog("Inside Step - the user should see \"" + string + "\" message on the Shopping Cart page.");

        CartPage cartPage = new CartPage(TestUtilities.driver);
        cartPage.emptyCartPageValidator(string);
    }
}
