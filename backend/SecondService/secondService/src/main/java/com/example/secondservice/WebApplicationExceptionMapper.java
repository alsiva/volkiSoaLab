package com.example.secondservice;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException exception) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", "External Service Error");
        errorDetails.put("message", exception.getMessage());
        errorDetails.put("status", exception.getResponse().getStatus());

        return Response.status(exception.getResponse().getStatus())
                .entity(errorDetails)
                .build();
    }
}
