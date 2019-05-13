package edu.knoldus.employee.couchbase.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    int userId;

    int id;

    String title;

    String body;
}
