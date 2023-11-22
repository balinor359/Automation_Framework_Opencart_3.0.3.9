package test.opencart.tests.menu;

import test.opencart.pom.HomePage;
import test.opencart.pom.LoginPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;
import test.opencart.utilities.UserBuilder;
import org.testng.annotations.Test;

public class Test_TC8_3_Test_menu_link_logout extends TestUtilities {

    /* The test uses the generated users from CSV file, initializes the web driver using the LoginPage object, call login page validator,
     * then calls the login method with specific user, after that validation method from the Homepage object.
     * Check if menu links are visible and click on "Logout" link, and check did user logout successful.
     */

    @Test
    public void testMenuLogoutLink() {
        /* Print info data to log file */
        MyFileWriter.writeToLog("TC8.3: Test menu link logout");

        LoginPage loginPage = new LoginPage(TestUtilities.driver);
        loginPage.loginPageValidator();

        HomePage homePage = loginPage.testUserLogin(UserBuilder.fullUsersList.get(0));
        homePage.homepageValidator();

        homePage.menuLinksValidator();
        homePage.clickMenuLogoutButton();

        loginPage.successfulLogout();
        loginPage.loginPageValidator();

    }

}
