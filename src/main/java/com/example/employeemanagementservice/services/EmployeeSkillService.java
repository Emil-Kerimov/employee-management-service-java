package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.models.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeSkillService {
    private final EmployeeService employeeService;
    private final Map<UUID, EmployeeSkillSet> sets = new HashMap<>();

    public EmployeeSkillService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public EmployeeSkillSet getEmployeeSkillSetById(UUID id) {
        employeeService.getEmployeeById(id);
        return Optional.ofNullable(sets.get(id))
                .orElse(new EmployeeSkillSet(id));
    }
    public EmployeeSkillSet setEmployeeSkillSetById(UUID id, Map<Integer,
            List<EmployeeSkill>> categorySkills) {
        employeeService.getEmployeeById(id);

        EmployeeSkillSet employeeSkillSet;
        if(sets.containsKey(id)){
            //if skillset exist update it
            employeeSkillSet = sets.get(id);
            employeeSkillSet.setCategorySkills(categorySkills);
        } else {
            employeeSkillSet = new EmployeeSkillSet(id, categorySkills);
        }

        sets.put(employeeSkillSet.getEmployeeId(), employeeSkillSet);
        return employeeSkillSet;
    }
}
