package com.movie.client.ticketapp.movies;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "movie_provider", hostInterface = "localhost", port = "8080")
public class PactMovieConsumerTest {
    @Pact(provider = "movie_provider", consumer = "ticket_consumer")
    protected RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET all movies")
                .uponReceiving("GET REQUEST")
                .path("/movies")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("[{\"id\": 1, \"name\": inception, \"genre\": \"science-fiction\"}]")
                .toPact();
    }

    @Test
    public void testFetchMovies(MockServer mockServer) throws IOException {
        // when
        HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/movies").execute().returnResponse();

        // then
        assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));
    }

}
