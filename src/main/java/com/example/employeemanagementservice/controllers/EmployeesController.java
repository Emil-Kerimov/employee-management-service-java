package com.example.employeemanagementservice.controllers;

import com.example.employeemanagementservice.annotations.*;
import com.example.employeemanagementservice.models.Employee;
import com.example.employeemanagementservice.models.EmployeeSkill;
import com.example.employeemanagementservice.models.EmployeeSkillSet;
import com.example.employeemanagementservice.services.EmployeeService;
import com.example.employeemanagementservice.services.EmployeeSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
@Tag(name = "Employee management", description = "API for viewing, adding, deleting, and updating employees")
@Validated
public class EmployeesController {
    private final EmployeeService employeeService;
    private final EmployeeSkillService employeeSkillService;
    public EmployeesController(EmployeeService employeeService, EmployeeSkillService employeeSkillService) {
        this.employeeService = employeeService;
        this.employeeSkillService = employeeSkillService;
    }

    @GetMapping("/{id}")
    @CommonSuccessfullyResponses
    @UUIDNotFoundResponses
    @InternalServerErrorResponse
    @Operation(
            summary = "Search employee by ID",
            description = "returns the employee with the specified ID"
    )
    public Employee getEmployeeById(
            @EmployeeIdParam
            @PathVariable UUID id) {
        return employeeService.getEmployeeById(id);
    }
    @GetMapping
    @CommonSuccessfullyResponses
    @InternalServerErrorResponse
    @Operation(
            summary = "find all employees",
            description = "returns all available employees in memory"
    )
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployee();
    }
    @PostMapping
    @CommonSuccessfullyResponses
    @InternalServerErrorResponse
    @Operation(
            summary = "Create new employee",
            description = "creates and saves new employee"
    )
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        Employee employee = employeeService.createEmployee(request.firstName(),
                request.lastName(), request.title(), request.birthday());
        return ResponseEntity.ok(employee);
    }
    @DeleteMapping ("/{id}")
    @DeleteApiResponses
    @UUIDNotFoundResponses
    @InternalServerErrorResponse
    @Operation(
            summary = "Delete employee with specified ID",
            description = "deletes employee with specified ID from memory"
    )
    public ResponseEntity<Void> deleteEmployeeById(
            @EmployeeIdParam
            @PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping ("/{id}")
    @CommonSuccessfullyResponses
    @UUIDNotFoundResponses
    @InternalServerErrorResponse
    @Operation(
            summary = "Update information of employee with specified ID",
            description = "updates information of employee with specified ID in memory if it exist or throw 404"
    )
    public ResponseEntity<Employee> changeEmployeeById(
            @EmployeeIdParam
            @PathVariable UUID id,
            @Valid @RequestBody EmployeeRequest request) {
        Employee employee = employeeService.updateEmployee(id, request.firstName(),
                request.lastName(), request.title(), request.birthday());
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/{id}/skills-set")
    @CommonSuccessfullyResponses
    @UUIDNotFoundResponses
    @InternalServerErrorResponse
    @Operation(
            summary = "Finds skill set of employee with specified ID",
            description = "returns empty or filled with data skill set of employee with the specified ID if he exist or throw 404"
    )
    public EmployeeSkillSet getEmployeeSkillsSetById(@EmployeeIdParam @PathVariable UUID id) {
        return employeeSkillService.getEmployeeSkillSetById(id);
    }
    @PostMapping ("/{id}/skills-set")
    @CommonSuccessfullyResponses
    @UUIDNotFoundResponses
    @InternalServerErrorResponse
    @Operation(
            summary = "Create skill set for employee with specified ID",
            description = "returns  filled with data skill set of employee with the specified ID if he exist or throw 404"
    )
    public ResponseEntity<EmployeeSkillSet> setEmployeeSkills(
            @EmployeeIdParam @PathVariable UUID id, @RequestBody SkillSetRequest request) {
        EmployeeSkillSet employeeSkillSet = employeeSkillService.setEmployeeSkillSetById(id, request.categorySkills());
        return ResponseEntity.ok(employeeSkillSet);
    }
    // DTO for Employee creation
    @Schema(description = "Request body for creating and updating Employee")
    public record EmployeeRequest(
            @NotBlank(message = "First name should not be blank")
            @Size(min = 2, max = 25, message = "First Name length should be 2 to 25 symbols long")
            @Schema(description = "First name of the employee", example = "John")
            String firstName,
            @NotBlank(message = "Last name should not be blank")
            @Size(min = 2, max = 25, message = "Last Name length should be 2 to 25 symbols long")
            @Schema(description = "Last name of the employee", example = "Smith")
            String lastName,
            @NotBlank(message = "Title should not be blank")
            @Size(min = 3, max = 25, message = "Title length should be 3 to 25 symbols long")
            @Schema(description = "Title of the employee", example = "Software Developer")
            String title,
            @NotNull(message = "Title should not be blank")
            @Past(message = "Birthday should be in the past date")
            @Schema(description = "Birthday of the employee", example = "2000-01-01")
            LocalDate birthday) {}
    // DTO for SkillSet creation
    @Schema(description = "Request body for creating Skill Set for Employee (UUID will be auto Generated)")
    public record SkillSetRequest(
            @Schema(
                    description = "Map of skills",
                    example = """
            {
                "1": [
                    {
                        "skillId": 101,
                        "level": "EXPERT"
                    }
                ],
                "2": [
                    {
                        "skillId": 102,
                        "level": "BEGINNER"
                    }
                ]
            }
            """
            )
            Map<Integer,
            List<EmployeeSkill>> categorySkills) {}
}
