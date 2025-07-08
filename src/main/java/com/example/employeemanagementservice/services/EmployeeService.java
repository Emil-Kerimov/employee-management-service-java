package com.example.employeemanagementservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<Employee> getEmployeeById(UUID id) {
        return Optional.ofNullable(employees.get(id));
    }
    @Override
    public List<Employee> getAllEmployee() {
        return new ArrayList<>(employees.values());
    }
    @Override
    public boolean updateEmployee(UUID id, String firstName, String lastName, String title,
                              LocalDate birthday) {
        if (employees.containsKey(id)) {
            Employee employee = employees.get(id);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setTitle(title);
            employee.setBirthday(birthday);
            return true;
        }
        return false;
    }
    @Override
    public boolean deleteEmployee(UUID id) {
        return employees.remove(id) != null;
    }
}