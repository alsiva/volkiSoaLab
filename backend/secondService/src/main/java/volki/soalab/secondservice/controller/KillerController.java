package volki.soalab.secondservice.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import volki.soalab.secondservice.DragonDto.DragonDto;
import volki.soalab.secondservice.DungeonDto.DungeonDto;
import volki.soalab.secondservice.DungeonDto.DungeonDtoList;


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

        // Создание клиента в блоке try-with-resources для автоматического закрытия
        try (Client client = ClientBuilder.newBuilder()
                .property("jersey.config.client.connectTimeout", 5000)  // Таймаут на подключение
                .property("jersey.config.client.readTimeout", 10000)   // Таймаут на чтение
                .build()) {

            WebTarget target = client.target("http://localhost:8080/firstService/dungeons");

            // Отправка первого запроса
            Response response = target.request(MediaType.APPLICATION_XML).get();

            if (response.getStatus() == 200) {
                DungeonDtoList responseBody = response.readEntity(DungeonDtoList.class);
                if (responseBody.getDungeonDtoList().isEmpty()) {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }
                DungeonDto toFind = responseBody.toFind(max);
                if (toFind == null) {
                    return Response.status(Response.Status.NO_CONTENT).build();
                }

                // Новый запрос для поиска дракона (например, с использованием найденной пещеры)
                WebTarget dragonTarget = client.target("http://localhost:8080/firstService/dragons/" + toFind.getDragonId());
                Response dragonResponse = dragonTarget.request(MediaType.APPLICATION_XML).get();

                if (dragonResponse.getStatus() == 200) {
                    // Обработка ответа от сервера дракона
                    DragonDto dragonDto = dragonResponse.readEntity(DragonDto.class);
                    return Response.ok(dragonDto).build();
                } else {
                    return Response.status(dragonResponse.getStatus())
                            .entity("Error fetching dragon details: " + dragonResponse.getStatus())
                            .build();
                }

            } else {
                System.out.println("Error: Response code " + response.getStatus());

                // Получаем тело ответа, если оно есть (например, текст ошибки)
                String errorMessage = response.readEntity(String.class);
                System.out.println("Error message: " + errorMessage);

                // Возвращаем ответ с ошибкой и подробной информацией
                return Response.status(response.getStatus())
                        .entity("Error: " + response.getStatus() + ", Message: " + errorMessage)
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error during request processing: " + e.getMessage())
                    .build();
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
