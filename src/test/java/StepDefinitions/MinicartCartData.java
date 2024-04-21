package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.CartPage;
import test.opencart.pom.HomePage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class MinicartCartData extends TestUtilities {

    @When("user clicks on mini cart in header he sees added product data")
    public void user_clicks_on_mini_cart_in_header_he_see_added_product_data() {
        System.out.println("Inside Step - user clicks on mini cart in header he sees added product data");
        MyFileWriter.writeToLog("Inside Step - user clicks on mini cart in header he sees added product data");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickMinicartButton();
        homePage.cartItemsValidator();
    }

    @Then("the product data in the shopping cart page must be the same as mini cart product data")
    public void the_product_data_in_the_shopping_cart_page_must_be_the_same_as_mini_cart_product_data() {
        System.out.println("Inside Step - the product data in the shopping cart page must be the same as mini cart product data");
        MyFileWriter.writeToLog("Inside Step - the product data in the shopping cart page must be the same as mini cart product data");

        CartPage cartPage = new CartPage(TestUtilities.driver);
        cartPage.cartPageValidator();
    }
}
