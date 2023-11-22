package test.opencart.tests.menu;

import test.opencart.pom.HomePage;
import test.opencart.pom.LoginPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;
import test.opencart.utilities.UserBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_TC8_4_1_Test_menu_link_reset_app_state_home_page extends TestUtilities {

    /* The test uses the generated users from CSV file, initializes the web driver using the LoginPage object, call login page validator,
     * then calls the login method with specific user, after that validation method from the Homepage object.
     * Add 2 products to the cart and validate is that action is done.
     * Check if menu links are visible, then click on "Reset App State" button.
     * Check is the cart empty, and all add to cart buttons are in right state.
     */

    @Test
    public void testMenuResetLink() {
        /* Print info data to log file */
        MyFileWriter.writeToLog("TC8.4.1: Test menu link reset app state home page");

        LoginPage loginPage = new LoginPage(TestUtilities.driver);
        loginPage.loginPageValidator();
        HomePage homePage = loginPage.testUserLogin(UserBuilder.fullUsersList.get(0));
        homePage.homepageValidator();

        homePage.addItemToTheCartAndSaveValues("sauce-labs-bolt-t-shirt");
        homePage.addItemToTheCartAndSaveValues("sauce-labs-bike-light");

        Assert.assertEquals(homePage.getItemsInTheCart(), 2, HomePage.CART_BADGE_WRONG_AMOUNT);

        homePage.menuLinksValidator();
        homePage.clickMenuResetButton();

        Assert.assertEquals(homePage.getItemsInTheCart(), 0, HomePage.CART_BADGE_WRONG_AMOUNT);

        homePage.addToCartButtonValidator("sauce-labs-bolt-t-shirt");
        homePage.addToCartButtonValidator("sauce-labs-bike-light");

    }

}
