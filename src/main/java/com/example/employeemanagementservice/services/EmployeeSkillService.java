package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.exceptions.EmployeeSkillSetNotFoundException;
import com.example.employeemanagementservice.exceptions.UserNotFoundException;
import com.example.employeemanagementservice.models.*;
import com.example.employeemanagementservice.repositories.EmployeeRepository;
import com.example.employeemanagementservice.repositories.EmployeeSkillRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeSkillService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeSkillRepository skillRepository;

    public EmployeeSkillService(EmployeeRepository employeeRepository, EmployeeSkillRepository skillRepository) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    public EmployeeSkillSet getEmployeeSkillSetById(UUID id) {
        if(employeeRepository.getEmployeeById(id) == null){
            throw new UserNotFoundException(id);
        }
        return Optional.ofNullable(skillRepository.getSkillSetById(id))
                .orElseThrow(() -> new EmployeeSkillSetNotFoundException(id));
    }
    public EmployeeSkillSet setEmployeeSkillSetById(UUID id, Map<Integer,
            List<EmployeeSkill>> categorySkills) {
        if(employeeRepository.getEmployeeById(id) == null){
            throw new UserNotFoundException(id);
        }

        return skillRepository.save(id, categorySkills);
    }
}
