package com.example.secondservice;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class ProcessingExceptionMapper implements ExceptionMapper<ProcessingException> {
    @Override
    public Response toResponse(ProcessingException exception) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", "Service Unavailable");
        errorDetails.put("message", "Unable to connect to the external service. Please try again later.");

        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(errorDetails)
                .build();
    }
}
