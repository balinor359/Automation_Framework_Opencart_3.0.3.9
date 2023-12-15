package test.opencart.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class ContactPage extends TestUtilities {

    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */
    private static final String CONTACT_PAGE_HEADING = "Contact Us";
    private static final String CONTACT_FORM_MISSING_MESSAGE = "Contact form is missing!";
    private static final String NAME_INPUT_MISSING_MESSAGE = "Name input is missing!";
    private static final String EMAIL_INPUT_MISSING_MESSAGE = "Email input is missing!";
    private static final String ENQUIRY_TEXTAREA_MISSING_MESSAGE = "Enquiry textarea is missing!";
    private static final String SUCCESS_MESSAGE_MISSING_MESSAGE = "Success message is missing!";
    private static final String SUCCESS_MESSAGE_DIFFERENT_MESSAGE = "Success message is different!";
    private static final String CONTACT_PAGE_URL = "https://opencart-test.test/index.php?route=information/contact";
    private static final String CONTACT_SUCCESS_PAGE_URL = "https://opencart-test.test/index.php?route=information/contact/success";
    private static final String ENQUIRY_FAILED_MESSAGE = "Enquiry sending failed!";

    /* Declaring page elements */
    @FindBy(xpath = "//div[@id='information-contact']//h1")
    private WebElement heading;
    @FindBy(xpath = "//form[@class='form-horizontal']")
    private WebElement contactForm;
    @FindBy(id = "input-name")
    private WebElement inputName;
    @FindBy(id = "input-email")
    private WebElement inputEmail;
    @FindBy(id = "input-enquiry")
    private WebElement enquiryTextarea;
    @FindBy(xpath = ".//input[@type='submit']")
    private WebElement contactFormSubmitBtn;
    @FindBy(xpath = "//div[@id='common-success']//p")
    private WebElement successMsg;


    /* This is constructor for contact page using PageFactory for web-elements */
    public ContactPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method validate search result page container is visible */
    public void contactPageValidator() {
        Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);

        /* Validate Contact page heading contains  */
        Assert.assertEquals(heading.getText(), CONTACT_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);

        Assert.assertTrue(contactForm.isDisplayed(), CONTACT_FORM_MISSING_MESSAGE);
        Assert.assertTrue(inputName.isDisplayed(), NAME_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(inputEmail.isDisplayed(), EMAIL_INPUT_MISSING_MESSAGE);
        Assert.assertTrue(enquiryTextarea.isDisplayed(), ENQUIRY_TEXTAREA_MISSING_MESSAGE);
        Assert.assertTrue(contactFormSubmitBtn.isDisplayed(), GenericMessages.SUBMIT_BUTTON_MISSING_MESSAGE);
    }

    /* This method insert Name string into contact form input field for Name */
    public void insertName_ContactForm(String name) {
        /* Clear, click and input data into name field */
        inputName.clear();
        inputName.click();
        inputName.sendKeys(name);
    }

    /* This method insert Name string into contact form input field for Name */
    public void insertEmail_ContactForm(String email) {
        /* Clear, click and input data into email field */
        inputEmail.clear();
        inputEmail.click();
        inputEmail.sendKeys(email);
    }

    /* This method insert text string into contact form textarea field for Enquiry */
    public void insertEnquiry_ContactForm(String enquiry) {
        /* Clear, click and input data into textarea */
        enquiryTextarea.clear();
        enquiryTextarea.click();
        enquiryTextarea.sendKeys(enquiry);
    }

    /* Click method for contact form submit button */
    public void contactFormSubmit() {

        try {
            waitClickable(driver, contactFormSubmitBtn, 5);
            contactFormSubmitBtn.click();

            /* Check if the current page is the contact page */
            if (driver.getCurrentUrl().equals(CONTACT_PAGE_URL)) {
                Assert.assertEquals(driver.getCurrentUrl(), CONTACT_SUCCESS_PAGE_URL, ENQUIRY_FAILED_MESSAGE);
            }

        } catch (NullPointerException e) {
            /* Throw a runtime exception, log and console print the exception */
            System.err.println("Enquiry sending failed due to a NullPointerException: " + e.getMessage());
            MyFileWriter.writeToLog("Enquiry sending failed due to a NullPointerException: " + e.getMessage());

            throw new RuntimeException("Enquiry sending failed due to a NullPointerException.", e);
        }

    }

    /* This method validate success message, is it visible and the same as provided */
    public void validateSuccessMessage(String message) {
        Assert.assertTrue(successMsg.isDisplayed(), SUCCESS_MESSAGE_MISSING_MESSAGE);
        Assert.assertEquals(successMsg.getText(), message, SUCCESS_MESSAGE_DIFFERENT_MESSAGE);
    }
}
