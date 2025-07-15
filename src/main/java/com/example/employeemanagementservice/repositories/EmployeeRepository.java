package com.example.employeemanagementservice.repositories;

import com.example.employeemanagementservice.models.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepository {
    private final Map<UUID, Employee> employees = new HashMap<>();
    public List<Employee> findAll(){
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployeeById(UUID id) {
        return employees.get(id);
    }

    public Employee save(Employee employee) {
        employees.put(employee.getId(), employee);
        return employee;
    }

    public boolean delete(UUID id) {
        return employees.remove(id) != null;
    }
}
