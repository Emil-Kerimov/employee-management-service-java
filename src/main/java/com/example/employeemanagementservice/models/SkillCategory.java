package com.example.employeemanagementservice.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Category grouping related skills")
public class SkillCategory {
    @Schema(
            description = "Unique identifier of the category",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer id;
    @Schema(
            description = "Name of the category",
            example = "Programming Languages",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;
    @Schema(
            description = "Area of expertise ('IT', 'Management')",
            example = "IT",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String area;

    public SkillCategory() {
    }

    public SkillCategory(Integer id, String name, String area) {
        this.id = id;
        this.name = name;
        this.area = area;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
