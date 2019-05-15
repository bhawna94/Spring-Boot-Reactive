package edu.knoldus.employee.couchbase.service;

import edu.knoldus.employee.couchbase.model.Employee;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface EmployeeCouchbaseService {

    Employee getEmployeeById(String employeeId);

    CompletableFuture<Employee> getEmployeeByIdAndName(String id, String name);

    Employee addEmployee(Employee employee);

    Mono<Employee> getEmployee(String id);

    Mono<List<Employee>> getEmployeeUsingCustomQuery(String name);

}
