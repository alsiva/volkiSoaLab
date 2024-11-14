package volki.soalab.controllers;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/temp")
public class TempController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTemp() {
        return "Hello, i'm working my friend";
    }
}
