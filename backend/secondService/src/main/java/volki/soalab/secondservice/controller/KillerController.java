package volki.soalab.secondservice.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/killer")
@Produces(MediaType.APPLICATION_XML)
public class KillerController {
    @GET
    @Path("/dragon/find-by-cave-depth/{max-of-min}")
    public Response findByCaveDepth(@PathParam("max-of-min") String maxOrMin) {
        return Response.ok()
                .build();
    }

    @GET
    @Path("/team/{team-id}/move-to-cave/{cave-id}")
    public Response moveToCave(@PathParam("team-id") Long teamId,
                               @PathParam("cave-id") Long caveId) {
        return Response.ok()
                .build();
    }
}
