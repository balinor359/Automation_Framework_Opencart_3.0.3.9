package test.opencart.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class CheckoutPage extends TestUtilities {
    /* Declaring web-driver in protected variable */
    protected WebDriver driver;

    /* Declaring string variables for the current page */

    /* Declaring page elements */
    @FindBy(xpath = ".//div[@id='checkout-checkout']//h1")
    private WebElement heading;
    @FindBy(xpath = "//div[@id='checkout-checkout']")
    private WebElement checkoutPageContainer;
    @FindBy(xpath = ".//input[@qa='registerInput']")
    private WebElement registerRadioBtn;
    @FindBy(xpath = ".//input[@qa='guestInput']")
    private WebElement guestRadioBtn;
    @FindBy(xpath = ".//input[@id='button-account']")
    private WebElement btnAccountStepOne;
    @FindBy(xpath = ".//input[@id='input-payment-firstname']")
    private WebElement inputFirstName;
    @FindBy(xpath = ".//input[@id='input-payment-lastname']")
    private WebElement inputLastName;
    @FindBy(xpath = ".//input[@id='input-payment-email']")
    private WebElement inputEmail;
    @FindBy(xpath = ".//input[@id='input-payment-telephone']")
    private WebElement inputTelephone;
    @FindBy(xpath = ".//input[@id='input-payment-address-1']")
    private WebElement inputAddress1;
    @FindBy(xpath = ".//input[@id='input-payment-city']")
    private WebElement inputCity;
    @FindBy(xpath = ".//input[@id='input-payment-postcode']")
    private WebElement inputPostCode;
    @FindBy(xpath = ".//select[@id='input-payment-country']")
    private WebElement selectCountry;
    @FindBy(xpath = ".//select[@id='input-payment-zone']")
    private WebElement selectZone;
    @FindBy(xpath = ".//input[@id='button-guest']")
    private WebElement btnGuestStep2;
    @FindBy(xpath = ".//input[@id='button-payment-address']")
    private WebElement btnContinueStep2;
    @FindBy(xpath = ".//input[@id='button-shipping-address']")
    private WebElement btnContinueStep3;
    @FindBy(xpath = ".//textarea[@qa='add-order-comment']")
    private WebElement orderCommentTextarea;
    @FindBy(xpath = ".//input[@id='button-shipping-method']")
    private WebElement btnContinueStep4;
    @FindBy(xpath = ".//input[@qa='agree-terms-checkout']")
    private WebElement agreeTermsCheckbox;
    @FindBy(xpath = ".//input[@id='button-payment-method']")
    private WebElement btnContinueStep5;
    @FindBy(xpath = ".//input[@id='button-confirm']")
    private WebElement btnConfirmOrder;


    /* This is constructor for cart page using PageFactory for web-elements */
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /* This method validate checkout page heading exist and assure that redirect is successfully */
    public void checkoutPageValidator() {

        /* Check if the current page is the checkout page */
        if (driver.getCurrentUrl().equals(GenericMessages.CHECKOUT_PAGE_URL)) {

            /* Validate cart page container and heading exist  */
            Assert.assertTrue(checkoutPageContainer.isDisplayed(), GenericMessages.CHECKOUT_PAGE_CONTAINER_MISSING);
            Assert.assertTrue(heading.isDisplayed(), GenericMessages.PAGE_HEADING_MISSING_MESSAGE);
            Assert.assertEquals(heading.getText(), GenericMessages.CHECKOUT_PAGE_HEADING, GenericMessages.DIFFERENT_PAGE_HEADING);

        } else {
            System.out.println(GenericMessages.REDIRECT_FAILED);
            MyFileWriter.writeToLog(GenericMessages.REDIRECT_FAILED);
        }
    }

    /* Method who select "Guest Checkout" radio button */
    public void clickOnGuestCheckoutRadioBtn() {
        TestUtilities.scrollToMethod(driver, guestRadioBtn);
        TestUtilities.waitClickable(driver, guestRadioBtn, 5);
        Assert.assertTrue(guestRadioBtn.isDisplayed(), GenericMessages.RADIO_BUTTON_MISSING_MESSAGE);

        // Get the parent element, which is the label
        WebElement labelElement = guestRadioBtn.findElement(By.xpath("./.."));
        Assert.assertEquals(labelElement.getText(), GenericMessages.RADIO_BUTTON_GUEST_TEXT, GenericMessages.RADIO_BUTTON_DIFFERENT_TEXT_MESSAGE);
        guestRadioBtn.click();

    }

    /* Method who click on Step:1 "Continue" button */
    public void clickOnStepOneContinueBtn() {
        TestUtilities.scrollToMethod(driver, btnAccountStepOne);
        TestUtilities.waitClickable(driver, btnAccountStepOne, 5);
        Assert.assertTrue(btnAccountStepOne.isDisplayed(), GenericMessages.CONTINUE_BUTTON_MISSING_MESSAGE);
        Assert.assertEquals(btnAccountStepOne.getAttribute("value"), GenericMessages.CONTINUE_BUTTON_TEXT, GenericMessages.CONTINUE_BUTTON_DIFFERENT_TEXT_MESSAGE);
        btnAccountStepOne.click();

    }

    /* This method fill billing details */
    public void fillCheckoutFormGuest(String firstName, String lastName, String email, String telephone, String address, String city, String postCode, String country, String region) {

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputFirstName, 5);
        inputFirstName.clear();
        inputFirstName.click();
        inputFirstName.sendKeys(firstName);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputLastName, 5);
        inputLastName.clear();
        inputLastName.click();
        inputLastName.sendKeys(lastName);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputEmail, 5);
        inputEmail.clear();
        inputEmail.click();
        inputEmail.sendKeys(email);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputTelephone, 5);
        inputTelephone.clear();
        inputTelephone.click();
        inputTelephone.sendKeys(telephone);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputAddress1, 5);
        inputAddress1.clear();
        inputAddress1.click();
        inputAddress1.sendKeys(address);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputCity, 5);
        inputCity.clear();
        inputCity.click();
        inputCity.sendKeys(city);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputPostCode, 5);
        inputPostCode.clear();
        inputPostCode.click();
        inputPostCode.sendKeys(postCode);

        /* Select country */
        Select countrySelect = new Select(selectCountry);
        countrySelect.selectByVisibleText(country);

        /* Select region */
        Select countryZone = new Select(selectZone);
        countryZone.selectByVisibleText(region);

        /* Click "Continue" button on step 2 */
        TestUtilities.waitClickable(driver, btnGuestStep2, 5);
        btnGuestStep2.click();

    }

    /* This method fill billing details */
    public void fillCheckoutFormUser(String firstName, String lastName, String address, String city, String postCode, String country, String region) {

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputFirstName, 5);
        inputFirstName.clear();
        inputFirstName.click();
        inputFirstName.sendKeys(firstName);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputLastName, 5);
        inputLastName.clear();
        inputLastName.click();
        inputLastName.sendKeys(lastName);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputAddress1, 5);
        inputAddress1.clear();
        inputAddress1.click();
        inputAddress1.sendKeys(address);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputCity, 5);
        inputCity.clear();
        inputCity.click();
        inputCity.sendKeys(city);

        /* Clear, click and input data into the field */
        TestUtilities.waitClickable(driver, inputPostCode, 5);
        inputPostCode.clear();
        inputPostCode.click();
        inputPostCode.sendKeys(postCode);

        /* Select country */
        Select countrySelect = new Select(selectCountry);
        countrySelect.selectByVisibleText(country);

        /* Select region */
        Select countryZone = new Select(selectZone);
        countryZone.selectByVisibleText(region);

        /* Click "Continue" button on step 2 */
        TestUtilities.waitClickable(driver, btnContinueStep2, 5);
        btnContinueStep2.click();

        /* Click "Continue" button on step 3 */
        TestUtilities.waitClickable(driver, btnContinueStep3, 5);
        btnContinueStep3.click();

    }

    /* This method comment to the order */
    public void addCommentToTheOrder(String comment) {

        /* Clear, click and input data into the textarea */
        TestUtilities.waitClickable(driver, orderCommentTextarea, 5);
        orderCommentTextarea.clear();
        orderCommentTextarea.click();
        orderCommentTextarea.sendKeys(comment);

        /* Click "Continue" button on step 4 */
        TestUtilities.waitClickable(driver, btnContinueStep4, 5);
        btnContinueStep4.click();
    }

    /* This method accept Terms & Conditions */
    public void acceptTerms() {

        /* Click checkbox to agree with Terms & Conditions */
        TestUtilities.waitClickable(driver, agreeTermsCheckbox, 5);
        agreeTermsCheckbox.click();

        /* Click "Continue" button on step 5 */
        TestUtilities.waitClickable(driver, btnContinueStep5, 5);
        btnContinueStep5.click();
    }

    /* This method complete the order */
    public OrderSuccessPage completeOrder() {

        /* Click "Confirm Order" on step 6 */
        TestUtilities.waitClickable(driver, btnConfirmOrder, 5);
        btnConfirmOrder.click();

        /* Return driver to SuccessPage (POM) */
        return new OrderSuccessPage(driver);
    }

}
