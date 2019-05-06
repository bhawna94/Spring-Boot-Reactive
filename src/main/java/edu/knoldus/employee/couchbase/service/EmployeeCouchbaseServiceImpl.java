package edu.knoldus.employee.couchbase.service;

import edu.knoldus.employee.couchbase.exceptions.EmployeeNotFoundById;
import edu.knoldus.employee.couchbase.model.Employee;
import edu.knoldus.employee.couchbase.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

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
    public Employee getEmployeeByIdAndName(String id, String name) {
        return null;
    }
}
