package edu.knoldus.employee.couchbase;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.knoldus.employee.couchbase.facade.ExternalserviceFacade;
import edu.knoldus.employee.couchbase.model.UserDetails;
import reactor.core.publisher.Mono;

import java.io.IOException;

public class MockExternalService extends ExternalserviceFacade {


    private static final ObjectMapper MAPPER = new ObjectMapper();
    private String data = "{\n" +
            "  \"userId\": 1,\n" +
            "  \"id\": 1,\n" +
            "  \"title\": \"ABC\",\n" +
            "  \"body\": \"DEF\"\n" +
            "}";

    @Override
    public Mono<UserDetails> callToExternalservice() {

        UserDetails information = null;
        try {
            information = MAPPER.readValue(data, UserDetails.class);

        } catch (IOException ex) {
            System.out.println("Its exception :)\n" + ex.getMessage());

        }
        return Mono.just(information);
    }
}
