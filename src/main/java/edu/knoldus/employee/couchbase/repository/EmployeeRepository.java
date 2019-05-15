package edu.knoldus.employee.couchbase.repository;

import edu.knoldus.employee.couchbase.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>, CustomisedEmployeeRepository {

}
