package de.turing85.quarkus.exception.mapping.bug;

import jakarta.ws.rs.core.Response;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;

@TestHTTPEndpoint(Endpoint.class)
@QuarkusTest
class EndpointTest {
  @Test
  void testOk() {
    // @formatter:off
    RestAssured
        .when().get()
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("message", is("hello"));
    // @formatter:on
  }

  @Test
  void testNotFound() {
    // @formatter:off
    RestAssured
        .when().get("not-found")
        .then()
            .statusCode(Response.Status.NOT_FOUND.getStatusCode())
            .body("message", is("Not found"));
    // @formatter:on
  }
}
