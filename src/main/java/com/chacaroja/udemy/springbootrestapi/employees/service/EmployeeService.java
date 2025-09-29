package com.chacaroja.udemy.springbootrestapi.employees.service;

import com.chacaroja.udemy.springbootrestapi.employees.entity.Employee;
import com.chacaroja.udemy.springbootrestapi.employees.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(long id);

    Employee save(EmployeeRequest employeeRequest);

    Employee update(long id, EmployeeRequest employeeRequest);

    void deleteById(long id);

    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);
}
