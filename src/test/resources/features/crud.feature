Feature: Test CRUD methods with JDBC on customer data in database
  Test database response with SQL manipulations.

  Scenario: Add new Customer to the oc_customer table
    Given I Set POST customer endpoint
    When I check the state of database table oc_customer
    And I send POST HTTP request
    Then I recieve valid Response

  Scenario: Get Customer record from oc_customer table
    Given I Set GET customers endpoint
    When I check the state of database table oc_customer
    And I send GET HTTP request
    Then I recieve valid Response

  Scenario: Update Customer phone number record on oc_customer table
    Given I Set PUT customer phone number endpoint
    When I send UPDATE HTTP request
    Then I recieve valid Response

  Scenario: Delete Customer from oc_customer table
    Given I Set Delete customer endpoint
    When I send DELETE HTTP request
    Then I recieve valid Response