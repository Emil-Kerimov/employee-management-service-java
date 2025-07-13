package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.annotations.CommonEmployeeResponses;
import com.example.employeemanagementservice.exceptions.GlobalExceptionHandler;
import com.example.employeemanagementservice.models.Employee;
import com.example.employeemanagementservice.models.EmployeeSkill;
import com.example.employeemanagementservice.models.EmployeeSkillSet;
import com.example.employeemanagementservice.services.EmployeeService;
import com.example.employeemanagementservice.services.EmployeeSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee management", description = "API for viewing, adding, deleting, and updating employees")
public class EmployeesController {
    private final EmployeeService employeeService;
    private final EmployeeSkillService employeeSkillService;
    public EmployeesController(EmployeeService employeeService, EmployeeSkillService employeeSkillService) {
        this.employeeService = employeeService;
        this.employeeSkillService = employeeSkillService;
    }
    @CommonEmployeeResponses
    @GetMapping("/{id}")
    @Operation(
            summary = "Search employee by ID",
            description = "returns the employee with the specified ID"
    )
    public Employee getEmployeeById(@PathVariable UUID id) {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping
    @CommonEmployeeResponses
    @Operation(
            summary = "find all employees",
            description = "returns all available employees in memory"
    )
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployee();
    }
    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created and saved new employee")
    })
    @Operation(
            summary = "Create new employee",
            description = "creates and saves new employee"
    )
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest request) {
        Employee employee = employeeService.createEmployee(request.firstName(),
                request.lastName(), request.title(), request.birthday());
        return ResponseEntity.ok(employee);
    }
    @DeleteMapping ("/{id}")
    @CommonEmployeeResponses
    @Operation(
            summary = "Delete employee with specified ID",
            description = "deletes employee with specified ID from memory"
    )
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping ("/{id}")
    @CommonEmployeeResponses
    @Operation(
            summary = "Update information of employee with specified ID",
            description = "updates information of employee with specified ID in memory if it exist or throw 404"
    )
    public ResponseEntity<Employee> changeEmployeeById(@PathVariable UUID id, @RequestBody EmployeeRequest request) {
        Employee employee = employeeService.updateEmployee(id, request.firstName(),
                request.lastName(), request.title(), request.birthday());
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/{id}/skills-set")
    @CommonEmployeeResponses
    @Operation(
            summary = "Finds skill set of employee with specified ID",
            description = "returns empty or filled with data skill set of employee with the specified ID if he exist or throw 404"
    )
    public EmployeeSkillSet getEmployeeSkillsSetById(@PathVariable UUID id) {
        return employeeSkillService.getEmployeeSkillSetById(id);
    }
    @PostMapping ("/{id}/skills-set")
    @CommonEmployeeResponses
    @Operation(
            summary = "Create skill set for employee with specified ID",
            description = "returns  filled with data skill set of employee with the specified ID if he exist or throw 404"
    )
    public ResponseEntity<EmployeeSkillSet> setEmployeeSkills(@PathVariable UUID id, @RequestBody SkillSetRequest request) {
        EmployeeSkillSet employeeSkillSet = employeeSkillService.setEmployeeSkillSetById(id, request.categorySkills());
        return ResponseEntity.ok(employeeSkillSet);
    }
    // DTO for Employee creation
    public record EmployeeRequest(String firstName, String lastName, String title, LocalDate
            birthday) {}
    // DTO for SkillSet creation
    public record SkillSetRequest(Map<Integer,
            List<EmployeeSkill>> categorySkills) {}
}
