package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.CartPage;
import test.opencart.pom.HomePage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class EmptyCartCheckout extends TestUtilities {

    @When("the user clicks on mini cart in header")
    public void the_user_clicks_on_mini_cart_in_header() {
        System.out.println("Inside Step - the user clicks on mini cart in header");
        MyFileWriter.writeToLog("Inside Step - the user clicks on mini cart in header");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.topLinksValidator();
        homePage.clickMinicartButton();

    }
    @Then("the user should see {string} minicart message.")
    public void the_user_should_see_minicart_message(String string) {
        System.out.println("Inside Step - the user should see " + string + " minicart message.");
        MyFileWriter.writeToLog("Inside Step - the user should see " + string + " minicart message.");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.validateEmptyMinicartMessage(string);
    }


    @When("the user clicks on Checkout link in header")
    public void the_user_clicks_on_checkout_link_in_header() {
        System.out.println("Inside Step - the user clicks on Checkout link in header");
        MyFileWriter.writeToLog("Inside Step - the user clicks on Checkout link in header");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.topLinksValidator();
        homePage.clickOnHeaderCheckoutLink();
    }
    @Then("the user should be redirected to shopping cart page and see {string} message.")
    public void the_user_should_be_redirected_to_shopping_cart_page_and_see_message(String string) {
        System.out.println("Inside Step - the user should be redirected to shopping cart page and see \"" + string + "\" message.");
        MyFileWriter.writeToLog("Inside Step - the user should be redirected to shopping cart page and see \"" + string + "\" message.");

        CartPage cartPage = new CartPage(TestUtilities.driver);
        cartPage.emptyCartPageValidator(string);
    }
}
