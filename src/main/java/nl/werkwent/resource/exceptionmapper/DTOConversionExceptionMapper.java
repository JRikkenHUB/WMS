package nl.werkwent.resource.exceptionmapper;

import com.mysql.cj.protocol.Resultset;
import nl.werkwent.service.exceptions.DTOConversionException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DTOConversionExceptionMapper implements ExceptionMapper<DTOConversionException> {
    @Override
    public Response toResponse(DTOConversionException e) {
        return Response.status(403).build();
    }
}
