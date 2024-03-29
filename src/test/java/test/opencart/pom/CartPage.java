package test.opencart.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.objects.Product;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

import java.util.List;

public class CartPage extends TestUtilities {
    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    private static final String CART_PAGE_HEADING = "Shopping Cart";
    private static final String CART_PAGE_CONTAINER_MISSING = "The Cart page container is missing!";
    private static final String CART_PAGE_URL = "https://opencart-test.test/index.php?route=checkout/cart";

    /* Declaring page elements */
    @FindBy(xpath = ".//div[@id='checkout-cart']//h1")
    private WebElement heading;
    @FindBy(xpath = ".//div[@id='error-not-found']//h1")
    private WebElement headingEmptyCart;
    @FindBy(xpath = ".//p[@qa='text_error']")
    private WebElement textEmptyCart;
    @FindBy(xpath = "//tr[@qa='cart-single-product-row']")
    private List<WebElement> cartProductRow;
    @FindBy(xpath = "//div[@id='checkout-cart']")
    private WebElement cartPageContainer;
    @FindBy(xpath = ".//button[@qa='button_remove']")
    private WebElement removeItemFromShoppingCart;

    /* This is constructor for cart page using PageFactory for web-elements */
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method validate empty cart page heading and error message are visible and with right text */
    public void emptyCartPageValidator(String message) {
        Assert.assertTrue(headingEmptyCart.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
        Assert.assertEquals(headingEmptyCart.getText(), CART_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);

        Assert.assertTrue(textEmptyCart.isDisplayed(), GenericMessages.CART_IS_EMPTY_MESSAGE_MISSING_MESSAGE);
        Assert.assertEquals(textEmptyCart.getText(), message, GenericMessages.CART_IS_EMPTY_MESSAGE_DIFFERENT_MESSAGE);

    }

    /* This method validate cart page heading exist and product list is full */
    public void cartPageValidator() {

        /* Check if the current page is the cart page */
        if (driver.getCurrentUrl().equals(CART_PAGE_URL)) {

            /* Validate cart page container and heading exist  */
            Assert.assertTrue(cartPageContainer.isDisplayed(), CART_PAGE_CONTAINER_MISSING);
            Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
            Assert.assertEquals(heading.getText(), CART_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);

            /* If cart product list have items, check their name,image and price */
            if (cartProductRow.size() > 0) {
                System.out.println(GenericMessages.CART_HAVE_ITEMS_TEXT);
                MyFileWriter.writeToLog(GenericMessages.CART_HAVE_ITEMS_TEXT);
                cartItemsValidator();

            } else {
                System.out.println(GenericMessages.CART_IS_EMPTY_TEXT);
                MyFileWriter.writeToLog(GenericMessages.CART_IS_EMPTY_TEXT);
            }
        } else {
            System.out.println(GenericMessages.REDIRECT_FAILED);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_FAILED);
        }
    }

    /* Method who goes through all Cart items and compare their name, images and price with items saved in productList */
    public void cartItemsValidator() {

        /* Go through all products saved in Cart */
        for (WebElement item : cartProductRow) {

            /* Take element - Name */
            WebElement cartItemName = item.findElement(By.xpath(".//td[@qa='product-name']/a"));
            Assert.assertTrue(cartItemName.isDisplayed(), GenericMessages.PRODUCTS_NAME_MISSING_MESSAGE);
            String cartItemNameText = cartItemName.getText();

            /* Take element - Price */
            WebElement cartItemPrice = item.findElement(By.xpath(".//td[@qa='product-price']"));
            Assert.assertTrue(cartItemPrice.isDisplayed(), GenericMessages.PRODUCT_PRICE_MISSING_MESSAGE);

            /* Take element - Image */
            WebElement cartItemImageSrc = item.findElement(By.xpath(".//td[@qa='product-image']/a/img"));
            Assert.assertTrue(cartItemImageSrc.isDisplayed(), GenericMessages.PRODUCT_IMAGE_MISSING_MESSAGE);

            HomePage homePage = new HomePage(TestUtilities.driver);
            String cartItemImageUrl = homePage.extractImageUrlWithoutDimensionsAndFileType(cartItemImageSrc.getAttribute("src"));

            /* Go through all products in productList */
            for (Product product : Product.productList) {

                /* If element from cart match with element from productList compare their name and price */
                if (cartItemNameText.equals(product.getName())) {

                    Assert.assertEquals(cartItemName.getText(), product.getName(), GenericMessages.PRODUCTS_NAME_IS_DIFFERENT_MESSAGE);
                    Assert.assertEquals(cartItemPrice.getText(), product.getPrice(), GenericMessages.PRODUCT_PRICE_IS_DIFFERENT_MESSAGE);
                    Assert.assertEquals(cartItemImageUrl, product.getImageSrc(), GenericMessages.PRODUCT_IMAGE_IS_DIFFERENT_MESSAGE);

                    System.out.println(GenericMessages.CART_ITEMS_ARE_VALID_TEXT);
                    MyFileWriter.writeToLog(GenericMessages.CART_ITEMS_ARE_VALID_TEXT);
                }
            }
        }
    }

    /* Method who remove product from the Cart by submitted item name */
    public void removeProductFromCart(String productName) {

        /* Go through all products saved in Cart */
        for (WebElement product : cartProductRow) {

            /* Take element - Name */
            WebElement cartItemName = product.findElement(By.xpath(".//td[@qa='product-name']/a"));
            Assert.assertTrue(cartItemName.isDisplayed(), GenericMessages.PRODUCTS_NAME_MISSING_MESSAGE);
            String cartItemNameText = cartItemName.getText();

            /* Locate submitted product and remove it */
            if (cartItemNameText.equals(productName)) {

                /* Remove product - remove button */
                WebElement removeFromCartBtn = product.findElement(By.xpath(".//button[@qa='button_remove']"));

                removeFromCartBtn.click();

            }
        }
    }

}
