package nl.werkwent.persistence;

import nl.werkwent.service.WorkorderService;
import nl.werkwent.service.dto.WorkorderDTO;

public interface IWorkorderDOA {
    void CreateNewWorkorder(WorkorderDTO workorderDTO);

    WorkorderDTO getWorkorder(String id);

    void updateWorkorderState(String id, int state);
}
