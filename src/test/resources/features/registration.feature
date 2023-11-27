Feature: Registration via registration page
  User wants to make a registration via registration page

  Scenario: User should be able to make registration with valid credentials
    Given the user is on home page
    When the user enters registration page
    And insert valid credentials
    And insert email "vasilev@test.test"
    And insert password "vasilev"
    And click on "Continue" button
    Then the user should been registrated and redirect to its profile
