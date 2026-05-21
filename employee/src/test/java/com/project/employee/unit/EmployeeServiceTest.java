package com.project.employee.unit;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.project.employee.model.Employee;
import com.project.employee.repository.EmployeeRepository;
import com.project.employee.service.EmployeeService;

public class EmployeeServiceTest {

    @Test
    void testGetEmployees() {

        EmployeeRepository repository =
                new EmployeeRepository();

        EmployeeService service =
                new EmployeeService(repository);

        List<Employee> employees =
                service.getEmployees();

        Assertions.assertEquals(3, employees.size());
    }
}