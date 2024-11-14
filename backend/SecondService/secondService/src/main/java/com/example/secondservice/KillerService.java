package com.example.secondservice;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class KillerService {
    private static final String EXTERNAL_SERVICE_URL = "http://localhost:8080/dragons/1";

    public Response findDragonByComparasion(String comparasion) {

        return Response.ok().build();

//        Client client = ClientBuilder.newClient();
//        WebTarget webTarget = client.target(EXTERNAL_SERVICE_URL);
//
//        try {
//            Response response = webTarget.request(MediaType.APPLICATION_XML).get();
//
//            if (response.getStatus() == 200) {
//                DragonDto dragonDto = response.readEntity(DragonDto.class);
//                return Response.ok(dragonDto).build();
//            }
//            return Response.status(response.getStatus())
//                    .entity("Error from external service: " + response.getStatusInfo())
//                    .build();
//
//
//        } catch (ProcessingException e) {
//            return Response.status(Response.Status.SERVICE_UNAVAILABLE) // 503 Service Unavailable
//                    .entity("Unable to connect to the external service. Please try again later.")
//                    .build();
//        } catch (WebApplicationException e) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // 500 Internal Server Error
//                    .entity("An error occurred while calling the external service.")
//                    .build();
//        } finally {
//            client.close();
//        }
    }
}
