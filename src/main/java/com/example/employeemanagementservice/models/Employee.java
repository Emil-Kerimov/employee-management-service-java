package com.example.employeemanagementservice.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;
@Schema(description = "Employee entity representing a worker in the company")
public class Employee {
    @Schema(
            description = "Unique identifier of the employee",
            example = "a1b2c3d4-e5f6-7890-1234-567890abcdef"
    )
    private UUID id;
    @Schema(
            description = "firstName of the employee",
            example = "John",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String firstName;
    @Schema(
            description = "lastName of the employee",
            example = "Doe",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String lastName;
    @Schema(
            description = "Job title of the employee",
            example = "Software Engineer",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String title;
    @Schema(
            description = "Date of birth of the employee (YYYY-MM-DD)",
            example = "2000-01-21",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LocalDate birthday;
    public Employee() {
        this.id = UUID.randomUUID();
    }
    public Employee(String firstName, String lastName, String title, LocalDate birthday) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.birthday = birthday;
    }
    // Getters and Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
