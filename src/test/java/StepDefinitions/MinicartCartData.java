package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.objects.Product;
import test.opencart.pom.CartPage;
import test.opencart.pom.HomePage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class MinicartCartData extends TestUtilities {

    @When("the user add product in shopping cart from home page")
    public void the_user_add_product_in_shopping_cart_from_home_page() {
        System.out.println("Inside Step - the user clicks on mini cart in header");
        MyFileWriter.writeToLog("Inside Step - the user clicks on mini cart in header");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.addProductToProductList("MacBook");
        homePage.addProductToCart("MacBook");

    }

    @When("user clicks on mini cart in header he sees added product data")
    public void see_added_product_data() {
        System.out.println("Inside Step - user clicks on mini cart in header he sees added product data");
        MyFileWriter.writeToLog("Inside Step - user clicks on mini cart in header he sees added product data");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickMinicartButton();
        homePage.cartItemsValidator();
    }

    @When("navigate to shopping cart page")
    public void navigate_to_shopping_cart_page() {
        System.out.println("Inside Step - navigate to shopping cart page");
        MyFileWriter.writeToLog("Inside Step - navigate to shopping cart page");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickOnHeaderShoppingCartLink();
    }

    @Then("the product data in the shopping cart page must be the same as mini cart product data")
    public void the_product_data_in_the_shopping_cart_page_must_be_the_same_as_mini_cart_product_data() {
        System.out.println("Inside Step - the product data in the shopping cart page must be the same as mini cart product data");
        MyFileWriter.writeToLog("Inside Step - the product data in the shopping cart page must be the same as mini cart product data");

        CartPage cartPage = new CartPage(TestUtilities.driver);
        cartPage.cartPageValidator();
    }
}
