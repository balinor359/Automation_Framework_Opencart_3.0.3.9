@FooterInternalLinks
Feature: Verify internal footer links
  User wants to see correct pages connected to links in footer.

  Scenario: User open About Us link in footer
    Given the user is on home page
    When the user open <About Us> link in footer
    Then the user should see About Us page.

  Scenario: User open Delivery Information link in footer
    Given the user is on home page
    When the user open <Delivery Information> link in footer
    Then the user should see Delivery Information page.

  Scenario: User open Privacy Policy link in footer
    Given the user is on home page
    When the user open <Privacy Policy> link in footer
    Then the user should see Privacy Policy page.

  Scenario: User open Terms & Conditions link in footer
    Given the user is on home page
    When the user open <Terms & Conditions> link in footer
    Then the user should see Terms & Conditions page.