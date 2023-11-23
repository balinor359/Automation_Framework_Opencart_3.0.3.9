Feature: Web Login
  User wants to login into system

  Scenario: User should be able to login with valid credentials
    Given the user is on login page
    When the user enters valid credentials
    And hits submit
    Then the user should be logged in successfully


