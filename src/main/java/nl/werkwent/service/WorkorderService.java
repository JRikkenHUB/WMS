package nl.werkwent.service;

import nl.werkwent.persistence.IWorkorderDOA;
import nl.werkwent.service.dto.WorkorderDTO;

import javax.inject.Inject;

public class WorkorderService implements IWorkorderService {
    @Inject
    IWorkorderDOA workorderDOA;

    @Override
    public void CreateNewWorkorder(WorkorderDTO workorderDTO){
        workorderDOA.CreateNewWorkorder(workorderDTO);
    }

    @Override
    public WorkorderDTO getWorkorder(String id) {
        return workorderDOA.getWorkorder(id);
    }
}
