package edu.knoldus.employee.couchbase.service;

import edu.knoldus.employee.couchbase.model.Employee;

import java.util.concurrent.CompletableFuture;

public interface EmployeeCouchbaseService {

    Employee getEmployeeById(String employeeId);

    CompletableFuture<Employee> getEmployeeByIdAndName(String id, String name);

    Employee addEmployee(Employee employee);

}
