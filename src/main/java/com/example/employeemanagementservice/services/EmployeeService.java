package com.example.employeemanagementservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.example.employeemanagementservice.exceptions.UserNotFoundException;
import com.example.employeemanagementservice.models.Employee;
import org.springframework.stereotype.Service;
@Service
public class EmployeeService implements IEmployeeService {
    private final Map<UUID, Employee> employees = new HashMap<>();
    @Override
    public Employee createEmployee(String firstName, String lastName, String title, LocalDate
            birthday) {
        Employee employee = new Employee(firstName, lastName, title, birthday);
        employees.put(employee.getId(), employee);
        return employee;
    }
    @Override
    public Employee getEmployeeById(UUID id) {
        return Optional.ofNullable(employees.get(id))
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    @Override
    public List<Employee> getAllEmployee() {
        return new ArrayList<>(employees.values());
    }
    @Override
    public Employee updateEmployee(UUID id, String firstName, String lastName, String title,
                                   LocalDate birthday) {
        Employee employee = Optional.ofNullable(employees.get(id))
                .orElseThrow(() -> new UserNotFoundException(id));

            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setTitle(title);
            employee.setBirthday(birthday);
            return employee;
    }
    @Override
    public void deleteEmployee(UUID id) {
        if(employees.remove(id) == null){
            throw new UserNotFoundException(id);
        }
    }
}