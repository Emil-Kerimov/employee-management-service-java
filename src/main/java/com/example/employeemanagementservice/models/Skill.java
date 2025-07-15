package com.example.employeemanagementservice.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "A skill that can be assigned to employee")
public class Skill {
    @Schema(
            description = "Unique identifier of the skill",
            example = "101",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer id;
    @Schema(
            description = "Name of the skill",
            example = "Java Programming",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;
    @Schema(
            description = "ID of the category this skill belongs to",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer categoryId;

    public Skill() {
    }

    public Skill(Integer id, String name, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
