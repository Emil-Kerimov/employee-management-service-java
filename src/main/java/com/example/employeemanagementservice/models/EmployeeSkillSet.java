package com.example.employeemanagementservice.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Schema(
        description = "Map of skills",
        example = """
                    {
                      "employeeId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
                      "categorySkills": {
                        "1": [
                              {
                                "skillId": 101,
                                "level": "EXPERT"
                              }
                            ],
                        "2": [
                              {
                                "skillId": 101,
                                "level": "EXPERT"
                              }
                            ],
                        "3": [
                              {
                                "skillId": 101,
                                "level": "EXPERT"
                              }
                            ]
                          }
                """
)
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
