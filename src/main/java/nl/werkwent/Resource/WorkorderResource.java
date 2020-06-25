package nl.werkwent.Resource;

import nl.werkwent.DTO.WorkorderDTO;
import nl.werkwent.Service.IWorkorderService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
}
