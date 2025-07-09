package com.example.employeemanagementservice.models;

import java.time.LocalDate;
import java.util.UUID;
public class Employee {
    private UUID id;
    private String firstName;
    private String lastName;
    private String title;
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
