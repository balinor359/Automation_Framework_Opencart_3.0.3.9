package test.opencart.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

import java.time.Duration;

public class LoginPage extends TestUtilities {

    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    private static final Object RETURN_NULL_OBJECT = null;
    private static String errorMessage = "";

    /* Declaring page elements */
    @FindBy(xpath = ".//h2[@qa='login_heading']")
    private WebElement login_heading;
    @FindBy(xpath = ".//form[@qa='login_form']")
    private WebElement loginForm;
    @FindBy(id = "input-email")
    private WebElement inputEmail;
    @FindBy(id = "input-password")
    private WebElement inputPassword;
    @FindBy(xpath = ".//input[@qa='login_btn']")
    private WebElement loginBtn;
    @FindBy(className = "alert")
    private WebElement errorText;

    /* This is constructor for login page using PageFactory for web-elements */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method return errorMessage */
    public String returnErrorText() {
        return errorMessage = errorText.getText();
    }

    /* This method validate login page heading and form elements are visible */
    public void loginPageValidator() {
        Assert.assertTrue(login_heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
        Assert.assertEquals(login_heading.getText(), GenericMessages.LOGIN_PAGE_FORM_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
        Assert.assertTrue(loginForm.isDisplayed(), GenericMessages.LOGIN_FORM_MISSING_MESSAGE);
        Assert.assertTrue(inputEmail.isDisplayed(), GenericMessages.EMAIL_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(inputPassword.isDisplayed(), GenericMessages.PASSWORD_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(loginBtn.isDisplayed(), GenericMessages.LOGIN_BUTTON_MISSING_MESSAGE);
    }

    /* This method fill login form */
    public void fillLoginForm(String email, String password) {

        /* Clear, click and input data into the field */
        inputEmail.clear();
        inputEmail.click();
        inputEmail.sendKeys(email);

        /* Clear, click and input data into the field */
        inputPassword.clear();
        inputPassword.click();
        inputPassword.sendKeys(password);
    }

    /* Click method for "Continue" button */
    public AccountPage clickOnLoginButton() {

        try {
            waitClickable(driver, loginBtn, 5);
            loginBtn.click();

            /* Check if the current page is the login page */
            if (driver.getCurrentUrl().equals(GenericMessages.LOGIN_PAGE_URL)) {

                /* Throw a runtime exception, log the error and stop the test */
                System.out.println("Login failed. Error: " + returnErrorText());
                MyFileWriter.writeToLog("Login failed. Error: " + returnErrorText());

                Assert.assertEquals(driver.getCurrentUrl(), GenericMessages.USER_ACCOUNT_PAGE_URL, GenericMessages.LOGIN_FAILED_MESSAGE + " Error: " + returnErrorText());

                throw new RuntimeException("Login failed. Error: " + returnErrorText());
            } else {
                return new AccountPage(driver);
            }
        } catch (NullPointerException e) {
            /* Throw a runtime exception, log and console print the exception */
            System.err.println("NullPointerException: " + e.getMessage());
            MyFileWriter.writeToLog("NullPointerException: " + e.getMessage());

            throw new RuntimeException("Login failed due to a NullPointerException.", e);
        }
    }
}
