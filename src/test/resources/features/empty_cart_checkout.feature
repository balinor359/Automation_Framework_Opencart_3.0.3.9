Feature: Empty cart checkout
  User wants to complete order with empty shopping cart.

  Scenario: User try to navigate to checkout via mini cart in header
    Given the user is on home page
    When the user clicks on mini cart in header
    Then the user should see "Your shopping cart is empty!" message.

  Scenario: User try to navigate to checkout via "Checkout" link in header
    Given the user is on home page
    When the user clicks on "Checkout" link in header
    Then the user should be redirected to shopping cart page and see "Your shopping cart is empty!" message.