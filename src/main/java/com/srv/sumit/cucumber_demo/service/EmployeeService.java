package com.srv.sumit.cucumber_demo.service;


import com.srv.sumit.cucumber_demo.dto.EmployeeDTO;
import com.srv.sumit.cucumber_demo.entity.Employee;
import com.srv.sumit.cucumber_demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public Employee createEmployee(EmployeeDTO employeeDTO) {
        // create Employee object
        String name = employeeDTO.name();
        String[] fnLn = name.split(" ");
        Employee employee;
        if (fnLn.length == 2) {
            employee = Employee.builder()
                    .firstName(fnLn[0])
                    .lastName(fnLn[1])
                    .salary(employeeDTO.salary())
                    .build();
        } else {
            employee = Employee.builder()
                    .firstName(fnLn[0])
                    .salary(employeeDTO.salary())
                    .build();
        }

        return employeeRepository.save(employee);
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            throw new RuntimeException("Employee not Found " + id);
        }
    }
}
