package com.example.employeemanagementservice.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(UUID id) {
        super("User with id " + id + " not found");
    }
}
