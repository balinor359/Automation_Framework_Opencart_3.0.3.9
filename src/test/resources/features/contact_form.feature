Feature: User send Enquiry to the web shop.
  User wants to send message to web shop owner via contact form.

  Scenario: User send Enquiry by contact form in contact page.
    Given the user is on home page
    When the user navigate to contact page
    And fill "Your Name" field as "Ivan Ivanov"
    And fill "E-Mail Address" as "ivanov@test.test"
    And fill "Enquiry" as "This is the test message!"
    And click on "Submit" button
    Then the user should see "Your enquiry has been successfully sent to the store owner!" message.