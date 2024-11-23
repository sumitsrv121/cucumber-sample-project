Feature: Employees
  As a user
  I want to onboard Employee
  So that I stay updated with latest employee details

  Scenario: Onboard a New Employee
    Given a employee with ID "1"
    When I retrieve the employee
    Then the employee details should be returned
