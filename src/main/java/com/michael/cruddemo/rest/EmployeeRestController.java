package com.michael.cruddemo.rest;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.michael.cruddemo.entity.Employee;
import com.michael.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    //Now instead of using DAO layer, we will use the service layer
//    @Autowired
//    private EmployeeDAO employeeDAO;

    // can either use @Autowired on the field, or constructor injection
//    private EmployeeDAO employeeDAO;
//
//    public EmployeeRestController(EmployeeDAO employeeDAO){
//        this.employeeDAO = employeeDAO;
//    }

//    @Autowired
//    private EmployeeService employeeService;

    // constructor injection
    private EmployeeService employeeService;

    private ObjectMapper objectMapper;

    public EmployeeRestController(EmployeeService theEmployeeService, ObjectMapper theObjectMapper){
        employeeService = theEmployeeService;
        objectMapper = theObjectMapper;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable int id){
        Employee theEmployee = employeeService.findById(id);
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - " + id);
        }
        return theEmployee;
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //if the employee id is int, then it will be 0 by default
        //if the employee id is Integer, then it will be null by default
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee dbEmployee = employeeService.findById(id);
        if(dbEmployee == null){
            throw new RuntimeException("Employee id not found - " + id);
        } else {
            employeeService.deleteById(id);
            return "Deleted employee id - " + id;
        }
    }

    @PatchMapping("/employee/{id}")
    public Employee patchEmployee(@PathVariable int id, @RequestBody Map<String, Object> updates) throws JsonMappingException {
        Employee employee = employeeService.findById(id);
        if(employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        if(updates.containsKey("id")){
            throw new RuntimeException("Cannot update employee id - " + id);
        }
        Employee updatedEmployee = apply(updates, employee);
//        Employee updatedEmployee = objectMapper.updateValue(employee, updates);
        return employeeService.save(updatedEmployee);
    }

    private Employee apply(Map<String, Object> updates, Employee employee){
        // Convert Employee to JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);

        // Convert updates map to JSON object node
        ObjectNode updatesNode = objectMapper.convertValue(updates, ObjectNode.class);

        // merge the patch updates into the employee object
        employeeNode.setAll(updatesNode);
        return objectMapper.convertValue(employeeNode, Employee.class);
    }



}
