package com.chacaroja.udemy.springbootrestapi.employees.controller;

import com.chacaroja.udemy.springbootrestapi.employees.entity.Employee;
import com.chacaroja.udemy.springbootrestapi.employees.request.EmployeeRequest;
import com.chacaroja.udemy.springbootrestapi.employees.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
@Tag(name = "Employee Rest API Endpoints", description = "Operations related to Employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @Operation(summary = "Get employee by ID", description = "Retrieve a single employee by their ID")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Employee findById(@PathVariable @Min(value = 1) Long id) {
        return employeeService.findById(id);
    }

    @Operation(summary = "Create a new employee", description = "Add a new employee to the system")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee create(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.save(employeeRequest);
    }

    @Operation(summary = "Update an existing employee", description = "Update the details of an existing employee by their ID")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Employee update(@PathVariable @Min(value = 1) Long id,
                           @Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.update(id, employeeRequest);
    }

    @Operation(summary = "Delete an employee", description = "Remove an employee from the system by their ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable @Min(value = 1) Long id) {
        employeeService.deleteById(id);
    }
}