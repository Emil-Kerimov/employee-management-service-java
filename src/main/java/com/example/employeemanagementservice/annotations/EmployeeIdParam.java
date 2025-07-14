package com.example.employeemanagementservice.annotations;

import io.swagger.v3.oas.annotations.Parameter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Parameter(description = "employee ID to execute operation for", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
public @interface EmployeeIdParam {
}
