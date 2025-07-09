package com.example.employeemanagementservice.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EmployeeSkillSet {
    private UUID employeeId;
    private Map<Integer, List<EmployeeSkill>> categorySkills; // categoryId -> skills

    public EmployeeSkillSet(UUID employeeId) {
        this.employeeId = employeeId;
        this.categorySkills = new HashMap<>();
    }

    public EmployeeSkillSet(UUID employeeId, Map<Integer, List<EmployeeSkill>> categorySkills) {
        this.employeeId = employeeId;
        this.categorySkills = categorySkills;

    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public Map<Integer, List<EmployeeSkill>> getCategorySkills() {
        return categorySkills;
    }

    public void setCategorySkills(Map<Integer, List<EmployeeSkill>> categorySkills) {
        this.categorySkills = categorySkills;
    }
}
