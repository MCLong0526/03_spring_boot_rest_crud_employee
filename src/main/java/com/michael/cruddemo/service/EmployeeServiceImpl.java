package com.michael.cruddemo.service;

import com.michael.cruddemo.dao.EmployeeDAO;
import com.michael.cruddemo.dao.EmployeeRepository;
import com.michael.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

//    private EmployeeDAO employeeDAO;

//    @Autowired
//    public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO){
//        employeeDAO = theEmployeeDAO;
//    }

//    @Override
//    public List<Employee> findAll() {
//        return employeeDAO.findAll();
//    }
//
//    @Override
//    public Employee findById(int theId) {
//        return employeeDAO.findById(theId);
//    }
//
//    @Override
//    @Transactional
//    public Employee save(Employee theEmployee) {
//        return employeeDAO.save(theEmployee);
//    }
//
//    @Override
//    @Transactional
//    public void deleteById(int theId) {
//        employeeDAO.deleteById(theId);
//    }
//
//    @Override
//    public Employee update(Employee theEmployee) {
//        return employeeDAO.update(theEmployee);
//    }

    // change employeeDAO to EmployeeRepository
    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository){
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> byId = employeeRepository.findById(theId);
        Employee theEmployee = null;

        if(byId.isPresent()){
            theEmployee = byId.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + theId);
        }
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public Employee update(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }
}
