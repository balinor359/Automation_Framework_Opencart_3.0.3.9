package test.opencart.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

import java.time.Duration;

public class RegistrationPage extends TestUtilities {

    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    private static final String ADD_TO_CART_LOCATOR = "//button[@id='add-to-cart-%s']";
    private static final String REMOVE_FROM_CART_LOCATOR = "//button[@id='remove-%s']";
    public static final String REGISTER_PAGE_HEADING = "Register Account";
    public static final String REGISTRATION_FAILED_MESSAGE = "Registration failed!";
    private static final String REGISTER_PAGE_URL = "https://opencart-test.test/index.php?route=account/register";
    private static final String REGISTER_SUCCESS_PAGE_URL = "https://opencart-test.test/index.php?route=account/success";

    private static final Object RETURN_NULL_OBJECT = null;
    private static String errorMessage = "";

    /* Declaring page elements */
    @FindBy(xpath = "//div[@id='account-register']//h1")
    private WebElement heading;
    @FindBy(xpath = ".//form[@qa='registration_form']")
    private WebElement registrationForm;
    @FindBy(id = "input-firstname")
    private WebElement inputFirstname;
    @FindBy(id = "input-lastname")
    private WebElement inputLastname;
    @FindBy(id = "input-email")
    private WebElement inputEmail;
    @FindBy(id = "input-telephone")
    private WebElement inputTelephone;
    @FindBy(id = "input-password")
    private WebElement inputPassword;
    @FindBy(id = "input-confirm")
    private WebElement inputConfirmPassword;
    @FindBy(xpath = ".//input[@qa='newsletter_yes']")
    private WebElement newsletterSubscribeYes;
    @FindBy(xpath = ".//input[@qa='newsletter_no']")
    private WebElement newsletterSubscribeNo;
    @FindBy(xpath = ".//input[@qa='agree_policy_checkbox']")
    private WebElement agreePolicyCheckbox;
    @FindBy(xpath = ".//input[@qa='register_button_continue']")
    private WebElement registerBtnContinue;
    @FindBy(className = "alert")
    private WebElement errorText;

