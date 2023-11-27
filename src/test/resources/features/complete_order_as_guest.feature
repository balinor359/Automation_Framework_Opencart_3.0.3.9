Feature: User complete order as Guest.
  User wants to add product from home page and complete order as a Guest.

  Scenario: User add product to shopping cart from homepage, and complete order as a Guest.
    Given the user is on home page
    When the user add product to shopping cart from homepage
    And click on "Checkout" button in header
    And select "Guest Checkout" in checkout page
    And go through all checkout steps
    And fill all required fields with valid credentials
    And click on "Confirm Order" button
    Then the user should be redirected to success page and see "Your order has been placed!" message.