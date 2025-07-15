package com.example.employeemanagementservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.employeemanagementservice.exceptions.UserNotFoundException;
import com.example.employeemanagementservice.models.Employee;
import com.example.employeemanagementservice.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(String firstName, String lastName, String title, LocalDate
            birthday) {
        Employee employee = new Employee(firstName, lastName, title, birthday);
        return employeeRepository.save(employee);
    }
    public Employee getEmployeeById(UUID id) {
        return Optional.ofNullable(employeeRepository.getEmployeeById(id))
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    public List<Employee> getAllEmployee() {
        return new ArrayList<>(employeeRepository.findAll());
    }
    public Employee updateEmployee(UUID id, String firstName, String lastName, String title,
                                   LocalDate birthday) {
        Employee employee = Optional.ofNullable(employeeRepository.getEmployeeById(id))
                .orElseThrow(() -> new UserNotFoundException(id));

            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setTitle(title);
            employee.setBirthday(birthday);

            return employeeRepository.save(employee);
    }
    public void deleteEmployee(UUID id) {
        if(!employeeRepository.delete(id)){
            throw new UserNotFoundException(id);
        }
    }
}