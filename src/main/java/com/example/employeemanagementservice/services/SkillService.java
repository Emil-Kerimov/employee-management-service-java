package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.models.*;
import com.fasterxml.jackson.databind.ser.std.EnumSetSerializer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SkillService {
    private final Map<UUID, EmployeeSkillSet> sets = new HashMap<>();
    private final Map<UUID, SkillCategory> categories = new HashMap<>();
    private final Map<Integer, Skill> skills = new HashMap<>();

    public boolean updateEmployeeSkillSetById(UUID id, UUID uuid, Map<Integer, List<EmployeeSkill>> integerListMap) {
        if (sets.containsKey(id)) {
            EmployeeSkillSet employeeSkillSet = sets.get(id);
            employeeSkillSet.setEmployeeId(uuid);
            employeeSkillSet.setCategorySkills(integerListMap);
            return true;
        }
        return false;
    }

    public Optional<EmployeeSkillSet> getEmployeeSkillSetById(UUID id) {
        return Optional.ofNullable(sets.get(id));
    }
    public EmployeeSkillSet setEmployeeSkillSetById(UUID id, Map<Integer,
            List<EmployeeSkill>> categorySkills) {
        EmployeeSkillSet employeeSkillSet = new EmployeeSkillSet(id, categorySkills);
        sets.put(employeeSkillSet.getEmployeeId(), employeeSkillSet);
        return employeeSkillSet;
    }
}
