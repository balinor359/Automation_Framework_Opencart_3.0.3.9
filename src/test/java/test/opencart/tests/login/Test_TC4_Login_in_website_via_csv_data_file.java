package test.opencart.tests.login;

import com.opencsv.exceptions.CsvException;
import test.opencart.pom.HomePage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.opencart.pom.LoginPage;
import test.opencart.utilities.CsvHelper;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

import java.io.IOException;

public class Test_TC4_Login_in_website_via_csv_data_file extends TestUtilities {

    /* The test uses the generated users from CSV file, initializes the web driver using the LoginPage object, call login page validator,
     * then calls the login method provided users from CSV, after that validation method from the Homepage object to check if user successful longed in
     */

    /* Load CSV File */
    @DataProvider(name = "csvDataFile")
    public static Object[][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("saucedemo-files\\TestData.csv");
    }

    /* Read form provided CSV file */
    @Test(dataProvider = "csvDataFile")
    public void loginWithCsvFileDataInput(String username, String password) {
        /* Print info data to log file */
        MyFileWriter.writeToLog("TC4: Login in website via csv data file");

        LoginPage loginPage = new LoginPage(TestUtilities.driver);
        loginPage.loginPageValidator();

        HomePage homePage = loginPage.login(username, password);
        homePage.homepageValidator();

    }
}
