Feature: Logged user add product to his wishlist.
  User wants to add product to his wishlist.

  Scenario: User login into his profile, add product to his wishlist and see the same product in his wishlist page.
    Given the user is on home page
    When the user login into his profile with email "petrov@test.test", and password "petrov"
    And user add product from homepage to his wishlist
    And click on "Wish List" button in header
    Then the user should be redirected to his wishlist page and see added product there.