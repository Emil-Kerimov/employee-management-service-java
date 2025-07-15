package com.example.employeemanagementservice.exceptions;

import java.util.UUID;

public class SkillNotFoundException extends RuntimeException{
    public SkillNotFoundException(Integer id) {
        super("Skill with id " + id + " not found");
    }
}
