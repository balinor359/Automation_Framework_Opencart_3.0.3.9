package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.CheckoutPage;
import test.opencart.pom.HomePage;
import test.opencart.pom.OrderSuccessPage;
import test.opencart.pom.ProductPage;
import test.opencart.utilities.DbCrud;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

import java.util.List;
import java.util.Map;

public class CompleteOrderAsLoggedUser extends TestUtilities {

    @When("the user add {string} product to shopping cart from product page")
    public void user_add_product_to_shopping_cart_from_product_page(String string) {
        System.out.println("Inside Step - the user add \"" + string + "\" product to shopping cart from product page");
        MyFileWriter.writeToLog("Inside Step - the user add \"" + string + "\" product to shopping cart from product page");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickOnHomepageLink();
        homePage.homepageValidator();

        homePage.addProductToProductList(string);
        ProductPage productPage = homePage.goToProductPage(string);

        productPage.productPageValidator();
        productPage.productValidator();

        productPage.addProductToCart();

    }

    @When("go through all checkout steps filling all required fields with valid user credentials as follows:")
    public void go_through_all_checkout_steps_filling_all_required_fields_with_valid_user_credentials_as_follows(io.cucumber.datatable.DataTable dataTable) {
        System.out.println("Inside Step - go through all checkout steps filling all required fields with valid user credentials");
        MyFileWriter.writeToLog("Inside Step - go through all checkout steps filling all required fields with valid user credentials");

        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);

        String firstName = credentials.get(0).get("firstName");
        String lastName = credentials.get(0).get("lastName");
        String address = credentials.get(0).get("address");
        String city = credentials.get(0).get("city");
        String postCode = credentials.get(0).get("postCode");
        String country = credentials.get(0).get("country");
        String region = credentials.get(0).get("region");
        String comment = credentials.get(0).get("comment");

        CheckoutPage checkoutPage = new CheckoutPage(TestUtilities.driver);
        checkoutPage.fillCheckoutFormUser(firstName, lastName, address, city, postCode, country, region);

        checkoutPage.addCommentToTheOrder(comment);
        checkoutPage.acceptTerms();

    }

    @Then("the user must be redirected to success page and see {string} message.")
    public void the_user_must_be_redirected_to_success_page_and_see_message(String string) {
        System.out.println("Inside Step - the user must be redirected to success page and see \"" + string + "\" message.");
        MyFileWriter.writeToLog("Inside Step - the user must be redirected to success page and see \"" + string + "\" message.");

        OrderSuccessPage orderSuccessPage = new OrderSuccessPage(TestUtilities.driver);
        orderSuccessPage.orderSuccessPageValidator(string);
        orderSuccessPage.clickOnContinueButton();

        DbCrud dbCrud = new DbCrud();
        dbCrud.deleteCustomer("ivanov@test.test");
        dbCrud.readDbCustomers();
    }
}
