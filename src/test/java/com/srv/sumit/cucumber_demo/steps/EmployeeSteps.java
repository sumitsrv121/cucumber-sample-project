package com.srv.sumit.cucumber_demo.steps;

import com.srv.sumit.cucumber_demo.entity.Employee;
import com.srv.sumit.cucumber_demo.repository.EmployeeRepository;
import com.srv.sumit.cucumber_demo.service.DatabaseResetService;
import com.srv.sumit.cucumber_demo.service.EmployeeService;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeSteps implements En {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DatabaseResetService databaseResetService;
    @Autowired
    private EmployeeRepository employeeRepository;


    private Employee employee;

    public EmployeeSteps() {
        // Hook: Before each scenario
        // Insert initial data into the database
        Before(this::initData);

        // Hook: After each scenario
        // Clear the database and reset IDs
        After(this::clearData);

        // Given step: Create or retrieve an employee by ID
        Given("^a employee with ID \"([^\"]*)\"$", (final String employeeId) -> {
            int id = Integer.parseInt(employeeId);
            employee = employeeService.getEmployeeById(id);  // Fetch employee using service layer
        });

        // When step: Retrieve employee
        When("^I retrieve the employee$", () -> {
            // In this case, retrieving was already done in the "Given" step
        });

        // Then step: Check if employee details are correct
        Then("^the employee details should be returned$", () -> {
            assertEquals("Neha", employee.getFirstName());  // Verify employee details, assuming "Neha" is expected
        });
    }

    void clearData() {
        employeeRepository.deleteAll();
        databaseResetService.resetEmployeeTable();

    }


    void initData() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Neha", "Sharma", new BigDecimal("5000")));
        employees.add(new Employee("Vineet", "Verma", new BigDecimal("1000")));
        employees.add(new Employee("Aaditya", "Singh", new BigDecimal("4000")));
        employeeRepository.saveAll(employees);
    }
}
