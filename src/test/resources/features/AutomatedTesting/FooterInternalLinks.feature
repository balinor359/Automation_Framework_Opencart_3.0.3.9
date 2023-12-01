Feature: Verify internal footer links
  User wants to see correct pages connected to links in footer.

  Scenario: User navigates through internal links in footer
    Given the user is on home page
    When the user open in new browser tab "About Us" link
    And open in new browser tab "Delivery Information" link
    And open in new browser tab "Privacy Policy" link
    And open in new browser tab "Terms & Conditions" link
    Then the user should see correct pages related to the clicked links.