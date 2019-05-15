package edu.knoldus.employee.couchbase.controller;

import edu.knoldus.employee.couchbase.model.Employee;
import edu.knoldus.employee.couchbase.service.EmployeeCouchbaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<Employee> getEmployeeByIdAndName(@PathVariable String id, @PathVariable String name) {
        return employeeCouchbaseService.getEmployeeByIdAndName(id, name);

    }

    @PostMapping("employee/update")
    public Employee addEmployee(@RequestBody Employee employee) {
          return employeeCouchbaseService.addEmployee(employee);
    }

    @GetMapping("employee/get/emp/{id}")
    public Mono<Employee> getEmployee(@PathVariable String id) {
        System.out.println(id + "fhds");
     return employeeCouchbaseService.getEmployee(id);
    }

    @GetMapping("employee/get/custom/query/{name}")
    public Mono<List<Employee>> getEmployeeUsingCustomQuery(@PathVariable String name) {
       return employeeCouchbaseService.getEmployeeUsingCustomQuery(name);
    }


}
