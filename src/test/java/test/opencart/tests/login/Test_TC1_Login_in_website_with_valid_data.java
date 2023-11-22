package test.opencart.tests.login;

import test.opencart.utilities.MyFileWriter;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.opencart.utilities.TestUtilities;

public class Test_TC1_Login_in_website_with_valid_data extends TestUtilities {

    /* The test uses the generated users from CSV file, initializes the web driver using the LoginPage object, call login page validator,
     * then calls the login method with specific user, after that validation method from the Homepage object
     */

    @Test
    public void loginWithValidDataInput() {
        /* Print info data to log file */
        MyFileWriter.writeToLog("TC1: Login in website with valid data");

        driver.getTitle();
        Assert.assertEquals(driver.getTitle(),"Test Opencart", "Tittle is wrong");

    }
}
