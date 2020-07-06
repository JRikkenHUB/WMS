package nl.werkwent.resource.exceptionmapper;

import nl.werkwent.service.exceptions.databaseRequestFailedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class databaseRequestFailedExceptionMapper implements ExceptionMapper<databaseRequestFailedException> {
    @Override
    public Response toResponse(databaseRequestFailedException e) {
        return Response.status(403).build();
    }
}
