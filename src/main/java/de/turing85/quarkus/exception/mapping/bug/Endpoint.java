package de.turing85.quarkus.exception.mapping.bug;

import java.util.NoSuchElementException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@Path("hello")
public class Endpoint {
  @GET
  public Uni<Response> hello() {
    return Uni.createFrom().item(Response.ok(Message.of("hello")).build());
  }

  @GET
  @Path("not-found")
  public Uni<Response> notFound() throws NoSuchElementException {
    throw new NoSuchElementException("Not found");
  }

  @ServerExceptionMapper
  @SuppressWarnings("unused")
  public Uni<Response> toResponse(NoSuchElementException e) {
    Log.info("not found", e);
    return Uni.createFrom().item(
        Response.status(Response.Status.NOT_FOUND).entity(Message.of(e.getMessage())).build());
  }
}
