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

public class WishlistPage extends TestUtilities {
    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */

    /* Declaring page elements */
    @FindBy(xpath = "//div[@id='account-wishlist']")
    private WebElement wishlistPageContainer;
    @FindBy(xpath = "//div[@id='account-wishlist']//h2")
    private WebElement heading;
    @FindBy(xpath = ".//a[@qa='homepage_link']")
    private WebElement homepageLink;
    @FindBy(xpath = "//tr[@qa='single-product']")
    private List<WebElement> wishlistItems;
    @FindBy(xpath = "//a[@qa='product-name']")
    private WebElement wishlistItemsName;
    @FindBy(xpath = "//div[@qa='product-price']")
    private WebElement wishlistItemsPrice;
    @FindBy(xpath = "//img[@qa='product-img']")
    private WebElement wishlistItemsImage;

    /* This is constructor for account page using PageFactory for web-elements */
    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method validate account page container is visible */
    public void wishlistPageValidator() {
        /* Check if the current page is the wishlist page */
        if (driver.getCurrentUrl().equals(GenericMessages.WISHLIST_PAGE_URL)) {
            Assert.assertTrue(wishlistPageContainer.isDisplayed(), GenericMessages.WISHLIST_PAGE_CONTAINER_MISSING);
            Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);

            /* Validate Wishlist page heading contains  */
            Assert.assertEquals(heading.getText(), GenericMessages.WISHLIST_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);

            /* If wishlist have items, check their name,image and price */
            if (wishlistItems.size() > 0) {
                System.out.println(GenericMessages.WISHLIST_HAVE_ITEMS_TEXT);
                MyFileWriter.writeToLog(GenericMessages.WISHLIST_HAVE_ITEMS_TEXT);
                wishlistItemsValidator();
            } else {
                System.out.println(GenericMessages.WISHLIST_IS_EMPTY_TEXT);
                MyFileWriter.writeToLog(GenericMessages.WISHLIST_IS_EMPTY_TEXT);
            }
        } else {
            System.out.println(GenericMessages.REDIRECT_FAILED);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_FAILED);
        }

    }

    /* Method who goes through all wishlist items and compare their name, images and price with items saved in productList */
    public void wishlistItemsValidator() {

        /* Go through all products saved in WISHLIST */
        for (WebElement item : wishlistItems) {

            /* Take element - Name */
            WebElement wishlistItemName = item.findElement(By.xpath(".//a[@qa='product-name']"));
            Assert.assertTrue(wishlistItemName.isDisplayed(), GenericMessages.PRODUCTS_NAME_MISSING_MESSAGE);
            String wishlistItemNameText = wishlistItemName.getText();

            /* Take element - Price */
            WebElement wishlistItemPrice = item.findElement(By.xpath(".//div[@qa='product-price']"));
            Assert.assertTrue(wishlistItemPrice.isDisplayed(), GenericMessages.PRODUCT_PRICE_MISSING_MESSAGE);

            /* Take element - Image */
            WebElement wishlistItemImageSrc = item.findElement(By.xpath(".//img[@qa='product-img']"));
            Assert.assertTrue(wishlistItemImageSrc.isDisplayed(), GenericMessages.PRODUCT_IMAGE_MISSING_MESSAGE);

            HomePage homePage = new HomePage(TestUtilities.driver);
            String wishlistItemImageUrl = homePage.extractImageUrlWithoutDimensionsAndFileType(wishlistItemImageSrc.getAttribute("src"));

            /* Go through all products in productList */
            for (Product product : Product.productList) {

                /* If element from cart match with element from productList compare their name and price */
                if (wishlistItemNameText.equals(product.getName())) {

                    Assert.assertEquals(wishlistItemName.getText(), product.getName(), GenericMessages.PRODUCTS_NAME_IS_DIFFERENT_MESSAGE);
                    Assert.assertEquals(wishlistItemPrice.getText(), product.getPrice(), GenericMessages.PRODUCT_PRICE_IS_DIFFERENT_MESSAGE);
                    Assert.assertEquals(wishlistItemImageUrl, product.getImageSrc(), GenericMessages.PRODUCT_IMAGE_IS_DIFFERENT_MESSAGE);

                    System.out.println(GenericMessages.WISHLIST_ITEMS_ARE_VALID_TEXT);
                    MyFileWriter.writeToLog(GenericMessages.WISHLIST_ITEMS_ARE_VALID_TEXT);
                }
            }
        }
    }

}
