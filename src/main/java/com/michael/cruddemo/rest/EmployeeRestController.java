package com.michael.cruddemo.rest;

import com.michael.cruddemo.dao.EmployeeDAO;
import com.michael.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    @Autowired
    private EmployeeDAO employeeDAO;

    // can either use @Autowired on the field, or constructor injection
//    private EmployeeDAO employeeDAO;
//
//    public EmployeeRestController(EmployeeDAO employeeDAO){
//        this.employeeDAO = employeeDAO;
//    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }


}
