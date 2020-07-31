package nl.werkwent.service;

import nl.werkwent.persistence.IWorkorderDOA;
import nl.werkwent.service.dto.WorkorderDTO;

import javax.inject.Inject;

public class WorkorderService implements IWorkorderService {
    @Inject
    IWorkorderDOA workorderDOA;

    public enum WorkorderState {
        OPEN(1),
        IN_PROGRESS(2),
        CANCELED(3),
        CLOSED(4);

        private final int state;

        WorkorderState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }

    @Override
    public void CreateNewWorkorder(WorkorderDTO workorderDTO){
        workorderDOA.CreateNewWorkorder(workorderDTO);
    }

    @Override
    public WorkorderDTO getWorkorder(String id) {
        return workorderDOA.getWorkorder(id);
    }

    @Override
    public void printWorkorder(String id) {
        var printOrder = workorderDOA.getWorkorder(id);
        WorkorderState workorderState = WorkorderState.IN_PROGRESS;
        workorderDOA.updateWorkorderState(id, workorderState.getState());
    }
}
