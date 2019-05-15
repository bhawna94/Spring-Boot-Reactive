package edu.knoldus.employee.couchbase.repository.reactive;

import edu.knoldus.employee.couchbase.model.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeReactiveRepository extends ReactiveCrudRepository<Employee, String> {
}
