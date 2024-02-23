package test.opencart.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.utilities.TestUtilities;

public class CartPage extends TestUtilities {
    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    private static final String CART_PAGE_HEADING = "Shopping Cart";

    /* Declaring page elements */
    @FindBy(xpath = ".//div[@id='checkout-cart']//h1")
    private WebElement heading;
    @FindBy(xpath = ".//div[@id='error-not-found']//h1")
    private WebElement headingEmptyCart;
    @FindBy(xpath = ".//p[@qa='text_error']")
    private WebElement textEmptyCart;


    /* This is constructor for cart page using PageFactory for web-elements */
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method validate empty cart page heading and error message are visible and with right text*/
    public void emptyCartPageValidator(String message) {
        Assert.assertTrue(headingEmptyCart.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
        Assert.assertEquals(headingEmptyCart.getText(), CART_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);

        Assert.assertTrue(textEmptyCart.isDisplayed(), GenericMessages.CART_IS_EMPTY_MESSAGE_MISSING_MESSAGE);
        Assert.assertEquals(textEmptyCart.getText(), message, GenericMessages.CART_IS_EMPTY_MESSAGE_DIFFERENT_MESSAGE);


    }

}
