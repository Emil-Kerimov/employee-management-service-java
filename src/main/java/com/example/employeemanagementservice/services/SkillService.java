package com.example.employeemanagementservice.services;

import com.example.employeemanagementservice.exceptions.SkillNotFoundException;
import com.example.employeemanagementservice.exceptions.UserNotFoundException;
import com.example.employeemanagementservice.models.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SkillService {
    private final EmployeeService employeeService;
    private final Map<UUID, EmployeeSkillSet> sets = new HashMap<>();
    private final Map<UUID, SkillCategory> categories = new HashMap<>();
    private final Map<Integer, Skill> skills = new HashMap<>();

    public SkillService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    public boolean updateEmployeeSkillSetById(UUID id, UUID uuid, Map<Integer, List<EmployeeSkill>> integerListMap) {
//        if (sets.containsKey(id)) {
//            EmployeeSkillSet employeeSkillSet = sets.get(id);
//            employeeSkillSet.setEmployeeId(uuid);
//            employeeSkillSet.setCategorySkills(integerListMap);
//            return true;
//        }
//        return false;
//    }

    public EmployeeSkillSet getEmployeeSkillSetById(UUID id) {
        employeeService.getEmployeeById(id);
        sets.get(id);
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
