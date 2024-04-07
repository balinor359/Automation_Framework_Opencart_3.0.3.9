package test.opencart.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class CheckoutPage extends TestUtilities {
    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    private static final String CHECKOUT_PAGE_HEADING = "Checkout";
    private static final String CHECKOUT_PAGE_CONTAINER_MISSING = "The Checkout page container is missing!";
    private static final String CHECKOUT_PAGE_URL = "https://opencart-test.test/index.php?route=checkout/checkout";

    /* Declaring page elements */
    @FindBy(xpath = ".//div[@id='checkout-checkout']//h1")
    private WebElement heading;
    @FindBy(xpath = "//div[@id='checkout-checkout']")
    private WebElement checkoutPageContainer;
    @FindBy(xpath = ".//input[@qa='registerInput']")
    private WebElement registerRadioBtn;
    @FindBy(xpath = ".//input[@qa='guestInput']")
    private WebElement guestRadioBtn;
    @FindBy(xpath = ".//input[@id='button-account']")
    private WebElement btnAccount;

    /* This is constructor for cart page using PageFactory for web-elements */
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    /* This method validate checkout page heading exist and assure that redirect is successfully */
    public void checkoutPageValidator() {

        /* Check if the current page is the checkout page */
        if (driver.getCurrentUrl().equals(CHECKOUT_PAGE_URL)) {

            /* Validate cart page container and heading exist  */
            Assert.assertTrue(checkoutPageContainer.isDisplayed(), CHECKOUT_PAGE_CONTAINER_MISSING);
            Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
            Assert.assertEquals(heading.getText(), CHECKOUT_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);

        } else {
            System.out.println(GenericMessages.REDIRECT_FAILED);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_FAILED);
        }
    }

    /* Method who select "Guest Checkout" radio button */
    public void clickOnGuestCheckoutRadioBtn() {
        TestUtilities.scrollToMethod(driver, guestRadioBtn);
        TestUtilities.waitClickable(driver, guestRadioBtn, 5);
        Assert.assertTrue(guestRadioBtn.isDisplayed(), GenericMessages.RADIO_BUTTON_MISSING_MESSAGE);

        // Get the parent element, which is the label
        WebElement labelElement = guestRadioBtn.findElement(By.xpath("./.."));
        Assert.assertEquals(labelElement.getText(), GenericMessages.RADIO_BUTTON_GUEST_TEXT, GenericMessages.RADIO_BUTTON_DIFFERENT_TEXT_MESSAGE);
        guestRadioBtn.click();

    }

    /* Method who click on Step:1 "Continue" button */
    public void clickOnStepOneContinueBtn() {
        TestUtilities.scrollToMethod(driver, btnAccount);
        TestUtilities.waitClickable(driver, btnAccount, 5);
        Assert.assertTrue(btnAccount.isDisplayed(), GenericMessages.CONTINUE_BUTTON_MISSING_MESSAGE);
        Assert.assertEquals(btnAccount.getAttribute("value"), GenericMessages.CONTINUE_BUTTON_TEXT, GenericMessages.CONTINUE_BUTTON_DIFFERENT_TEXT_MESSAGE);
        btnAccount.click();

    }


}
