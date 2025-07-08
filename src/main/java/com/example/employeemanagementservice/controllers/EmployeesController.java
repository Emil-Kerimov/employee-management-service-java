package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.models.Employee;
import com.example.employeemanagementservice.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeeService employeeService;
    // Constructor injection (recommended)
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable UUID id) {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployee();
    }
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest request) {
        Employee employee = employeeService.createEmployee(request.firstName(),
                request.lastName(), request.title(), request.birthday());
        return ResponseEntity.ok(employee);
    }
    @DeleteMapping ("/{id}")
    public boolean DeleteEmployeeById(@PathVariable UUID id) {
        return employeeService.deleteEmployee(id);
    }
    @PutMapping ("/{id}")
    public boolean ChangeEmployeeById(@PathVariable UUID id, @RequestBody EmployeeRequest request) {
        return employeeService.updateEmployee(id, request.firstName(), request.lastName(), request.title(), request.birthday());
    }
    // DTO for Employee creation
    public record EmployeeRequest(String firstName, String lastName, String title, LocalDate
            birthday) {}
}
