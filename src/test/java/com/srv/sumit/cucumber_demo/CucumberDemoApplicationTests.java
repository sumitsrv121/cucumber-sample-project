package com.srv.sumit.cucumber_demo;

import com.srv.sumit.cucumber_demo.dto.EmployeeDTO;
import com.srv.sumit.cucumber_demo.entity.Employee;
import com.srv.sumit.cucumber_demo.repository.EmployeeRepository;
import com.srv.sumit.cucumber_demo.service.DatabaseResetService;
import com.srv.sumit.cucumber_demo.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CucumberDemoApplicationTests {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DatabaseResetService databaseResetService;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
        databaseResetService.resetEmployeeTable();

    }

    @BeforeEach
    void init() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Neha", "Choubey", new BigDecimal("5000")));
        employees.add(new Employee("Vineet", "Choubey", new BigDecimal("1000")));
        employees.add(new Employee("Aaditya", "Choubey", new BigDecimal("4000")));
        employeeRepository.saveAll(employees);
    }

    @Test
    void givenValidEmployeeIdWhenFindAEmployeeByIdIsInvokedThenReturnEmployee() {
        Employee employee = employeeService.getEmployeeById(1);
        Assertions.assertEquals("Neha", employee.getFirstName());
    }

    @Test
    void givenURLToFetchListOfEmployeeWhenFindByEmployeesIsInvokedThenReturnAllEmployees() {
        Assertions.assertEquals(3, employeeService.getAllEmployees().size());
    }

    @Test
    void givenValidEmployeeDetailsWhenCreateEmployeeIsInvokedThenReturnCreatedEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO("Sumit", new BigDecimal("200000"));

        Employee employee = employeeService.createEmployee(employeeDTO);

        Assertions.assertEquals("Sumit", employee.getFirstName());
        Assertions.assertNull(employee.getLastName());
    }

}
