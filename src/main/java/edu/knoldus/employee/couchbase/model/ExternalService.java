package edu.knoldus.employee.couchbase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
public class ExternalService {

    String type;

    Value value;
}
