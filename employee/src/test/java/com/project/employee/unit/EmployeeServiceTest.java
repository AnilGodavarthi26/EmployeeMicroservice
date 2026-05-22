package com.project.employee.unit;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.project.employee.controller.EmployeeController;
import com.project.employee.model.Employee;
import com.project.employee.repository.EmployeeRepository;
import com.project.employee.service.EmployeeService;

class EmployeeServiceTest {

    private EmployeeService employeeService;
    private EmployeeController employeeController;

    @BeforeEach
    void setup() {
        employeeService = mock(EmployeeService.class);
        employeeController = new EmployeeController(employeeService);
    }
    
    @Test
    void testGetEmployees1() {

        EmployeeRepository repository =
                new EmployeeRepository();

        EmployeeService service =
                new EmployeeService(repository);

        List<Employee> employees =
                service.getEmployees();

        Assertions.assertEquals(3, employees.size());
    }

    @Test
    void testHealth() {

        String response = employeeController.health();

        assertEquals(
                "Employee Application Running ",
                response
        );
    }

    @Test
    void testGetEmployees() {

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Anil", "DevOps"),
                new Employee(2, "Rahul", "Cloud")
        );

        when(employeeService.getEmployees())
                .thenReturn(employees);

        List<Employee> result =
                employeeController.getEmployees();

        assertEquals(2, result.size());

        assertEquals(
                "Anil",
                result.get(0).getName()
        );
    }

    @Test
    void testGetEmployeeById() {

        String response =
                employeeController.getEmployeeById(1);

        assertTrue(response.contains("1"));
    }

    @Test
    void testAddEmployee() {

        String response =
                employeeController.addEmployee();

        assertEquals(
                "Employee Added Successfully",
                response
        );
    }

    @Test
    void testUpdateEmployee() {

        String response =
                employeeController.updateEmployee(1);

        assertTrue(response.contains("1"));
    }

    @Test
    void testDeleteEmployee() {

        String response =
                employeeController.deleteEmployee(1);

        assertTrue(response.contains("1"));
    }
}


    