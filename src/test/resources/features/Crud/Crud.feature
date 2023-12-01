Feature: Test CRUD methods with JDBC on customer data in database
  Test database response with SQL manipulations.

  Scenario: Add new Customer to the oc_customer table
    Given I check the state of database table oc_customer
    When I send POST HTTP request
    Then I receive a Response

  Scenario: Get Customer record from oc_customer table
    Given I check the state of database table oc_customer
    When I send GET HTTP request
    Then I receive a Response

  Scenario: Update Customer phone number record on oc_customer table
    Given I Read database customer table
    When I send UPDATE HTTP request
    Then I receive a Response

  Scenario: Delete Customer from oc_customer table
    Given I Read database customer table
    When I send DELETE HTTP request
    Then I receive a Response