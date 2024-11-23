package com.srv.sumit.cucumber_demo.steps;

import com.srv.sumit.cucumber_demo.dto.EmployeeDTO;
import com.srv.sumit.cucumber_demo.entity.Employee;
import com.srv.sumit.cucumber_demo.repository.EmployeeRepository;
import com.srv.sumit.cucumber_demo.service.DatabaseResetService;
import com.srv.sumit.cucumber_demo.service.EmployeeService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeSteps implements En {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DatabaseResetService databaseResetService;
    @Autowired
    private EmployeeRepository employeeRepository;


    private Employee employee;
    private List<Employee> employees;
    private EmployeeDTO employeeDTO;

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
        Given("^no details$", () -> {
        });
        When("^I retrieve all employees$", () -> {
            employees = employeeService.getAllEmployees();
        });
        Then("^total number of employee count should be '(\\d+)'$", (Integer count) -> {
            assertEquals(3, employees.size());
        });
        Given("^the following employee details$", (DataTable dataTable) -> {
            // Convert DataTable to a Map
            List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
            Map<String, String> employeeData = rows.get(0); // Use the first row (or adapt for multiple employees)

            // Map values to DTO
            String name = employeeData.get("name");
            BigDecimal salary = new BigDecimal(employeeData.get("salary"));
            employeeDTO = new EmployeeDTO(name, salary);
        });
        When("^I create the employee$", () -> {
            employeeService.createEmployee(employeeDTO); // Call the service to create the employee
        });
        Then("^the employee should be added to the database$", () -> {
            String[] name = employeeDTO.name().split(" ");
            Employee createdEmployee = employeeRepository.findByFirstNameAndLastName(name[0], name[1]).get();
            assertNotNull(createdEmployee, "Employee was not found in the database");
        });
        And("^the employee details should match$", () -> {
            String[] name = employeeDTO.name().split(" ");
            Employee createdEmployee = employeeRepository.findByFirstNameAndLastName(name[0], name[1]).get();

            assertEquals(employeeDTO.name(), createdEmployee.getFirstName() + " " + createdEmployee.getLastName());
            assertEquals(employeeDTO.salary(), createdEmployee.getSalary());
        });
    }

    void clearData() {
        employeeRepository.deleteAll();
        databaseResetService.resetEmployeeTable();

    }


    void initData() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Neha", "Sharma", new BigDecimal("5000")));
        employees.add(new Employee("Lisa", "Verma", new BigDecimal("1000")));
        employees.add(new Employee("Aaditya", "Singh", new BigDecimal("4000")));
        employeeRepository.saveAll(employees);
    }
}
