package com.srv.sumit.cucumber_demo.steps;

import io.cucumber.java8.En;

public class EmployeeSteps implements En {
    public EmployeeSteps() {
        Given("^a employee with ID \"([^\"]*)\"$", (final String employeeId) -> {
        });
        When("^I retrieve the employee$", () -> {
        });
        Then("^the employee details should be returned$", () -> {
        });
    }
}
