package test.opencart.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.objects.Product;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends TestUtilities {

    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    private static final Object RETURN_NULL_OBJECT = null;
    public static String SEARCH_KEYWORD = "";
    public static ArrayList<Double> originalPriceList = new ArrayList<>();
    public static ArrayList<Double> productsAfterLoad = new ArrayList<>();
    private static String alertMessage = "";

    /* Declaring page elements */
    @FindBy(xpath = "//li[@qa='my_account']//a")
    private WebElement headerAccountDropdownToggle;
    @FindBy(id = "top-links")
    private WebElement headerLinksContainer;
    @FindBy(xpath = ".//a[@qa='register_link']")
    private WebElement headerRegisterLink;
    @FindBy(xpath = ".//a[@qa='login_link']")
    private WebElement headerLoginLink;
    @FindBy(xpath = ".//a[@qa='wishlist_link']")
    private WebElement headerWishlistLink;
    @FindBy(xpath = ".//a[@qa='shopping_cart_link']")
    private WebElement headerShoppingCartLink;
    @FindBy(xpath = ".//a[@qa='checkout_link']")
    private WebElement headerCheckoutLink;
    @FindBy(xpath = ".//a[@qa='homepage_link']")
    private WebElement homepageLink;
    @FindBy(xpath = ".//div[@id='common-home']//h2")
    private WebElement heading;
    @FindBy(xpath = ".//input[@qa='search-input']")
    private WebElement searchInput;
    @FindBy(xpath = ".//button[@qa='search-submit-btn']")
    private WebElement searchSubmitBtn;
    @FindBy(xpath = ".//a[@qa='footer-contact']")
    private WebElement contactPageLink;
    @FindBy(xpath = "//li[@qa='footer-info-page-link']//a[contains(text(),'About Us')]")
    private WebElement footerAboutLink;
    @FindBy(xpath = "//li[@qa='footer-info-page-link']//a[contains(text(),'Delivery Information')]")
    private WebElement footerDeliveryLink;
    @FindBy(xpath = "//li[@qa='footer-info-page-link']//a[contains(text(),'Privacy Policy')]")
    private WebElement footerPrivacyLink;
    @FindBy(xpath = "//li[@qa='footer-info-page-link']//a[contains(text(),'Terms & Conditions')]")
    private WebElement footerTermsLink;
    @FindBy(xpath = ".//div[@id='information-information']//h1")
    private WebElement infoPageHeading;
    @FindBy(xpath = ".//button[@qa='add-to-cart-btn']")
    private WebElement addToCartBtn;
    @FindBy(xpath = ".//button[@qa='add-to-wishlist-btn']")
    private WebElement addToWishlistBtn;
    @FindBy(xpath = ".//button[@qa='add-to-compare-btn']")
    private WebElement addToCompareBtn;
    @FindBy(xpath = "//div[contains(@class, 'product-layout')]")
    private List<WebElement> productListLocal;
    @FindBy(xpath = ".//div[@qa='minicart']/button")
    private WebElement minicartBtn;
    @FindBy(xpath = ".//p[@qa='text_empty']")
    private WebElement minicartTextEmpty;
    @FindBy(xpath = "//tr[@qa='minicart-single-product-row']")
    private List<WebElement> minicartProductRow;
    @FindBy(className = "alert")
    private WebElement alertText;

    /* This method return errorMessage */
    public String returnAlertText() {
        return alertMessage = alertText.getText().replace("×", "").trim();
    }

    /* This is constructor for home page using PageFactory for web-elements */
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method validate homepage heading */
    public void homepageValidator() {

        TestUtilities.waitVisible(driver, heading, 5);
        Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
        Assert.assertEquals(heading.getText(), GenericMessages.HOME_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
    }

    /* This method validate top links in header are visible */
    public void topLinksValidator() {
        Assert.assertTrue(headerLinksContainer.isDisplayed(), GenericMessages.TOP_LINKS_CONTAINER_MISSING_MESSAGE);
        headerAccountDropdownToggle.click();
        Assert.assertTrue(headerLoginLink.isDisplayed(), GenericMessages.LOGIN_LINK_MISSING_MESSAGE);
        Assert.assertTrue(headerRegisterLink.isDisplayed(), GenericMessages.REGISTER_LINK_MISSING_MESSAGE);
        Assert.assertTrue(headerWishlistLink.isDisplayed(), GenericMessages.WISHLIST_LINK_MISSING_MESSAGE);
        Assert.assertTrue(headerShoppingCartLink.isDisplayed(), GenericMessages.SHOPPING_CART_MISSING_MESSAGE);
        Assert.assertTrue(headerCheckoutLink.isDisplayed(), GenericMessages.CHECKOUT_LINK_MISSING_MESSAGE);
    }

    /* Click method for "Register" link in header */
    public RegistrationPage clickOnRegisterLink() {
        headerRegisterLink.click();
        /* Pass the driver to RegistrationPage (POM) */
        return new RegistrationPage(driver);
    }

    /* Click method for "Logo/homepage" link in header */
    public HomePage clickOnHomepageLink() {
        try {

            TestUtilities.scrollTo(driver, homepageLink);
            TestUtilities.waitClickable(driver, homepageLink, 5);
            Assert.assertTrue(homepageLink.isDisplayed(), GenericMessages.LOGO_MISSING_MESSAGE);

            TestUtilities.clickMethod(driver, homepageLink);

            /* Return driver to HomePage (POM) */
            return new HomePage(driver);
        } catch (Exception e) {
            e.printStackTrace();
            /* Return null driver if haven`t found the product */
            return (HomePage) RETURN_NULL_OBJECT;
        }
    }

    /* This method validate search bar is visible */
    public void searchBarValidator() {
        Assert.assertTrue(searchInput.isDisplayed(), GenericMessages.SEARCH_BAR_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(searchSubmitBtn.isDisplayed(), GenericMessages.SEARCH_BAR_SUBMIT_BTN_MISSING_MESSAGE);
    }

    /* This method place keyword into search bar input field */
    public void searchBarInputKeyword(String keyword) {
        /* Add keyword to public variable */
        SEARCH_KEYWORD = keyword;

        /* Clear, click and input data into the search field */
        searchInput.clear();
        searchInput.click();
        searchInput.sendKeys(keyword);
    }

    /* Click method for search bar submit button in header */
    public SearchResultPage searchBarSubmit() {
        /* Clear, search bar submit button */
        searchSubmitBtn.click();
        /* Pass the driver to RegistrationPage (POM) */
        return new SearchResultPage(driver);
    }

    /* This method validate footer contact page link is visible and have right text */
    public void footerContactPageLinkValidator() {
        Assert.assertTrue(contactPageLink.isDisplayed(), GenericMessages.CONTACT_PAGE_FOOTER_LINK_MISSING_MESSAGE);
        Assert.assertEquals(contactPageLink.getText(), GenericMessages.CONTACT_PAGE_FOOTER_LINK_TEXT, GenericMessages.CONTACT_PAGE_FOOTER_LINK_DIFFERENT_TEXT);
    }

    /* Click method for contact page link in footer */
    public ContactPage clickOnFooterContactPageLink() {
        contactPageLink.click();
        /* Return driver to ContactPage (POM) */
        return new ContactPage(driver);
    }

    /* This method validate footer information pages links is visible and have right text */
    public void footerInformationPagesLinksValidator() {
        Assert.assertTrue(footerAboutLink.isDisplayed(), GenericMessages.ABOUT_US_FOOTER_LINK_MISSING_MESSAGE);
        String fontColorAboutLink = footerAboutLink.getCssValue("color");
        String fontColorHexAboutLink = Color.fromString(fontColorAboutLink).asHex();
        Assert.assertEquals(fontColorHexAboutLink, GenericMessages.FOOTER_LINK_FONT_COLOR, GenericMessages.DIFFERENT_CSS_VALUE);

        Assert.assertTrue(footerDeliveryLink.isDisplayed(), GenericMessages.DELIVERY_FOOTER_LINK_MISSING_MESSAGE);
        String fontColorDeliveryLink = footerDeliveryLink.getCssValue("color");
        String fontColorHexDeliveryLink = Color.fromString(fontColorDeliveryLink).asHex();
        Assert.assertEquals(fontColorHexDeliveryLink, GenericMessages.FOOTER_LINK_FONT_COLOR, GenericMessages.DIFFERENT_CSS_VALUE);

        Assert.assertTrue(footerPrivacyLink.isDisplayed(), GenericMessages.PRIVACY_FOOTER_LINK_MISSING_MESSAGE);
        String fontColorPrivacyLink = footerPrivacyLink.getCssValue("color");
        String fontColorHexPrivacyLink = Color.fromString(fontColorPrivacyLink).asHex();
        Assert.assertEquals(fontColorHexPrivacyLink, GenericMessages.FOOTER_LINK_FONT_COLOR, GenericMessages.DIFFERENT_CSS_VALUE);

        Assert.assertTrue(footerTermsLink.isDisplayed(), GenericMessages.TERMS_FOOTER_LINK_MISSING_MESSAGE);
        String fontColorTermsLink = footerTermsLink.getCssValue("color");
        String fontColorHexTermsLink = Color.fromString(fontColorTermsLink).asHex();
        Assert.assertEquals(fontColorHexTermsLink, GenericMessages.FOOTER_LINK_FONT_COLOR, GenericMessages.DIFFERENT_CSS_VALUE);

    }


    /* Method who click footer "About Us" link */
    public void clickFooterAboutLink() {
        footerAboutLink.click();
    }

    /* Method who click footer "Delivery Information" link */
    public void clickFooterDeliveryLink() {
        footerDeliveryLink.click();
    }

    /* Method who click footer "Privacy Policy" link */
    public void clickFooterPrivacyLink() {
        footerPrivacyLink.click();
    }

    /* Method who click footer "Terms & Conditions" link */
    public void clickFooterTermsLink() {
        footerTermsLink.click();
    }

    /* Method who validate correctness of the footer link and print right messages in console and log file */
    public void validateAboutPage() {
        if (driver.getCurrentUrl().equals(GenericMessages.ABOUT_US_LINK_URL)) {
            System.out.println(GenericMessages.REDIRECT_IS_SUCCESSFUL);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_IS_SUCCESSFUL);
            Assert.assertEquals(infoPageHeading.getText(), GenericMessages.ABOUT_US_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
        } else {
            System.out.println(GenericMessages.REDIRECT_FAILED);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_FAILED);
            Assert.assertEquals(driver.getCurrentUrl(), GenericMessages.ABOUT_US_LINK_URL, GenericMessages.REDIRECT_FAILED);
        }
    }

    /* Method who validate correctness of the footer link and print right messages in console and log file */
    public void validateDeliveryPage() {
        if (driver.getCurrentUrl().equals(GenericMessages.DELIVERY_INFORMATION_LINK_URL)) {
            System.out.println(GenericMessages.REDIRECT_IS_SUCCESSFUL);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_IS_SUCCESSFUL);
            Assert.assertEquals(infoPageHeading.getText(), GenericMessages.DELIVERY_INFORMATION_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
        } else {
            System.out.println(GenericMessages.REDIRECT_FAILED);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_FAILED);
            Assert.assertEquals(driver.getCurrentUrl(), GenericMessages.DELIVERY_INFORMATION_LINK_URL, GenericMessages.REDIRECT_FAILED);
        }
    }

    /* Method who validate correctness of the footer link and print right messages in console and log file */
    public void validatePrivacyPage() {
        if (driver.getCurrentUrl().equals(GenericMessages.PRIVACY_POLICY_LINK_URL)) {
            System.out.println(GenericMessages.REDIRECT_IS_SUCCESSFUL);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_IS_SUCCESSFUL);
            Assert.assertEquals(infoPageHeading.getText(), GenericMessages.PRIVACY_POLICY_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
        } else {
            System.out.println(GenericMessages.REDIRECT_FAILED);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_FAILED);
            Assert.assertEquals(driver.getCurrentUrl(), GenericMessages.PRIVACY_POLICY_LINK_URL, GenericMessages.REDIRECT_FAILED);
        }
    }

    /* Method who validate correctness of the footer link and print right messages in console and log file */
    public void validateTermsPage() {
        if (driver.getCurrentUrl().equals(GenericMessages.TERMS_AND_CONDITIONS_LINK_URL)) {
            System.out.println(GenericMessages.REDIRECT_IS_SUCCESSFUL);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_IS_SUCCESSFUL);
            Assert.assertEquals(infoPageHeading.getText(), GenericMessages.TERMS_AND_CONDITIONS_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
        } else {
            System.out.println(GenericMessages.REDIRECT_FAILED);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_FAILED);
            Assert.assertEquals(driver.getCurrentUrl(), GenericMessages.TERMS_AND_CONDITIONS_LINK_URL, GenericMessages.REDIRECT_FAILED);
        }
    }

    /* Click method for login page link in header */
    public LoginPage clickOnLoginPageLink() {
        TestUtilities.waitClickable(driver, headerAccountDropdownToggle, 5);
        Assert.assertTrue(headerLinksContainer.isDisplayed(), GenericMessages.TOP_LINKS_CONTAINER_MISSING_MESSAGE);
        headerAccountDropdownToggle.click();

        TestUtilities.waitClickable(driver, headerLoginLink, 5);
        Assert.assertTrue(headerLoginLink.isDisplayed(), GenericMessages.LOGIN_LINK_MISSING_MESSAGE);
        headerLoginLink.click();

        /* Return driver to ContactPage (POM) */
        return new LoginPage(driver);
    }

    /* Method who add product to wishlist by submitted item name */
    public void addProductToWishlist(String productName) {
        /* Go through all products */
        for (WebElement product : productListLocal) {

            /* Save values for Name/Price/Image src for comparison in other pages */

            /* Take product - Name */
            WebElement innerProductName = product.findElement(By.cssSelector("div.caption h4 a"));

            /* Take product - Price */
            WebElement productPrice = product.findElement(By.cssSelector("p.price"));

            /* Take product - Image Src*/
            WebElement productImageSrc = product.findElement(By.cssSelector("img.img-responsive"));
            String imageUrl = extractImageUrlWithoutDimensionsAndFileType(productImageSrc.getAttribute("src"));

            /* Locate submitted product and create new product object */
            if (innerProductName.getText().equals(productName)) {

                /* Take product - Wishlist button */
                WebElement addToWishlistBtn = product.findElement(By.xpath(".//button[@qa='add-to-wishlist-btn']"));

                /* Scroll to add to wishlist button of the product and click it */
                TestUtilities.scrollToMethod(driver, addToWishlistBtn);

                addToWishlistBtn.click();

                /* Create new product with name, price and image src, and add it to the product list */
                Product newProduct = new Product(innerProductName.getText(), productPrice.getText(), imageUrl);
                Product.productList.add(newProduct);

            }
        }
    }

    /* Method to extract image URL without dimensions and file type using regex */
    public String extractImageUrlWithoutDimensionsAndFileType(String imageUrlWithDimensions) {
        // Define a regex pattern to match the image URL without dimensions
        String regexPattern = "(.*?)(-\\d+x\\d+)?(\\.\\w+)";
        // Replace the matched dimensions and file type with an empty string
        return imageUrlWithDimensions.replaceAll(regexPattern, "$1$3");
    }

    /* Method who validate notification for added product to wishlist */
    public void validateAddedProductToWishlist(String productName) {
        /* Log the alert and print it in the console */
        System.out.println("<<< Shows Alert: " + returnAlertText() + " >>>");
        MyFileWriter.writeToLog("<<< Shows Alert: " + returnAlertText() + " >>>");

        /* Validate that message contains product name */
        Assert.assertTrue(returnAlertText().contains(productName), GenericMessages.DIFFERENT_PRODUCT_ADDED_MESSAGE);

    }

    /* Click method for user wishlist page link in header */
    public WishlistPage clickOnHeaderWishlistPageLink() {
        Assert.assertTrue(headerWishlistLink.isDisplayed(), GenericMessages.WISHLIST_LINK_MISSING_MESSAGE);
        TestUtilities.scrollToMethod(driver, headerWishlistLink);
        TestUtilities.waitClickable(driver, headerWishlistLink, 5);
        headerWishlistLink.click();

        /* Return driver to WishlistPage (POM) */
        return new WishlistPage(driver);
    }

    /* Method who add product by submitted item name to the Product List */
    public void addProductToProductList(String productName) {

        /* Go through all products */
        for (WebElement product : productListLocal) {

            /* Save values for Name/Price/Image src for comparison in other pages */

            /* Take product - Name */
            WebElement innerProductName = product.findElement(By.cssSelector("div.caption h4 a"));

            /* Take product - Price */
            WebElement productPrice = product.findElement(By.cssSelector("p.price"));

            /* Take product - Image Src*/
            WebElement productImageSrc = product.findElement(By.cssSelector("img.img-responsive"));
            String imageUrl = extractImageUrlWithoutDimensionsAndFileType(productImageSrc.getAttribute("src"));

            /* Locate submitted product and create new product object */
            if (innerProductName.getText().equals(productName)) {

                /* Create new product with name, price and image src, and add it to the product list */
                Product newProduct = new Product(innerProductName.getText(), productPrice.getText(), imageUrl);
                Product.productList.add(newProduct);

            }
        }

    }

    /* Method who add product by submitted item name to the Cart */
    public void addProductToCart(String productName) {

        /* Go through all products */
        for (WebElement product : productListLocal) {

            /* Save values for Name/Price/Image src for comparison in other pages */

            /* Take product - Name */
            WebElement innerProductName = product.findElement(By.cssSelector("div.caption h4 a"));

            /* Locate submitted product and create new product object */
            if (innerProductName.getText().equals(productName)) {

                /* Take product - add-to-cart button */
                WebElement addToCartBtn = product.findElement(By.xpath(".//button[@qa='add-to-cart-btn']"));

                /* Scroll to add-to-cart button of the product and click it */
                TestUtilities.scrollToMethod(driver, addToCartBtn);

                addToCartBtn.click();

            }
        }
    }

    /* Click method who open product page */
    public ProductPage goToProductPage(String productName) {
//        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        /* Go through all products */
        for (WebElement product : productListLocal) {

            /* Take product - Name */
            WebElement innerProductName = product.findElement(By.cssSelector("div.caption h4 a"));

            /* Locate submitted product and create new product object */
            if (innerProductName.getText().equals(productName)) {

                innerProductName.click();

                /* Return driver to ContactPage (POM) */
                return new ProductPage(driver);
            }

        }
        /* Return null driver if haven`t found the product */
        return (ProductPage) RETURN_NULL_OBJECT;
    }

    /* Method who click Minicart button in header */
    public void clickMinicartButton() {
        TestUtilities.scrollToMethod(driver, minicartBtn);
        TestUtilities.waitVisible(driver, minicartBtn, 5);
        TestUtilities.waitClickable(driver, minicartBtn, 5);
        minicartBtn.click();
    }

    /* This method validate empty minicart message, is it visible and the same as provided */
    public void validateEmptyMinicartMessage(String message) {
        Assert.assertTrue(minicartTextEmpty.isDisplayed(), GenericMessages.CART_IS_EMPTY_MESSAGE_MISSING_MESSAGE);
        Assert.assertEquals(minicartTextEmpty.getText(), message, GenericMessages.CART_IS_EMPTY_MESSAGE_DIFFERENT_MESSAGE);
    }

    /* Method who validate and click "Checkout" link in header */
    public CheckoutPage clickOnHeaderCheckoutLink() {
        TestUtilities.scrollToMethod(driver, headerCheckoutLink);
        TestUtilities.waitClickable(driver, headerCheckoutLink, 5);
        Assert.assertTrue(headerCheckoutLink.isDisplayed(), GenericMessages.CHECKOUT_LINK_MISSING_MESSAGE);
        Assert.assertEquals(headerCheckoutLink.getText(), GenericMessages.CHECKOUT_LINK_TEXT, GenericMessages.CHECKOUT_LINK_DIFFERENT_TEXT_MESSAGE);
        headerCheckoutLink.click();
        /* Return driver to CheckoutPage (POM) */
        return new CheckoutPage(driver);
    }

    /* Method who validate and click "Shopping Cart" link in header */
    public CartPage clickOnHeaderShoppingCartLink() {
        TestUtilities.scrollToMethod(driver, headerShoppingCartLink);
        TestUtilities.waitClickable(driver, headerShoppingCartLink, 5);
        Assert.assertTrue(headerShoppingCartLink.isDisplayed(), GenericMessages.SHOPPING_CART_MISSING_MESSAGE);
        Assert.assertEquals(headerShoppingCartLink.getText(), GenericMessages.SHOPPING_CART_LINK_TEXT, GenericMessages.SHOPPING_CART_LINK_DIFFERENT_TEXT_MESSAGE);
        headerShoppingCartLink.click();
        /* Return driver to CartPage (POM) */
        return new CartPage(driver);
    }


    /* Method who goes through all minicart items and compare their name, images and price with items saved in productList */
    public void cartItemsValidator() {

        /* Go through all products saved in Cart */
        for (WebElement item : minicartProductRow) {

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

            String cartItemImageUrl = extractImageUrlWithoutDimensionsAndFileType(cartItemImageSrc.getAttribute("src"));

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
}
