package Low_level_design.Elevator_System;

public class DisplayPanel {
    public void showStatus(int floor, Direction direction){
        System.out.println("Floor: " + floor + " | Direction: " + direction);
    }
}
