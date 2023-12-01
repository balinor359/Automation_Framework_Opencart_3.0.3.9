package test.opencart.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.utilities.TestUtilities;

public class AccountPage extends TestUtilities {
    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    public static final String ACCOUNT_PAGE_CONTAINER_MISSING = "The Account page container is missing!";

    /* Declaring page elements */
    @FindBy(xpath = "//div[@id='account-account']")
    private WebElement accountPageContainer;
    @FindBy(xpath = ".//a[@qa='homepage_link']")
    private WebElement homepageLink;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void accountPageValidator() {
        Assert.assertTrue(accountPageContainer.isDisplayed(), ACCOUNT_PAGE_CONTAINER_MISSING);
//        Assert.assertEquals(heading.getText(), SUCCESS_REGISTER_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
//        Assert.assertTrue(continueButton.isDisplayed(), "Continue button is missing");
    }
//    public HomePage clickOnHomepageLink() {
//        homepageLink.click();
//        /* Pass the driver to CheckoutInfoPage (POM) */
//        return new HomePage(driver);
//    }

}
