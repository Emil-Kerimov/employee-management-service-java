package com.example.employeemanagementservice.repositories;

import com.example.employeemanagementservice.models.EmployeeSkill;
import com.example.employeemanagementservice.models.EmployeeSkillSet;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class EmployeeSkillRepository {
    private final Map<UUID, EmployeeSkillSet> sets = new HashMap<>();
    public EmployeeSkillSet getSkillSetById(UUID id) {
        return sets.get(id);
    }

    public EmployeeSkillSet save(UUID id, Map<Integer,
            List<EmployeeSkill>> categorySkills) {
        EmployeeSkillSet employeeSkillSet = new EmployeeSkillSet(id, categorySkills);
        sets.put(id, employeeSkillSet);
        return employeeSkillSet;
    }
}
