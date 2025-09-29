package com.chacaroja.udemy.springbootrestapi.employees.dao;

import com.chacaroja.udemy.springbootrestapi.employees.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(long id);

    Employee save(Employee employee);

    void deleteById(long id);
}
