package edu.knoldus.employee.couchbase.repository;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.AsyncN1qlQueryRow;
import com.couchbase.client.java.query.N1qlQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.knoldus.employee.couchbase.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class CustomisedEmployeeRepositoryImpl  implements CustomisedEmployeeRepository{

    @Autowired
    private Bucket bucket;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public CompletableFuture<Employee> getByIdAndName(String id, String name) {

        String query = "select empId, empName from Bucket1 where empId=$empId and empName=$empName";
        JsonObject placeholder = JsonObject.create()
                .put("empId", id)
                .put("empName", name);

        N1qlQuery n1qlQuery = N1qlQuery.parameterized(query, placeholder);

       Observable<JsonObject> row =  bucket.async()
                .query(n1qlQuery)
                .flatMap(queryResult -> queryResult.errors()
                        .flatMap(error ->

                                Observable
                                        .<JsonObject>error(new CouchbaseException(error.toString()))
                        )
                        .switchIfEmpty(queryResult.rows().map(AsyncN1qlQueryRow::value))
                ).singleOrDefault(JsonObject.empty());

        return toCompletableFuture(row).thenApply(this::mapToEmployee);
    }

    private CompletableFuture<JsonObject> toCompletableFuture(Observable<JsonObject> observable) {
        CompletableFuture<JsonObject> future = new CompletableFuture<>();
        observable.single().subscribe(future::complete, future::completeExceptionally);
        return future;
    }

    private Employee mapToEmployee(JsonObject jsonObject) {

        Employee emp = Employee.builder().build();

        try {
            emp = mapper.readValue(jsonObject.toString(), Employee.class);
        } catch (IOException ex) {
            ex.getMessage();
        }

        return emp;
    }
}
