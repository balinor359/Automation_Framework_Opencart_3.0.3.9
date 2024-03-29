@ShoppingCartDeleteProduct
Feature: Delete product from shopping cart page
  User wants to delete the product from his shopping cart page.

  Scenario: User delete product from his shopping cart page
    Given the user is on home page
    When the user add "iMac" product in shopping cart from home page
    And navigate to shopping cart page
    And user click on "iMac" product delete button
    Then the user should see "Your shopping cart is empty!" message on the Shopping Cart page.