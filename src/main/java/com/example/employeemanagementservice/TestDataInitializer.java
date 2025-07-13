package com.example.employeemanagementservice;

import com.example.employeemanagementservice.controllers.EmployeesController;
import com.example.employeemanagementservice.models.AppConfiguration;
import com.example.employeemanagementservice.models.Employee;
import com.example.employeemanagementservice.repositories.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TestDataInitializer {
    private final AppConfiguration appConfiguration;
    private final EmployeeRepository employeeRepository;

    public TestDataInitializer(AppConfiguration appConfiguration, EmployeeRepository employeeRepository) {
        this.appConfiguration = appConfiguration;
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public  void init(){
        if (appConfiguration.getTestMode().isEnabled()){
            loadTestData();
        }
    }
    public void loadTestData(){
        Employee employee = employeeRepository.save(new Employee("Name1", "LastName1", "Title1", LocalDate.of(2025, 1, 1)));
    }
}
