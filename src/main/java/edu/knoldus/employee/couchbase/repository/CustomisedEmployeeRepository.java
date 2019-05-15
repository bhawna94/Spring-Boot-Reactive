package edu.knoldus.employee.couchbase.repository;

import edu.knoldus.employee.couchbase.model.Employee;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomisedEmployeeRepository {

    CompletableFuture<Employee> getByIdAndName(String id, String name);

    Mono<List<Employee>> getEmployeeUsingCustomquery(String name);
}
