package com.example.employeemanagementservice.models;

public class SkillCategory {
    private Integer id;
    private String name;
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
