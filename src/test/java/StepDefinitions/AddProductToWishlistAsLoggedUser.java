package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.HomePage;
import test.opencart.pom.WishlistPage;
import test.opencart.utilities.DbCrud;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class AddProductToWishlistAsLoggedUser extends TestUtilities {

    @When("user add product from homepage to his wishlist")
    public void user_add_product_from_homepage_to_his_wishlist() {
        System.out.println("Inside Step - user add product from homepage to his wishlist");
        MyFileWriter.writeToLog("Inside Step - user add product from homepage to his wishlist");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickOnHomepageLink();
        homePage.homepageValidator();

        homePage.addProductToWishlist("MacBook");
        homePage.validateAddedProductToWishlist("MacBook");

    }

    @When("click on <Wish List> button in header")
    public void click_on_button_in_header() {
        System.out.println("Inside Step - click on <Wish List> button in header");
        MyFileWriter.writeToLog("Inside Step - click on <Wish List> button in header");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.clickOnHeaderWishlistPageLink();

    }

    @Then("the user should be redirected to his wishlist page and see added product there.")
    public void the_user_should_be_redirected_to_his_wishlist_page_and_see_added_product_there() {
        System.out.println("Inside Step - the user should be redirected to his wishlist page and see added product there.");
        MyFileWriter.writeToLog("Inside Step - the user should be redirected to his wishlist page and see added product there.");

        WishlistPage wishlistPage = new WishlistPage(TestUtilities.driver);
        wishlistPage.wishlistPageValidator();

        DbCrud dbCrud = new DbCrud();
        dbCrud.deleteCustomer("ivanov@test.test");
        dbCrud.readDbCustomers();
    }
}
