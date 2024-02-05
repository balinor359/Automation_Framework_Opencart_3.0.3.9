package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.HomePage;
import test.opencart.pom.ProductPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class AddReviewToProduct extends TestUtilities {
    @When("the user navigate to product page")
    public void the_user_navigate_to_product_page() {
        System.out.println("Inside Step - the user navigate to product page");
        MyFileWriter.writeToLog("Inside Step - the user navigate to product page");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.addProductToProductList("iMac");

        ProductPage productPage = homePage.goToProductPage("iMac");
        productPage.productPageValidator();
    }

    @When("click on `Reviews` tab")
    public void click_on_reviews_tab() {
        System.out.println("Inside Step - click on `Reviews` tab");
        MyFileWriter.writeToLog("Inside Step - click on `Reviews` tab");

        ProductPage productPage = new ProductPage(TestUtilities.driver);
        productPage.clickTabReviews();
        productPage.reviewFormValidator();

    }

    @When("fill the review form with valid data")
    public void fill_the_review_form_with_valid_data() {
        System.out.println("Inside Step - fill the review form with valid data");
        MyFileWriter.writeToLog("Inside Step - fill the review form with valid data");

        ProductPage productPage = new ProductPage(TestUtilities.driver);
        productPage.insertNameIntoReview("Ivan Ivanov");
        productPage.insertReviewTextIntoReview("This product is very cheap!");
        productPage.insertRatingIntoReview(4);

    }

    @When("click on `Continue` button")
    public void click_on_continue_button() {
        System.out.println("Inside Step - click on `Continue` button");
        MyFileWriter.writeToLog("Inside Step - click on `Continue` button");

        ProductPage productPage = new ProductPage(TestUtilities.driver);
        productPage.reviewFormSubmit();

    }

    @Then("the user should see {string} notification message.")
    public void the_user_should_see_message(String string) {
        System.out.println("Inside Step - the user should see \"" + string + "\" notification message.");
        MyFileWriter.writeToLog("Inside Step - the user should see \"" + string + "\" notification message.");

        ProductPage productPage = new ProductPage(TestUtilities.driver);
        productPage.validateSuccessMessage(string);
    }
}
