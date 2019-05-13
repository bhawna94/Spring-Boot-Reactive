package edu.knoldus.employee.couchbase.service;

import edu.knoldus.employee.couchbase.exceptions.BackenedServiceDownException;
import edu.knoldus.employee.couchbase.exceptions.EmployeeNotFoundById;
import edu.knoldus.employee.couchbase.model.Employee;
import edu.knoldus.employee.couchbase.model.UserDetails;
import edu.knoldus.employee.couchbase.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeCouchbaseServiceImpl implements EmployeeCouchbaseService {

    private EmployeeRepository employeeRepository;

    public EmployeeCouchbaseServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {

        System.out.println("hey" +employeeRepository.findById(employeeId));
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
                .exceptionally( throwable -> {
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
    public Mono<UserDetails> callToExternalservice() {

     return WebClient
             .builder()
             .baseUrl("https://jsonplaceholder.typicode.com")
             .build()
             .get()
             .uri("posts/1")
             .retrieve()
             .bodyToMono(UserDetails.class)
             .onErrorMap(ex -> {
                 System.out.println(ex.getCause());
                  throw new BackenedServiceDownException("not Available");
             });

    }
}
