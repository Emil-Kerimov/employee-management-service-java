package com.example.employeemanagementservice;

import com.example.employeemanagementservice.models.*;
import com.example.employeemanagementservice.repositories.EmployeeRepository;
import com.example.employeemanagementservice.repositories.EmployeeSkillRepository;
import com.example.employeemanagementservice.repositories.SkillCategoryRepository;
import com.example.employeemanagementservice.repositories.SkillRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Component
public class TestDataInitializer {
    private final AppConfiguration appConfiguration;
    private final EmployeeRepository employeeRepository;
    private final EmployeeSkillRepository employeeSkillRepository;
    private final SkillCategoryRepository skillCategoryRepository;
    private final SkillRepository skillRepository;

    public TestDataInitializer(AppConfiguration appConfiguration, EmployeeRepository employeeRepository,
                               EmployeeSkillRepository employeeSkillRepository, SkillCategoryRepository skillCategoryRepository,
                               SkillRepository skillRepository) {
        this.appConfiguration = appConfiguration;
        this.employeeRepository = employeeRepository;
        this.employeeSkillRepository = employeeSkillRepository;
        this.skillCategoryRepository = skillCategoryRepository;
        this.skillRepository = skillRepository;
    }

    @PostConstruct
    public  void init(){
        if (appConfiguration.getTestMode().isEnabled()){
            loadTestData();
        }
    }
    public void loadTestData(){
        Employee employee = employeeRepository.save(
                new Employee("Name1", "LastName1", "Title1",
                        LocalDate.of(2025, 1, 1)));
        Employee employeeSecond = employeeRepository.save(
                new Employee("Name2", "LastName2", "Title2",
                        LocalDate.of(2025, 2, 2)));
        employeeRepository.save(
                new Employee("Name3", "LastName3", "Title3",
                        LocalDate.of(2025, 3, 3)));
        employeeRepository.save(
                new Employee("Name4", "LastName4", "Title4",
                        LocalDate.of(2025, 4, 4)));

        employeeSkillRepository.save(
                employee.getId(), Map.of(1, List.of(new EmployeeSkill(101, SkillLevel.BEGINNER))));
        employeeSkillRepository.save(
                employeeSecond.getId(), Map.of(
                        1, List.of(new EmployeeSkill(101, SkillLevel.BEGINNER)),
                        2, List.of(new EmployeeSkill(102, SkillLevel.BEGINNER))));

        SkillCategory skillCategory = skillCategoryRepository.save("skillCategoryName1", "CategoryArea1");
        SkillCategory skillCategorySecond = skillCategoryRepository.save("skillCategoryName2", "CategoryArea2");
        skillCategoryRepository.save("skillCategoryName3", "CategoryArea3");

        skillRepository.save("SkillName1", skillCategory.getId());
        skillRepository.save("SkillName2", skillCategorySecond.getId());
        skillRepository.save("SkillName3", skillCategorySecond.getId());
    }
}
