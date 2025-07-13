package com.example.employeemanagementservice;

import com.example.employeemanagementservice.models.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfiguration.class)
public class EmployeeManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementServiceApplication.class, args);
    }

}
