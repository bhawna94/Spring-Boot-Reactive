package edu.knoldus.employee.couchbase.facade;

import edu.knoldus.employee.couchbase.model.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ExternalserviceFacade {

    public Mono<UserDetails> callToExternalservice() {

        return WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build()
                .get()
                .uri("posts/1")
                .retrieve()
                .bodyToMono(UserDetails.class);
    }
}
