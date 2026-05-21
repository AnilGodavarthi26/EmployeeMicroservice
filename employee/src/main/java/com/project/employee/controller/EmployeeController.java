package com.project.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee.model.Employee;
import com.project.employee.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Health API
    @GetMapping("/health")
    public String health() {
        return "Employee Application Running ";
    }

    // Get All Employees
    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    // Get Employee By ID
    @GetMapping("/{id}")
    public String getEmployeeById(@PathVariable int id) {
        return "Employee details for ID: " + id;
    }

    // Add Employee
    @PostMapping
    public String addEmployee() {
        return "Employee Added Successfully";
    }

    // Update Employee
    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable int id) {
        return "Employee Updated Successfully for ID: " + id;
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return "Employee Deleted Successfully for ID: " + id;
    }
}