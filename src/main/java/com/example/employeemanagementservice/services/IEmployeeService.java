package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.models.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IEmployeeService {
    // Create Employee
    Employee createEmployee(String firstName, String lastName,String title, LocalDate birthday);
    // Get Employee by ID
    Employee getEmployeeById(UUID id);

    // Get All Users
    List<Employee> getAllEmployee();
    // Update Employee
    Employee updateEmployee(UUID id, String firstName, String lastName, String title, LocalDate
            birthday);
    // Delete Employee
    void deleteEmployee(UUID id);
}