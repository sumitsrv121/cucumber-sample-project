package com.srv.sumit.cucumber_demo.controller;

import com.srv.sumit.cucumber_demo.dto.EmployeeDTO;
import com.srv.sumit.cucumber_demo.entity.Employee;
import com.srv.sumit.cucumber_demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.createEmployee(employee);
    }
}
