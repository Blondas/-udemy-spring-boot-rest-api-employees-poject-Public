package com.chacaroja.udemy.springbootrestapi.employees.service;

import com.chacaroja.udemy.springbootrestapi.employees.dao.EmployeeDAO;
import com.chacaroja.udemy.springbootrestapi.employees.entity.Employee;
import com.chacaroja.udemy.springbootrestapi.employees.request.EmployeeRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(long id) {
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest employeeRequest) {
        return employeeDAO.save(convertToEmployee(0, employeeRequest));
    }

    @Transactional
    @Override
    public Employee update(long id, EmployeeRequest employeeRequest) {
        Employee existingEmployee = employeeDAO.findById(id);
        return employeeDAO.save(convertToEmployee(id, employeeRequest));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public Employee convertToEmployee(long id, EmployeeRequest employeeRequest) {
        return new Employee(id, employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getEmail());
    }
}
