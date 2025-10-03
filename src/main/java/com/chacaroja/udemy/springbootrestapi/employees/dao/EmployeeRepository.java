package com.chacaroja.udemy.springbootrestapi.employees.dao;

import com.chacaroja.udemy.springbootrestapi.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
