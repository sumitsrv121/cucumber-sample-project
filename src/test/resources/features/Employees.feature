Feature: Employees
  As a user
  I want to onboard Employee
  So that I stay updated with latest employee details

  Scenario: Retrieve an existing employee
    Given a employee with ID "1"
    When I retrieve the employee
    Then the employee details should be returned

  Scenario: Retrieve all employee
    Given no details
    When I retrieve all employees
    Then total number of employee count should be '3'

  Scenario: Create a new employee
    Given the following employee details
      | name     | salary  |
      | John Doe | 5000.00 |
    When I create the employee
    Then the employee should be added to the database
    And the employee details should match