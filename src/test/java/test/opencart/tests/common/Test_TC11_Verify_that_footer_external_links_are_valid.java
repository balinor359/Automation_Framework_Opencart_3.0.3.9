package test.opencart.tests.common;

import test.opencart.pom.HomePage;
import org.testng.annotations.Test;
import test.opencart.pom.LoginPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;
import test.opencart.utilities.UserBuilder;

public class Test_TC11_Verify_that_footer_external_links_are_valid extends TestUtilities {

    /* The test uses the generated users from CSV file, initializes the web driver using the LoginPage object, call login page validator,
     * then calls the login method with specific user, after that validation method from the Homepage object.
     * Call method who verify all social links in footer are visible, clickable, and with right paths
     */

    @Test
    public void testFooterSocialLinks() {
        /* Print info data to log file */
        MyFileWriter.writeToLog("TC11: Verify that footer external links are valid");

        LoginPage loginPage = new LoginPage(TestUtilities.driver);
        loginPage.loginPageValidator();

        HomePage homePage = loginPage.testUserLogin(UserBuilder.fullUsersList.get(0));
        homePage.homepageValidator();

        homePage.socialLinksValidator();

    }
//
}
