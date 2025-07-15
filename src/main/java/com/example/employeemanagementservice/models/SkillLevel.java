package com.example.employeemanagementservice.models;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Proficiency level of a skill",
        example = "EXPERT"
)
public enum SkillLevel {
    @Schema(description = "Basic concepts understanding")
    BEGINNER,
    @Schema(description = "Can work independently")
    INTERMEDIATE,
    @Schema(description = "Has in-depth knowledge")
    ADVANCED,
    @Schema(description = "Expert level")
    EXPERT
}
