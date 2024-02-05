package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.HomePage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class FooterInternalLinks extends TestUtilities {
    @When("the user open <About Us> link in footer")
    public void the_user_open_about_us_link_in_footer() {
        System.out.println("Inside Step - the user open <About Us> link in footer");
        MyFileWriter.writeToLog("Inside Step - the user open <About Us> link in footer");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.footerInformationPagesLinksValidator();
        homePage.clickFooterAboutLink();

    }

    @Then("the user should see About Us page.")
    public void the_user_should_see_about_us_page() {
        System.out.println("Inside Step - the user should see About Us page.");
        MyFileWriter.writeToLog("Inside Step - the user should see About Us page.");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.validateAboutPage();
    }


    @When("the user open <Delivery Information> link in footer")
    public void the_user_open_delivery_information_link_in_footer() {
        System.out.println("Inside Step - the user open <Delivery Information> link in footer");
        MyFileWriter.writeToLog("Inside Step - the user open <Delivery Information> link in footer");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.footerInformationPagesLinksValidator();
        homePage.clickFooterDeliveryLink();

    }

    @Then("the user should see Delivery Information page.")
    public void the_user_should_see_delivery_information_page() {
        System.out.println("Inside Step - the user should see Delivery Information page.");
        MyFileWriter.writeToLog("Inside Step - the user should see Delivery Information page.");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.validateDeliveryPage();
    }


    @When("the user open <Privacy Policy> link in footer")
    public void the_user_open_privacy_policy_link_in_footer() {
        System.out.println("Inside Step - the user open <Privacy Policy> link in footer");
        MyFileWriter.writeToLog("Inside Step - the user open <Privacy Policy> link in footer");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.footerInformationPagesLinksValidator();
        homePage.clickFooterPrivacyLink();

    }

    @Then("the user should see Privacy Policy page.")
    public void the_user_should_see_privacy_policy_page() {
        System.out.println("Inside Step - the user should see Privacy Policy page.");
        MyFileWriter.writeToLog("Inside Step - the user should see Privacy Policy page.");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.validatePrivacyPage();
    }


    @When("the user open <Terms & Conditions> link in footer")
    public void the_user_open_terms_conditions_link_in_footer() {
        System.out.println("Inside Step - the user open <Terms & Conditions> link in footer");
        MyFileWriter.writeToLog("Inside Step - the user open <Terms & Conditions> link in footer");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.footerInformationPagesLinksValidator();
        homePage.clickFooterTermsLink();

    }

    @Then("the user should see Terms & Conditions page.")
    public void the_user_should_see_terms_conditions_page() {
        System.out.println("Inside Step - the user should see Terms & Conditions page.");
        MyFileWriter.writeToLog("Inside Step - the user should see Terms & Conditions page.");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.validateTermsPage();
    }
}
