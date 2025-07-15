package com.example.employeemanagementservice.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.employeemanagementservice.controllers")
public class GlobalExceptionHandler {
// for error response

    public record ErrorResponse(
            @Schema(
                    description = "description of the error",
                    example = "Employee with id a1b2c3d4-e5f6-7890-1234-567890abcdef not found"
            )
            String error) {}
    @ExceptionHandler({UserNotFoundException.class, SkillNotFoundException.class, CategoryNotFoundException.class, EmployeeSkillSetNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException ex) {
        var errorResponse = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class) // Catch-all handler for unexpected exceptions
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        var errorResponse = new ErrorResponse("Internal server error(invalid data)");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
