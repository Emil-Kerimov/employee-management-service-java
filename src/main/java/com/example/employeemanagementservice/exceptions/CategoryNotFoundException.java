package com.example.employeemanagementservice.exceptions;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(Integer id) {
        super("Category with id " + id + " not found");
    }
}
