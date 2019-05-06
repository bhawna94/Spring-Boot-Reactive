package edu.knoldus.employee.couchbase.repository;

import edu.knoldus.employee.couchbase.model.Employee;

import java.util.concurrent.CompletableFuture;

public interface CustomisedEmployeeRepository {

    CompletableFuture<Employee> getByIdAndName(String id, String name);
}
