package Low_level_design.Elevator_System;

import java.util.List;

public class RequestManager {

    private List<Elevator> elevators;
    private Scheduler scheduler;

    public RequestManager(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public Elevator assignElevator(int floor, Direction direction){
        return scheduler.findBestElevator(elevators, floor, direction);
    }
}
