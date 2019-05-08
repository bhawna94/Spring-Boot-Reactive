package edu.knoldus.employee.couchbase.service;

import edu.knoldus.employee.couchbase.model.Employee;
import edu.knoldus.employee.couchbase.model.UserDetails;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public interface EmployeeCouchbaseService {

    Employee getEmployeeById(String employeeId);

    CompletableFuture<Employee> getEmployeeByIdAndName(String id, String name);

    Employee addEmployee(Employee employee);

    /*Mono is a publisher that emits zero or single value and Flux is a publisher that emits zero to n values.
     Mono<UserDetails> means that it emits a zero or single UserDetails.
     Mono and Flux are quite similar to Observable in RxJava.
     */
    Mono<UserDetails> callToExternalservice();

}
