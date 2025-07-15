package com.example.employeemanagementservice.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Skill assigned to an employee with skill id and a proficiency level")
public class EmployeeSkill {
    @Schema(
            description = "ID of the skill",
            example = "101",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer skillId;
    @Schema(
            description = "Proficiency level of the skill",
            example = "EXPERT",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private SkillLevel level;

    public EmployeeSkill() {
    }

    public EmployeeSkill(Integer skillId, SkillLevel level) {
        this.skillId = skillId;
        this.level = level;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public void setLevel(SkillLevel level) {
        this.level = level;
    }
}
