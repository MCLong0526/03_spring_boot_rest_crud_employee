package com.michael.cruddemo.dao;

import com.michael.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
}
