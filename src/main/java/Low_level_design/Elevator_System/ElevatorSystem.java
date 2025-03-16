package Low_level_design.Elevator_System;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private List<Elevator> elevators = new ArrayList<>();
    private RequestManager requestManager = new RequestManager(elevators);

    public ElevatorSystem(int numElevators) {
        for (int i = 0; i < numElevators; i++) {
            elevators.add(new Elevator());
        }
    }

    public void requestElevator(int floor, Direction direction) {
        Elevator assignedElevator = requestManager.assignElevator(floor, direction);
        assignedElevator.addRequest(floor);
    }

    public void showElevatorStatus() {
        for (int i = 0; i < elevators.size(); i++) {
            System.out.println("Elevator " + (i + 1) + " is at floor " + elevators.get(i).getCurrentFloor());
        }
    }

    public static void main(String[] args) {
        ElevatorSystem system = new ElevatorSystem(3);

        system.requestElevator(3, Direction.UP);
        system.requestElevator(7, Direction.UP);
        system.requestElevator(2, Direction.DOWN);

        system.showElevatorStatus();
    }
}
