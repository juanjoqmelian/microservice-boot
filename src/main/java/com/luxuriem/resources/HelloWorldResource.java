package com.luxuriem.resources;

import com.luxuriem.components.MyComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/hello")
public class HelloWorldResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    @Inject
    private MyComponent component;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        LOGGER.info("Accessing hello world resource on '{}'", component.getName());
        return Response.ok(String.format("hello! Welcome to '%s' environment!", component.getName())).build();
    }
}
