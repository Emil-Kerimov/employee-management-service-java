package com.example.employeemanagementservice.exceptions;

import java.util.UUID;

public class SkillNotFoundException extends RuntimeException{
    public SkillNotFoundException(UUID id) {
        super("Skill set with id" + id + " not found");
    }
}
