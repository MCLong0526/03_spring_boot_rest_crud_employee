package com.michael.cruddemo.service;

import com.michael.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int theId);
    Employee save(Employee theEmployee);
    void deleteById(int theId);
    Employee update(Employee theEmployee);
}
