package test.opencart.tests.checkout;

import test.opencart.pom.CartPage;
import test.opencart.pom.HomePage;
import test.opencart.pom.LoginPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;
import test.opencart.utilities.UserBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_TC5_Add_products_in_the_cart extends TestUtilities {

    /* The test uses the generated users from CSV file, initializes the web driver using the LoginPage object, call login page validator,
     * then calls the login method with specific user, after that validation method from the Homepage object.
     * Select product, enter in product page, and validate that product page items are visible, and the same as in homepage
     * Add 2 products to the cart and validate is the cart amount changed.
     * Navigate to cart page and validate the added product is with right name and price.
     */

    @Test
    public void verifyProductsAddedToTheCart() {
        /* Print info data to log file */
        MyFileWriter.writeToLog("TC5: Add products in the cart");

        LoginPage loginPage = new LoginPage(TestUtilities.driver);
        loginPage.loginPageValidator();

        HomePage homePage = loginPage.testUserLogin(UserBuilder.fullUsersList.get(0));
        homePage.homepageValidator();

        homePage.addItemToTheCartAndSaveValues("sauce-labs-backpack");
        homePage.addItemToTheCartAndSaveValues("test.allthethings()-t-shirt-(red)");

        Assert.assertEquals(homePage.getItemsInTheCart(), 2, HomePage.CART_BADGE_WRONG_AMOUNT);

        CartPage cartPage = homePage.clickOnCartButton();
        cartPage.cartPageValidator();

    }
}
