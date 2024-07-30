package dev.neuralkatana.qrk;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/greeting")
public class GreetingResource {
    GreetingConfig config;

    @Inject
    public GreetingResource(GreetingConfig config) {
        this.config = config;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return config.receipient;
    }
}
