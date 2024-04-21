@CompleteOrderAsLoggedUser
Feature: User complete order as logged customer.
  User wants to login into his profile and add buy product from product page.

  Scenario: User login into his profile add product from product page and complete the checkout process.
    Given the user have a profile in website and is on home page
#    When the user login into his profile with email "petrov@test.test", and password "petrov"
    #Create and delete user - add t wishlist feature !
    When the user login into his profile with email "ivanov@test.test", and password "ivanov"
    And the user add "iPod Touch" product to shopping cart from product page
    And click on <Checkout> button in header
    And go through all checkout steps
    And fill all required fields with valid credentials
    And click on <Confirm Order> button
    Then the user should be redirected to success page and see "Your order has been placed!" message.