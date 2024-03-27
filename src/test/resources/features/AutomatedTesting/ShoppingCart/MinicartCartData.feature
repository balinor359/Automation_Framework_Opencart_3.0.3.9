@MinicartCartData
Feature: Product data in mini cart and shopping cart page is the same
  User wants to see the products data in mini cart is the same as product data in shopping cart page

  Scenario: User compare mini cart product data with the product data in shopping cart page
    Given the user is on home page
    When the user add product in shopping cart from home page
    And user clicks on mini cart in header he sees added product data
    And navigate to shopping cart page
    Then the product data in the shopping cart page must be the same as mini cart product data