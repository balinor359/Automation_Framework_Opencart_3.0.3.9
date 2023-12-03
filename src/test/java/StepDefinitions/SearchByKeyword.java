package StepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.opencart.pom.HomePage;
import test.opencart.pom.SearchResultPage;
import test.opencart.utilities.MyFileWriter;
import test.opencart.utilities.TestUtilities;

public class SearchByKeyword extends TestUtilities {

    @When("the user enter keyword {string} in header search bar")
    public void the_user_enter_keyword_in_header_search_bar(String string) {
        System.out.println("Inside Step - the user enter keyword " + string + " in header search bar");
        MyFileWriter.writeToLog("Inside Step - the user enter keyword " + string + " in header search bar");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.searchBarValidator();
        homePage.searchBarInputKeyword(string);
    }

    @When("click on search button")
    public void click_on_search_button() {
        System.out.println("Inside Step - click on search button");
        MyFileWriter.writeToLog("Inside Step - click on search button");

        HomePage homePage = new HomePage(TestUtilities.driver);
        homePage.searchBarSubmit();
    }

    @Then("the user should be redirected to search result page and see all products containing provided keyword.")
    public void the_user_should_be_redirected_to_search_result_page_and_see_all_products_containing_provided_keyword() {
        System.out.println("Inside Step - the user should be redirected to search result page and see all products containing provided keyword.");
        MyFileWriter.writeToLog("Inside Step - the user should be redirected to search result page and see all products containing provided keyword.");

        SearchResultPage searchResultPage = new SearchResultPage(TestUtilities.driver);
        searchResultPage.searchResultPageValidator();
        searchResultPage.getAllProductResult();
        searchResultPage.validateAllProductResultByKeyword();
    }
}
