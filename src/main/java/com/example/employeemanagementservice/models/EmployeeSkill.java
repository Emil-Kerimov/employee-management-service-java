package com.example.employeemanagementservice.models;

public class EmployeeSkill {
    private Integer skillId;
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
