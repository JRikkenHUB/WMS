package nl.werkwent.service;

import nl.werkwent.service.dto.WorkorderDTO;

public interface IWorkorderService {
    void CreateNewWorkorder(WorkorderDTO workorderDTO);

    WorkorderDTO getWorkorder(String id);

    void printWorkorder(String id);
}
