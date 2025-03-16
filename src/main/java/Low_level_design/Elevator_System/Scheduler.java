package Low_level_design.Elevator_System;

import java.util.List;

public class Scheduler {
    public Elevator findBestElevator(List<Elevator> elevators, int floor, Direction direction) {
        Elevator bestElevator = null;
        //Tracks the smallest distance between the elevator's current position and the requested floor.
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            //If the elevator is IDLE, it is directly eligible.
            if (elevator.getElevatorState() == ElevatorState.IDLE ||
                    //If the elevator is already moving in the requested direction
                    (elevator.getDirection() == direction &&
                            //For UP requests ➔ Elevator must be below or at the requested floor.
                            ((direction == Direction.UP && elevator.getCurrentFloor() <= floor) ||
                                    //For DOWN requests ➔ Elevator must be above or at the requested floor.
                                    (direction == Direction.DOWN && elevator.getCurrentFloor() >= floor)))) {
                int distance = Math.abs(elevator.getCurrentFloor() - floor);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
        }
        return bestElevator != null ? bestElevator : elevators.get(0); // Default fallback
    }
}
