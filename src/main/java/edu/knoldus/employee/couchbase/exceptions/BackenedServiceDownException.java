package edu.knoldus.employee.couchbase.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, reason = "DON'T Know>>>>>>>>>>>>")
public class BackenedServiceDownException extends RuntimeException{


    public BackenedServiceDownException() {
    }

    public BackenedServiceDownException(String message) {
        super(message);
    }
}
