package com.project.employee.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.employee.model.Employee;

@Repository
public class EmployeeRepository {

    public List<Employee> getEmployees() {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(1, "Anil", "DevOps"));
        employees.add(new Employee(2, "Rahul", "Cloud"));
        employees.add(new Employee(3, "Kiran", "Platform"));

        return employees;
    }
}