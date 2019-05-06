package edu.knoldus.employee.couchbase.controller;

import edu.knoldus.employee.couchbase.model.Employee;
import edu.knoldus.employee.couchbase.service.EmployeeCouchbaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private EmployeeCouchbaseService employeeCouchbaseService;

    public EmployeeController(EmployeeCouchbaseService employeeCouchbaseService) {
        this.employeeCouchbaseService = employeeCouchbaseService;
    }

    @GetMapping("employee/get/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
          return employeeCouchbaseService.getEmployeeById(id);
    }

    @GetMapping("employee/get/id/{id}/name/{name}")
    public Employee getEmployeeByIdAndName(@PathVariable String id, String name) {
        return employeeCouchbaseService.getEmployeeByIdAndName(id, name);

    }
}
