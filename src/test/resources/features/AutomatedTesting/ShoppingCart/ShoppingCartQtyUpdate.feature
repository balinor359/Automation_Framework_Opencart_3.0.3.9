@ShoppingCartQtyUpdate
Feature: Update product quantity in shopping cart page
  User wants to change the product quantity from his shopping cart page.

  Scenario: User update quantity of product in shopping cart page
    Given the user is on home page
    When the user add "iPod Nano" product in shopping cart from home page
    And navigate to shopping cart page
    And insert new value "2" in product quantity field and click update button
    Then the product data in the shopping cart page must change