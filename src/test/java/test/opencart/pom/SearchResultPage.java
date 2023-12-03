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

public class SearchResultPage extends TestUtilities {
    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    private static final String SEARCH_PAGE_HEADING_STRING = "Search - ";
    private List<WebElement> productListLocal = (TestUtilities.driver).findElements(By.className("product-layout"));

    /* Declaring page elements */
    @FindBy(xpath = "//div[@id='product-search']//h1")
    private WebElement heading;

    /* This is constructor for search result page using PageFactory for web-elements */
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method validate search result page container is visible */
    public void searchResultPageValidator() {
        Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);

        /* Validate Search result page heading contains "Search - " */
        String actualHeading = heading.getText();
        Assert.assertTrue(actualHeading.contains(SEARCH_PAGE_HEADING_STRING), GenericMessages.DIFFERENT_PAGE_HEADING);
    }

    public void getAllProductResult() {
        /* Go through all products */
        for (WebElement product : productListLocal) {

            /* Take product - Name */
            WebElement itemName = product.findElement(By.xpath(".//div[@class='caption']//h4//a"));

            /* Take product - Price */
            WebElement itemPrice = product.findElement(By.xpath(".//p[@class='price']"));

            /* Take product - Image Src */
            WebElement itemImageSrc = product.findElement(By.xpath(".//div[@class='image']//a//img"));

            /* Create new product with name, price and image src, and add it to the product list */
            Product newProduct = new Product(itemName.getText(), itemPrice.getText(), itemImageSrc.getAttribute("src"));
            Product.productList.add(newProduct);

        }
    }

    /* Validation for all products in productList, are they contain the provided keyword */
    public void validateAllProductResultByKeyword() {
        /* Go through all products in productList */
        for (Product product : Product.productList) {

            /* Validate that the product name contain provided keyword */
            Assert.assertTrue(product.getName().contains(HomePage.SEARCH_KEYWORD), GenericMessages.KEYWORD_IN_PRODUCT_NAME);

            /* Log and console print the products names */
            System.out.println("Search result - Product name: " + product.getName());
            MyFileWriter.writeToLog("Search result - Product name: " + product.getName());
        }
    }


}
