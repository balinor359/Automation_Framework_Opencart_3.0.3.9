package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.objects.Product;
import test.opencart.pom.AccountPage;
import test.opencart.pom.HomePage;
import test.opencart.pom.LoginPage;
import test.opencart.utilities.DbCrud;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class AddProductToWishlistAsLoggedUser extends TestUtilities {
    @Given("the user have a profile in website and is on home page")
    public void the_user_have_a_profile_in_website_and_is_on_home_page() {
        System.out.println("Inside Step - the user have a profile in website and is on home page");
        MyFileWriter.writeToLog("Inside Step - the user have a profile in website and is on home page");

        DbCrud dbCrud = new DbCrud();
        dbCrud.readDbCustomers();
//        dbCrud.createDbCustomer();
//        dbCrud.readDbCustomers();

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.homepageValidator();

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
    @When("user add product from homepage to his wishlist")
    public void user_add_product_from_homepage_to_his_wishlist() {
        System.out.println("Inside Step - user add product from homepage to his wishlist");
        MyFileWriter.writeToLog("Inside Step - user add product from homepage to his wishlist");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickOnHomepageLink();
        homePage.homepageValidator();
        homePage.addProductToWishlist("MacBook");
        System.out.println("Product.productList2 " + Product.productList);

    }
    @When("click on <Wish List> button in header")
    public void click_on_button_in_header() {
        System.out.println("Inside Step - click on <Wish List> button in header");
        MyFileWriter.writeToLog("Inside Step - click on <Wish List> button in header");

    }
    @Then("the user should be redirected to his wishlist page and see added product there.")
    public void the_user_should_be_redirected_to_his_wishlist_page_and_see_added_product_there() {
        System.out.println("Inside Step - the user should be redirected to his wishlist page and see added product there.");
        MyFileWriter.writeToLog("Inside Step - the user should be redirected to his wishlist page and see added product there.");

    }
}
