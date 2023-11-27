Feature: User add customer review to product.
  User wants to add customer review in product page.

  Scenario: User add customer review on product.
    Given the user is on home page
    When the user navigate to product page
    And click on "Reviews" tab
    And fill the review form with valid data
    And click on "Continue" button
    Then the user should see "Thank you for your review. It has been submitted to the webmaster for approval." message.