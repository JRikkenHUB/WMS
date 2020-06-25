package nl.werkwent.Service;

import nl.werkwent.DTO.WorkorderDTO;
import nl.werkwent.Persistence.IWorkorderDOA;

import javax.inject.Inject;

public class WorkorderService implements IWorkorderService {
    @Inject
    IWorkorderDOA workorderDOA;

    @Override
    public void CreateNewWorkorder(WorkorderDTO workorderDTO){
        workorderDOA.CreateNewWorkorder(workorderDTO);
    }
}
