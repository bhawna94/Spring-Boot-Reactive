package edu.knoldus.employee.couchbase.service;

import edu.knoldus.employee.couchbase.exceptions.EmployeeNotFoundById;
import edu.knoldus.employee.couchbase.model.Employee;
import edu.knoldus.employee.couchbase.repository.EmployeeRepository;
import edu.knoldus.employee.couchbase.repository.reactive.EmployeeReactiveRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeCouchbaseServiceImpl implements EmployeeCouchbaseService {

    private EmployeeRepository employeeRepository;

    private EmployeeReactiveRepository employeeReactiveRepository;

    public EmployeeCouchbaseServiceImpl(EmployeeRepository employeeRepository,
                                        EmployeeReactiveRepository employeeReactiveRepository) {
        this.employeeReactiveRepository = employeeReactiveRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {

        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundById("Employee with id " + employeeId + " not found")
                );
    }

    @Override
    public CompletableFuture<Employee> getEmployeeByIdAndName(String id, String name) {
        return employeeRepository.getByIdAndName(id, name)
                .thenApply(response -> {
                            System.out.println("Response" + response);
                            return response;
                        }
                )
                .exceptionally(throwable -> {
                            Throwable cause = throwable.getCause();
                            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, cause.getMessage());
                        }

                );
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);

    }

    @Override
    public Mono<Employee> getEmployee(String id) {

        return employeeReactiveRepository.findById(id)
                .switchIfEmpty(Mono.just(Employee.builder().build()))
                .onErrorMap(throwable -> {
                    throw new RuntimeException(throwable.getCause());
                });
    }

    @Override
    public Mono<List<Employee>> getEmployeeUsingCustomQuery(String name) {

        return employeeRepository.getEmployeeUsingCustomquery(name)
                .switchIfEmpty(Mono.just(Collections.singletonList(Employee.builder().build())))
                .onErrorMap(throwable -> {
                    throw new RuntimeException(throwable.getCause());
                });
    }
}
