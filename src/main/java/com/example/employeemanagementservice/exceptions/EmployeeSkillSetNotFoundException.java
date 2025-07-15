package com.example.employeemanagementservice.exceptions;

import java.util.UUID;

public class EmployeeSkillSetNotFoundException extends RuntimeException{
    public EmployeeSkillSetNotFoundException(UUID id) {
        super("Employee Skill Set with id " + id + " not found");
    }
}
