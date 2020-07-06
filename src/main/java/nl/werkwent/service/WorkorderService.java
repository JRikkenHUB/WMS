package nl.werkwent.service;

import nl.werkwent.service.dto.WorkorderDTO;
import nl.werkwent.persistence.IWorkorderDOA;

import javax.inject.Inject;

public class WorkorderService implements IWorkorderService {
    @Inject
    IWorkorderDOA workorderDOA;

    @Override
    public void CreateNewWorkorder(WorkorderDTO workorderDTO){
        workorderDOA.CreateNewWorkorder(workorderDTO);
    }
}
