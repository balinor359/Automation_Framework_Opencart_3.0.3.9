package test.opencart.pom;

import test.opencart.objects.Product;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductPage extends TestUtilities {

    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    private static final String SUCCESS_MESSAGE_MISSING_MESSAGE = "Success message is missing!";
    private static final String SUCCESS_MESSAGE_DIFFERENT_MESSAGE = "Success message is different!";

    /* Declaring page elements */
    @FindBy(xpath = ".//h1[@qa='product_name_heading']")
    private WebElement heading;
    @FindBy(xpath = ".//a[@qa='tab-description-trigger']")
    private WebElement tabDescriptionTrigger;
    @FindBy(xpath = ".//a[@qa='tab-review-trigger']")
    private WebElement tabReviewTrigger;
    @FindBy(xpath = "//form[@id='form-review']")
    private WebElement reviewForm;
    @FindBy(xpath = ".//h2[@qa='write-a-review-title']")
    private WebElement reviewBoxTitle;
    @FindBy(id = "input-name")
    private WebElement inputName;
    @FindBy(id = "input-review")
    private WebElement inputReview;
    @FindBy(xpath = ".//input[@qa='rating1']")
    private WebElement reviewRating1;
    @FindBy(xpath = ".//input[@qa='rating2']")
    private WebElement reviewRating2;
    @FindBy(xpath = ".//input[@qa='rating3']")
    private WebElement reviewRating3;
    @FindBy(xpath = ".//input[@qa='rating4']")
    private WebElement reviewRating4;
    @FindBy(xpath = ".//input[@qa='rating5']")
    private WebElement reviewRating5;
    @FindBy(xpath = "//button[@id='button-review']")
    private WebElement reviewFormSubmitBtn;
    @FindBy(xpath = "//form[@id='form-review']//div[contains(@class,'alert-success')]")
    private WebElement reviewSuccessMsg;


    /* This is constructor for product page using PageFactory for web-elements */
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /* This method validate product page container is visible */
    public void productPageValidator() {
        TestUtilities.waitVisible(driver, heading, 5);
        Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);

        /* Go through all products in productList */
        for (Product product : Product.productList) {

            /* If element from cart match with element from productList compare their name and price */
            if (driver.getCurrentUrl().contains("route=product/product")) {

                /* Validate product page heading */
                Assert.assertEquals(heading.getText(), product.getName(), GenericMessages.PRODUCTS_NAME_IS_DIFFERENT_MESSAGE);

            }
        }
    }

    /* This method select tab "Reviews" */
    public void clickTabReviews() {
        TestUtilities.scrollTo(driver, tabReviewTrigger);
        TestUtilities.waitClickable(driver, tabReviewTrigger, 5);
        tabReviewTrigger.click();
    }

    /* This method validate search result page container is visible */
    public void reviewFormValidator() {
        Assert.assertTrue(reviewBoxTitle.isDisplayed(), GenericMessages.REVIEW_BOX_MISSING_HEADING);

        /* Validate review box heading contains */
        Assert.assertEquals(reviewBoxTitle.getText(), GenericMessages.REVIEW_BOX_HEADING, GenericMessages.DIFFERENT_REVIEW_BOX_HEADING);

        Assert.assertTrue(reviewForm.isDisplayed(), GenericMessages.REVIEW_FORM_MISSING_MESSAGE);
        Assert.assertTrue(inputName.isDisplayed(), GenericMessages.NAME_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(inputReview.isDisplayed(), GenericMessages.REVIEW_TEXTAREA_MISSING_MESSAGE);
        Assert.assertTrue(reviewRating1.isDisplayed(), GenericMessages.RATING_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(reviewRating2.isDisplayed(), GenericMessages.RATING_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(reviewRating3.isDisplayed(), GenericMessages.RATING_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(reviewRating4.isDisplayed(), GenericMessages.RATING_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(reviewRating5.isDisplayed(), GenericMessages.RATING_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(reviewFormSubmitBtn.isDisplayed(), GenericMessages.SUBMIT_BUTTON_MISSING_MESSAGE);
    }


    /* This method insert Name string into input field for Name */
    public void insertNameIntoReview(String name) {
        /* Clear, click and input data into name field */
        inputName.clear();
        inputName.click();
        inputName.sendKeys(name);
    }


    /* This method insert text string into textarea field for Review */
    public void insertReviewTextIntoReview(String review) {
        /* Clear, click and input data into textarea */
        inputReview.clear();
        inputReview.click();
        inputReview.sendKeys(review);
    }

    /* This method select rating input for Review by given number from 1 to 5 */
    public void insertRatingIntoReview(int number) {

        /* Click input by provided number */
        if (number == 1) {
            reviewRating1.click();
        } else if (number == 2) {
            reviewRating2.click();
        } else if (number == 3) {
            reviewRating3.click();
        } else if (number == 4) {
            reviewRating4.click();
        } else if (number == 5) {
            reviewRating5.click();
        }

    }

    /* Click method for review form submit button */
    public void reviewFormSubmit() {
        waitClickable(driver, reviewFormSubmitBtn, 5);
        reviewFormSubmitBtn.click();
    }

    /* This method validate success message, is it visible and the same as provided */
    public void validateSuccessMessage(String message) {
        Assert.assertTrue(reviewSuccessMsg.isDisplayed(), SUCCESS_MESSAGE_MISSING_MESSAGE);
        Assert.assertEquals(reviewSuccessMsg.getText(), message, SUCCESS_MESSAGE_DIFFERENT_MESSAGE);
    }

}
