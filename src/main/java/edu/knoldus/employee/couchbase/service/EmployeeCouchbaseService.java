package edu.knoldus.employee.couchbase.service;

import edu.knoldus.employee.couchbase.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;

public interface EmployeeCouchbaseService {

    Employee getEmployeeById(String employeeId);

    Employee getEmployeeByIdAndName(String id, String name);
}
