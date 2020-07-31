package nl.werkwent.resource;

import nl.werkwent.service.dto.WorkorderDTO;
import nl.werkwent.service.IWorkorderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/workorder")
public class WorkorderResource {
    @Inject
    IWorkorderService workorderService;

    @Path("/create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response CreateNewWorkOrder(WorkorderDTO workorderDTO){
        workorderService.CreateNewWorkorder(workorderDTO);
        return Response.status(Response.Status.OK).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces
    public Response getWorkorder(@PathParam("id") String id){
        return Response.ok().entity(workorderService.getWorkorder(id)).build();
    }

    @Path("/get/{id}")
    @PUT
    @Produces
    public Response printWorkorder(@PathParam("id") String id){
        workorderService.printWorkorder(id);
        return Response.ok().build();
    }
}
