package com.example.employeemanagementservice.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Schema(
        description = "Collection of skills grouped by category for an employee")
public class EmployeeSkillSet {
    @Schema(
            description = "Unique identifier of the employee",
            example = "a1b2c3d4-e5f6-7890-1234-567890abcdef"
    )
    private UUID employeeId;
    @Schema(
            description = "Map where key is category ID and value is list of skills in this category",
            example = """
            {
              "1": [{"skillId": 101, "level": "EXPERT"}],
              "2": [{"skillId": 102, "level": "BEGINNER"}]
            }
            """
    )
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
