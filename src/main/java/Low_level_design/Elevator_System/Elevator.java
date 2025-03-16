package Low_level_design.Elevator_System;

import java.util.PriorityQueue;

public class Elevator {
    private int currentFloor;
    private Direction direction;
    private PriorityQueue<Integer> requests;
    private ElevatorState elevatorState;

    /*
    A PriorityQueue in Java automatically maintains its elements in sorted order based on the provided comparator logic.
    This logic dynamically decides the sorting order based on the elevator's direction:

   If the elevator is moving UP → Requests are sorted in ascending order (lowest to highest floor).
   If the elevator is moving DOWN → Requests are sorted in descending order (highest to lowest floor).

     */
    public Elevator(){
        this.requests = new PriorityQueue<>((a,b) -> direction == Direction.UP ? a-b : b-a);
    }

    public void addRequest(int floor){
        requests.add(floor);
        if(elevatorState == ElevatorState.IDLE){
            processRequests();
        }
    }

    public void processRequests(){
        while (!requests.isEmpty()){
            int nextFloor = requests.poll();
            moveToFloor(nextFloor);
        }
        elevatorState = ElevatorState.IDLE;
    }

    public void moveToFloor(int floor){
        System.out.println("Moving to floor " + floor);
        this.currentFloor = floor;
        elevatorState = ElevatorState.DOOR_OPEN;
        System.out.println("Reached floor " + floor);
        elevatorState = ElevatorState.DOOR_CLOSED;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public PriorityQueue<Integer> getRequests() {
        return requests;
    }

    public void setRequests(PriorityQueue<Integer> requests) {
        this.requests = requests;
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }
}
