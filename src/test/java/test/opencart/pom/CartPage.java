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
    @FindBy(xpath = ".//td[@qa='product-qty']//div//input")
    private WebElement inputQty;
    @FindBy(xpath = ".//button[@qa='button_update']")
    private WebElement updateItemQtyInShoppingCart;
    @FindBy(xpath = "//div[@qa='alert-success']")
    private WebElement successMsg;

    /* This is constructor for cart page using PageFactory for web-elements */
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method validate empty cart page heading and error message are visible and with right text */
    public void emptyCartPageValidator(String message) {
        Assert.assertTrue(headingEmptyCart.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
        Assert.assertEquals(headingEmptyCart.getText(), GenericMessages.CART_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);

        Assert.assertTrue(textEmptyCart.isDisplayed(), GenericMessages.CART_IS_EMPTY_MESSAGE_MISSING_MESSAGE);
        Assert.assertEquals(textEmptyCart.getText(), message, GenericMessages.CART_IS_EMPTY_MESSAGE_DIFFERENT_MESSAGE);

    }

    /* This method validate cart page heading exist and product list is full */
    public void cartPageValidator() {

        /* Check if the current page is the cart page */
        if (driver.getCurrentUrl().equals(GenericMessages.CART_PAGE_URL)) {

            /* Validate cart page container and heading exist  */
            Assert.assertTrue(cartPageContainer.isDisplayed(), GenericMessages.CART_PAGE_CONTAINER_MISSING);
            Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
            Assert.assertEquals(heading.getText(), GenericMessages.CART_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);

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
                removeItemFromShoppingCart.click();

            }
        }
    }

    /* Method who update product Qty in the Cart by submitted item name */
    public void updateProductQtyInCart(String productName, String productQty) {

        /* Go through all products saved in Cart */
        for (WebElement product : cartProductRow) {

            /* Take element - Name */
            WebElement cartItemName = product.findElement(By.xpath(".//td[@qa='product-name']/a"));
            Assert.assertTrue(cartItemName.isDisplayed(), GenericMessages.PRODUCTS_NAME_MISSING_MESSAGE);
            String cartItemNameText = cartItemName.getText();

            /* Locate submitted product and update it */
            if (cartItemNameText.equals(productName)) {

                inputQty.click();
                inputQty.clear();
                inputQty.sendKeys(productQty);

                /* Update product Qty - update button */
                updateItemQtyInShoppingCart.click();

            }
        }
    }

    /* Method who validate product Qty in the Cart is changed */
    public void validateProductQtyInCart(String productName, int productQty) {

        /* Go through all products saved in Cart */
        for (WebElement product : cartProductRow) {

            /* Take element - Name */
            WebElement cartItemName = product.findElement(By.xpath(".//td[@qa='product-name']/a"));
            Assert.assertTrue(cartItemName.isDisplayed(), GenericMessages.PRODUCTS_NAME_MISSING_MESSAGE);
            String cartItemNameText = cartItemName.getText();

            /* Locate submitted product and update it */
            if (cartItemNameText.equals(productName)) {
                WebElement productPrice = product.findElement(By.xpath(".//td[@qa='product-price']"));
                WebElement productTotal = product.findElement(By.xpath(".//td[@qa='column-total']"));

                // Get the text from WebElement
                String productPriceText = productPrice.getText();
                String productTotalText = productTotal.getText();

                // Remove the currency symbol and any non-numeric characters from the strings
                double price = Double.parseDouble(productPriceText.replaceAll("[^\\d.]+", ""));
                double total = Double.parseDouble(productTotalText.replaceAll("[^\\d.]+", ""));


                double multipliedPrice = price * productQty;

                System.out.println("Product price: " + price + " | Product Qty: " + productQty + " | Product Total: " + total + " | Product Multiplied Price: " + multipliedPrice);
                MyFileWriter.writeToLog("Product price: " + price + " | Product Qty: " + productQty + " | Product Total: " + total + " | Product Multiplied Price: " + multipliedPrice);

                Assert.assertEquals(total, multipliedPrice, GenericMessages.WRONG_CALCULATIONS_TEXT);

            }
        }
    }

    /* This method validate success message, is it visible and the same as provided */
    public void validateSuccessMessageForModifiedCart() {
        TestUtilities.waitVisible(driver, successMsg, 5);
        Assert.assertTrue(successMsg.isDisplayed(), GenericMessages.SUCCESS_MESSAGE_MISSING_MESSAGE);

        String alertMessage = successMsg.getText().replace("×", "").trim();
        Assert.assertEquals(alertMessage, GenericMessages.SUCCESS_MESSAGE_TEXT, GenericMessages.SUCCESS_MESSAGE_DIFFERENT_MESSAGE);
    }

}
