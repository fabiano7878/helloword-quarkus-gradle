package br.com.comida.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/helloword")
public class HelloWordQuarkusResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("hello Fabiano, follow response ");
        return "from Quarkus REST";
    }
}
