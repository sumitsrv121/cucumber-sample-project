package com.srv.sumit.cucumber_demo.repository;

import com.srv.sumit.cucumber_demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
}
