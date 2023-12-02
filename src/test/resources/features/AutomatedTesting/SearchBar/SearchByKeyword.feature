@SearchByKeyword
Feature: User search products by keyword
  User wants to use search bar in header to see all products with keyword "iPod"

  Scenario: User use search bar in header to find results by specific keyword
    Given the user is on home page
    When the user enter keyword "iPod" in header search bar
    And click on search button
    Then the user should be redirected to search result page and see all products containing provided keyword.