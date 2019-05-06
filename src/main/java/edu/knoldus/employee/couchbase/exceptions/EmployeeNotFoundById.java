package edu.knoldus.employee.couchbase.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundById extends RuntimeException {


    public EmployeeNotFoundById(String message) {

        super(message);
    }
}
