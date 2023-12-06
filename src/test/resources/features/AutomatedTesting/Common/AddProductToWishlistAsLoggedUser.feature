@AddProductToWishlistAsLoggedUser
Feature: Logged user add product to his wishlist.
  User wants to add product to his wishlist.

  Scenario: User login into his profile, add product to his wishlist and see the same product in his wishlist page.
    Given the user have a profile in website and is on home page
#    When the user login into his profile with email "petrov@test.test", and password "petrov"
    When the user login into his profile with email "ivanov@test.test", and password "ivanov"
    And user add product from homepage to his wishlist
    And click on <Wish List> button in header
    Then the user should be redirected to his wishlist page and see added product there.