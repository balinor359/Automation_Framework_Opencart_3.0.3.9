package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.*;
import test.opencart.utilities.DbCrud;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class CommonSteps extends TestUtilities {

    /* Declaring string variables for the current steps */
    private static final int CUSTOMER_ID = 3;
    private static final String CUSTOMER_PHONE_NUMBER = "0888888888";
    private static final String CUSTOMER_EMAIL = "ivanov@test.test";

    @Before
    public void setUpBeforeTest() {
        setUp();
    }

    @After
    public void tearDownAfterTest() {
        tearDown();
    }

    @Given("the user is on home page")
    public void the_user_is_on_home_page() {
        System.out.println("Inside Step - the user is on home page");
        MyFileWriter.writeToLog("Inside Step - the user is on home page");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.homepageValidator();
    }

    @When("I check the state of database table oc_customer")
    public void i_check_the_state_of_database_table_oc_customer() {
        System.out.println("Inside Step - I check the state of database table oc_customer");
        MyFileWriter.writeToLog("Inside Step - I check the state of database table oc_customer");

        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @When("I send POST HTTP request")
    public void i_send_post_http_request() {
        System.out.println("Inside Step - I send POST HTTP request");
        MyFileWriter.writeToLog("Inside Step - I send POST HTTP request");

        DbCrud dbCrud = new DbCrud();
        dbCrud.createDbCustomer();
    }

    @Then("I receive a Response")
    public void i_receive_a_response() {
        System.out.println("Inside Step - I receive a Response");
        MyFileWriter.writeToLog("Inside Step - I receive a Response");

        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @When("I send GET HTTP request")
    public void i_send_get_http_request() {
        System.out.println("Inside Step - I send GET HTTP request");
        MyFileWriter.writeToLog("Inside Step - I send GET HTTP request");

        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @Given("I Read database customer table")
    public void i_read_database_customer_table() {
        System.out.println("Inside Step - I Read database customer table");
        MyFileWriter.writeToLog("Inside Step - I Read database customer table");

        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
    }

    @When("I send UPDATE HTTP request")
    public void i_send_update_http_request() {
        System.out.println("Inside Step - I send UPDATE HTTP request");
        MyFileWriter.writeToLog("Inside Step - I send UPDATE HTTP request");

        DbCrud dbCrud = new DbCrud();
        dbCrud.updateCustomerPhone(CUSTOMER_ID, CUSTOMER_PHONE_NUMBER);
    }

    @When("I send DELETE HTTP request")
    public void i_send_delete_http_request() {
        System.out.println("Inside Step - I send DELETE HTTP request");
        MyFileWriter.writeToLog("Inside Step - I send DELETE HTTP request");

        DbCrud dbCrud = new DbCrud();
        dbCrud.deleteCustomer(CUSTOMER_EMAIL);
    }

    @When("the user login into his profile with email {string}, and password {string}")
    public void the_user_login_into_his_profile_with_email_and_password(String email, String password) {
        System.out.println("Inside Step - the user login into his profile with email " + email + ", and password " + password);
        MyFileWriter.writeToLog("Inside Step - the user login into his profile with email " + email + ", and password " + password);

        HomePage homePage = new HomePage(TestUtilities.driver);

        LoginPage loginPage = homePage.clickOnLoginPageLink();
        loginPage.loginPageValidator();
        loginPage.fillLoginForm(email, password);

        AccountPage accountPage = loginPage.clickOnLoginButton();
        accountPage.accountPageValidator();
    }

    @When("the user add {string} product in shopping cart from home page")
    public void the_user_add_product_in_shopping_cart_from_home_page(String string) {
        System.out.println("Inside Step - the user add \"" + string + "\" product in shopping cart from home page");
        MyFileWriter.writeToLog("Inside Step - the user add \"" + string + "\" product in shopping cart from home page");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.addProductToProductList(string);
        homePage.addProductToCart(string);

    }

    @When("navigate to shopping cart page")
    public void navigate_to_shopping_cart_page() {
        System.out.println("Inside Step - navigate to shopping cart page");
        MyFileWriter.writeToLog("Inside Step - navigate to shopping cart page");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickOnHeaderShoppingCartLink();
    }

    @Given("the user have a profile in website and is on home page")
    public void the_user_have_a_profile_in_website_and_is_on_home_page() {
        System.out.println("Inside Step - the user have a profile in website and is on home page");
        MyFileWriter.writeToLog("Inside Step - the user have a profile in website and is on home page");

        DbCrud dbCrud = new DbCrud();
        dbCrud.createDbCustomer();
        dbCrud.readDbCustomers();

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.homepageValidator();

    }

    @When("click on <Checkout> button in header")
    public void click_on_checkout_button_in_header() {
        System.out.println("Inside Step - click on <Checkout> button in header");
        MyFileWriter.writeToLog("Inside Step - click on <Checkout> button in header");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickOnHeaderCheckoutLink();

    }

    @When("click on <Confirm Order> button")
    public void click_on_confirm_order_button() {
        System.out.println("Inside Step - click on <Confirm Order> button");
        MyFileWriter.writeToLog("Inside Step - click on <Confirm Order> button");

        CheckoutPage checkoutPage = new CheckoutPage(TestUtilities.driver);
        checkoutPage.completeOrder();

    }

    @Then("the user should be redirected to success page and see {string} message.")
    public void the_user_should_be_redirected_to_success_page_and_see_message(String string) {
        System.out.println("Inside Step - the user should be redirected to success page and see \"" + string + "\" message.");
        MyFileWriter.writeToLog("Inside Step - the user should be redirected to success page and see \"" + string + "\" message.");

        OrderSuccessPage orderSuccessPage = new OrderSuccessPage(TestUtilities.driver);
        orderSuccessPage.orderSuccessPageValidator(string);
        orderSuccessPage.clickOnContinueButton();

    }
}