    /* This is constructor for home page using PageFactory for web-elements */
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    /* This method return errorMessage */
    public String returnErrorText() {
        return errorMessage = errorText.getText();
    }
    public void registrationPageValidator() {
        Assert.assertEquals(heading.getText(), REGISTER_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
        Assert.assertTrue(registrationForm.isDisplayed(), "Registration form is missing");
        Assert.assertTrue(inputFirstname.isDisplayed(), "First name input is missing");
        Assert.assertTrue(inputLastname.isDisplayed(), "Last name input is missing");
        Assert.assertTrue(inputEmail.isDisplayed(), "Email input is missing");
        Assert.assertTrue(inputTelephone.isDisplayed(), "Telephone number input is missing");
        Assert.assertTrue(inputPassword.isDisplayed(), "Password input is missing");
        Assert.assertTrue(inputConfirmPassword.isDisplayed(), "Confirm password input is missing");
        Assert.assertTrue(agreePolicyCheckbox.isDisplayed(), "Privacy Policy checkbox is missing");
        Assert.assertTrue(registerBtnContinue.isDisplayed(), "Continue button is missing");
    }
    public void fillRegistrationForm(String firstName, String lastName, String email, String telephone, String password) {

        /* Clear, click and input data into the field */
        inputFirstname.clear();
        inputFirstname.click();
        inputFirstname.sendKeys(firstName);

        /* Clear, click and input data into the field */
        inputLastname.clear();
        inputLastname.click();
        inputLastname.sendKeys(lastName);

        /* Clear, click and input data into the field */
        inputEmail.clear();
        inputEmail.click();
        inputEmail.sendKeys(email);

        /* Clear, click and input data into the field */
        inputTelephone.clear();
        inputTelephone.click();
        inputTelephone.sendKeys(telephone);

        /* Clear, click and input data into the field */
        inputPassword.clear();
        inputPassword.click();
        inputPassword.sendKeys(password);

        /* Clear, click and input data into the field */
        inputConfirmPassword.clear();
        inputConfirmPassword.click();
        inputConfirmPassword.sendKeys(password);

        /* Subscribe user for the newsletter */
        newsletterSubscribeYes.click();

        /* Agree with Privacy Policy */
        agreePolicyCheckbox.click();


    }

    /* Click method for "Continue" button */
    public RegistrationSuccessPage clickOnContinueButton() {

        try {
            waitClickable(driver, registerBtnContinue, 5);
            registerBtnContinue.click();

            /* Check if the current page is the registration page */
            if (driver.getCurrentUrl().equals(REGISTER_PAGE_URL)) {

                // Throw a runtime exception, log the error and stop the test

                System.out.println("Registration failed. Error: " + returnErrorText());
                MyFileWriter.writeToLog("Registration failed. Error: " + returnErrorText());

                Assert.assertEquals(driver.getCurrentUrl(), REGISTER_SUCCESS_PAGE_URL, REGISTRATION_FAILED_MESSAGE + " Error: " + returnErrorText());

                throw new RuntimeException("Registration failed. Error: " + returnErrorText());
            } else {
                return new RegistrationSuccessPage(driver);
            }
        } catch (NullPointerException e) {
            // Log and console print the exception
            System.err.println("NullPointerException: " + e.getMessage());
            MyFileWriter.writeToLog("NullPointerException: " + e.getMessage());

            // Throw a runtime exception or log the error and stop the test
            throw new RuntimeException("Registration failed due to a NullPointerException.", e);
        }
//        waitClickable(driver, registerBtnContinue, 5);
//        registerBtnContinue.click();
//
//            /* Check if current page is domain( registration page ) and print messages in console and log file */
//            if (driver.getCurrentUrl().equals(REGISTER_PAGE_URL)) {
//
//                System.out.println("Error text: " + returnErrorText());
//                MyFileWriter.writeToLog("Error text: " + returnErrorText());
//
//
//                return (RegistrationSuccessPage) RETURN_NULL_OBJECT;
//            } else {
//                return new RegistrationSuccessPage(driver);
//            }
//
//        /* Return null driver if haven`t found the product */
//
//
//
////        if (errorText.isDisplayed()) {
////            System.out.println("Error text: " + returnErrorText());
////            MyFileWriter.writeToLog("Error text: " + returnErrorText());
////        }
//


    }

//    /* Login method who use username and password strings */
//    public RegistrationPage login(String username, String password) {
//
//        /* Clear, click and input data into the field */
//        userNameField.clear();
//        userNameField.click();
//        userNameField.sendKeys(username);
//
//        /* Validate the inserted data is the same with provided */
//        Assert.assertEquals(username, this.userNameField.getAttribute("value"), LOGIN_WRONG_USERNAME_MESSAGE);
//
//        /* Clear, click and input data into the field */
//        userPasswordField.clear();
//        userPasswordField.click();
//        userPasswordField.sendKeys(password);
//
//        /* Validate the inserted data is the same with provided */
//        Assert.assertEquals(password, this.userPasswordField.getAttribute("value"), LOGIN_WRONG_PASSWORD_MESSAGE);
//
//        loginButton.click();
//
//        /* Check if current page is domain( login page ) and print messages in console and log file */
//        if (driver.getCurrentUrl().equals(LOGIN_PAGE_URL)) {
//            System.out.println(LOGIN_FAILED_MESSAGE);
//            System.out.println("Error text: " + returnErrorText());
//            MyFileWriter.writeToLog(LOGIN_FAILED_MESSAGE + " - " + returnErrorText());
//
//            Assert.assertEquals(driver.getCurrentUrl(), GenericMessages.HOME_PAGE_URL, LOGIN_FAILED_MESSAGE);
//        } else {
//            System.out.println(LOGIN_SUCCESSFUL_MESSAGE);
//            MyFileWriter.writeToLog(LOGIN_SUCCESSFUL_MESSAGE);
//
//            Assert.assertEquals(driver.getCurrentUrl(), GenericMessages.HOME_PAGE_URL, LOGIN_FAILED_MESSAGE);
//        }
//        /* Pass the driver to HomePage (POM) */
//        return new HomePage(driver);
//    }
}
