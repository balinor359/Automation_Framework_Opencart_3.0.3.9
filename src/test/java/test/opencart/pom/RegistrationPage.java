package test.opencart.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;


public class RegistrationPage extends TestUtilities {

    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
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

    /* This is constructor for registration page using PageFactory for web-elements */
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method return errorMessage */
    public String returnErrorText() {
        return errorMessage = errorText.getText();
    }

    /* This method validate registration page heading and form elements are visible */
    public void registrationPageValidator() {
        Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
        Assert.assertEquals(heading.getText(), GenericMessages.REGISTER_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);
        Assert.assertTrue(registrationForm.isDisplayed(), GenericMessages.REGISTRATION_FORM_MISSING_MESSAGE);
        Assert.assertTrue(inputFirstname.isDisplayed(), GenericMessages.FIRST_NAME_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(inputLastname.isDisplayed(), GenericMessages.LAST_NAME_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(inputEmail.isDisplayed(), GenericMessages.EMAIL_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(inputTelephone.isDisplayed(), GenericMessages.TELEPHONE_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(inputPassword.isDisplayed(), GenericMessages.PASSWORD_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(inputConfirmPassword.isDisplayed(), GenericMessages.CONFIRM_PASSWORD_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(agreePolicyCheckbox.isDisplayed(), GenericMessages.POLICY_CHECKBOX_MISSING_MESSAGE);
        Assert.assertTrue(registerBtnContinue.isDisplayed(), GenericMessages.CONTINUE_BUTTON_MISSING_MESSAGE);
    }

    /* This method fill registration form */
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
            if (driver.getCurrentUrl().equals(GenericMessages.REGISTER_PAGE_URL)) {

                /* Throw a runtime exception, log the error and stop the test */
                System.out.println("Registration failed. Error: " + returnErrorText());
                MyFileWriter.writeToLog("Registration failed. Error: " + returnErrorText());

                Assert.assertEquals(driver.getCurrentUrl(), GenericMessages.REGISTER_SUCCESS_PAGE_URL, GenericMessages.REGISTRATION_FAILED_MESSAGE + " Error: " + returnErrorText());

                throw new RuntimeException("Registration failed. Error: " + returnErrorText());
            } else {
                return new RegistrationSuccessPage(driver);
            }
        } catch (NullPointerException e) {
            /* Throw a runtime exception, log and console print the exception */
            System.err.println("NullPointerException: " + e.getMessage());
            MyFileWriter.writeToLog("NullPointerException: " + e.getMessage());

            throw new RuntimeException("Registration failed due to a NullPointerException.", e);
        }
    }
}
