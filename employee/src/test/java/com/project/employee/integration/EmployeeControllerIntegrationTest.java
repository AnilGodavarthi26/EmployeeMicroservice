package com.project.employee.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(
        webEnvironment =
                SpringBootTest.WebEnvironment.RANDOM_PORT
)
class EmployeeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // Health API Test
    @Test
    void testHealthApi() {

        String response =
                restTemplate.getForObject(
                        "http://localhost:" + port +
                                "/api/employees/health",
                        String.class
                );

        assertTrue(
                response.contains("Running")
        );
    }

    // Get All Employees Test
    @Test
    void testGetEmployeesApi() {

        String response =
                restTemplate.getForObject(
                        "http://localhost:" + port +
                                "/api/employees",
                        String.class
                );

        assertTrue(
                response.contains("Anil")
        );
    }

    // Get Employee By ID Test
    @Test
    void testGetEmployeeByIdApi() {

        String response =
                restTemplate.getForObject(
                        "http://localhost:" + port +
                                "/api/employees/1",
                        String.class
                );

        assertTrue(
                response.contains("1")
        );
    }

    // Add Employee Test
    @Test
    void testAddEmployeeApi() {

        HttpHeaders headers =
                new HttpHeaders();

        HttpEntity<String> request =
                new HttpEntity<>(null, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "http://localhost:" + port +
                                "/api/employees",
                        request,
                        String.class
                );

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertTrue(
                response.getBody()
                        .contains("Added")
        );
    }

    // Update Employee Test
    @Test
    void testUpdateEmployeeApi() {

        HttpHeaders headers =
                new HttpHeaders();

        HttpEntity<String> request =
                new HttpEntity<>(null, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "http://localhost:" + port +
                                "/api/employees/1",
                        HttpMethod.PUT,
                        request,
                        String.class
                );

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertTrue(
                response.getBody()
                        .contains("Updated")
        );
    }

    // Delete Employee Test
    @Test
    void testDeleteEmployeeApi() {

        HttpHeaders headers =
                new HttpHeaders();

        HttpEntity<String> request =
                new HttpEntity<>(null, headers);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "http://localhost:" + port +
                                "/api/employees/1",
                        HttpMethod.DELETE,
                        request,
                        String.class
                );

        assertEquals(
                HttpStatus.OK,
                response.getStatusCode()
        );

        assertTrue(
                response.getBody()
                        .contains("Deleted")
        );
    }
}