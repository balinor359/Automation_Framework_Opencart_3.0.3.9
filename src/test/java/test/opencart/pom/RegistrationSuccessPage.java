package test.opencart.pom;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class RegistrationSuccessPage extends TestUtilities {
    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    public static final String SUCCESS_REGISTER_PAGE_HEADING = "Your Account Has Been Created!";

    /* Declaring page elements */
    @FindBy(xpath = "//div[@id='common-success']//h1")
    private WebElement heading;
    @FindBy(className = "btn-primary")
    private WebElement continueButton;
    @FindBy(xpath = ".//a[@qa='homepage_link']")
    private WebElement homepageLink;
    public RegistrationSuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void registrationSuccessPageValidator() {
        try {
            Assert.assertEquals(heading.getText(), SUCCESS_REGISTER_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
            Assert.assertTrue(continueButton.isDisplayed(), "Continue button is missing");
        } catch (NoSuchElementException e){
            System.out.println(String.format(GenericMessages.MISSING_ELEMENT_MESSAGE, heading));
            MyFileWriter.writeToLog(String.format(GenericMessages.MISSING_ELEMENT_MESSAGE, heading));

            Assert.fail(String.format(GenericMessages.MISSING_ELEMENT_MESSAGE, heading));
        }

    }
//    public HomePage clickOnHomepageLink() {
//        homepageLink.click();
//        /* Pass the driver to CheckoutInfoPage (POM) */
//        return new HomePage(driver);
//    }
    public AccountPage clickOnContinueButton() {
        continueButton.click();
        /* Pass the driver to CheckoutInfoPage (POM) */
        return new AccountPage(driver);
    }
}
