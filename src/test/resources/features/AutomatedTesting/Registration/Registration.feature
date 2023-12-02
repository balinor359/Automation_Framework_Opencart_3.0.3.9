@Registration
Feature: Registration via registration page
  User wants to make a registration via registration page

  Scenario: User should be able to make registration with valid credentials
    Given the user is on home page
    When the user enters registration page
    And insert valid credentials as follows:
      | firstName | lastName | email                | telephone    | password |
      | Vasil     | Vasilev  | vasilev@test.test    | 0884395614   | vasilev  |
    And click on Continue button
    Then the user should been registered and redirect to its profile
