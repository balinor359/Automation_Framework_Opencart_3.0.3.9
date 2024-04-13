package test.opencart.pom;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class OrderSuccessPage extends TestUtilities {

    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    public static final String SUCCESS_ORDER_PAGE_HEADING = "Your order has been placed!";

    /* Declaring page elements */
    @FindBy(xpath = "//div[@id='common-success']//h1")
    private WebElement heading;
    @FindBy(className = "btn-primary")
    private WebElement continueButton;
    @FindBy(xpath = ".//a[@qa='homepage_link']")
    private WebElement homepageLink;

    /* This is constructor for registration success page using PageFactory for web-elements */
    public OrderSuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method validate heading and continue button on order success page are visible */
    public void orderSuccessPageValidator() {
        try {
            Assert.assertEquals(heading.getText(), SUCCESS_ORDER_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
            Assert.assertTrue(continueButton.isDisplayed(), GenericMessages.CONTINUE_BUTTON_MISSING_MESSAGE);
        } catch (NoSuchElementException e) {
            System.out.println(String.format(GenericMessages.MISSING_ELEMENT_MESSAGE, heading));
            MyFileWriter.writeToLog(String.format(GenericMessages.MISSING_ELEMENT_MESSAGE, heading));

            Assert.fail(String.format(GenericMessages.MISSING_ELEMENT_MESSAGE, heading));
        }

    }

    /* Click method for "Continue" button */
    public HomePage clickOnContinueButton() {
        continueButton.click();
        /* Pass the driver to HomePage (POM) */
        return new HomePage(driver);
    }
}
