package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.models.Employee;
import com.example.employeemanagementservice.models.EmployeeSkill;
import com.example.employeemanagementservice.models.EmployeeSkillSet;
import com.example.employeemanagementservice.services.EmployeeService;
import com.example.employeemanagementservice.services.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeeService employeeService;
    private final SkillService skillService; //wh
    // Constructor injection (recommended)
    public EmployeesController(EmployeeService employeeService, SkillService skillService) {
        this.employeeService = employeeService;
        this.skillService = skillService;
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable UUID id) {
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
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping ("/{id}")
    public ResponseEntity<Employee> changeEmployeeById(@PathVariable UUID id, @RequestBody EmployeeRequest request) {
        Employee employee = employeeService.updateEmployee(id, request.firstName(),
                request.lastName(), request.title(), request.birthday());
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/{id}/skills-set")
    public EmployeeSkillSet getEmployeeSkillsSetById(@PathVariable UUID id) {
        return skillService.getEmployeeSkillSetById(id);
    }
    @PostMapping ("/{id}/skills-set")
    public ResponseEntity<EmployeeSkillSet> setEmployeeSkills(@PathVariable UUID id, @RequestBody SkillSetRequest request) {
        EmployeeSkillSet employeeSkillSet = skillService.setEmployeeSkillSetById(id, request.categorySkills());
        return ResponseEntity.ok(employeeSkillSet);
    }
    // DTO for Employee creation
    public record EmployeeRequest(String firstName, String lastName, String title, LocalDate
            birthday) {}
    // DTO for SkillSet creation
    public record SkillSetRequest(Map<Integer,
            List<EmployeeSkill>> categorySkills) {}
}
