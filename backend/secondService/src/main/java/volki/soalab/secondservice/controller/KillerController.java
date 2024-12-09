package volki.soalab.secondservice.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import volki.soalab.secondservice.DungeonDto.DungeonDtoWithIdList;

import java.io.PrintWriter;
import java.io.StringWriter;

@Path("/killer")
public class KillerController {


    @GET
    @Path("/pelmeni")
    public Response positive() {
        return Response.ok().build();
    }


    @GET
    @Path("/dragon/find-by-cave-depth/{max-of-min}")
    public Response findByCaveDepth(@PathParam("max-of-min") String maxOrMin) {
        boolean max = maxOrMin.equalsIgnoreCase("max");

        Client client = ClientBuilder.newBuilder()
                .property("jersey.config.client.connectTimeout", 5000)  // Таймаут на подключение
                .property("jersey.config.client.readTimeout", 10000)   // Таймаут на чтение
                .build();

        WebTarget target = client.target("http://localhost:8080/firstService/dungeons");

        try {
            Response response = target.request(MediaType.APPLICATION_XML).get();

            if (response.getStatus() == 200) {
                DungeonDtoWithIdList responseBody = response.readEntity(DungeonDtoWithIdList.class);
                return Response.ok(responseBody).build();
            } else {
                System.out.println("Error: Response code " + response.getStatus());
                return Response.status(response.getStatus()).entity("Error: " + response.getStatus()).build();
            }
        } catch (ProcessingException e) {
            e.printStackTrace(); // Вывод стека в консоль
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            // Включаем стек исключений в тело ответа
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error occurred: " + e.getMessage() + "\n" + sw.toString())
                    .build();
        } finally {
            client.close(); // Закрываем клиент
        }

    }

    @GET
    public Response yes() {
        return Response.ok().build();
    }

    @GET
    @Path("/team/{team-id}/move-to-cave/{cave-id}")
    public Response moveToCave(@PathParam("team-id") Long teamId,
                               @PathParam("cave-id") Long caveId) {
        return Response.ok()
                .build();
    }
}
