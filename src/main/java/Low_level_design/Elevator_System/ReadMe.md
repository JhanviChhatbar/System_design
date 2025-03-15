**Problem Statement: Design an Elevator System**

**Objective:**
Design a scalable and efficient elevator system that can handle multiple elevators in a building with various floors while optimizing passenger wait and travel times.

**Requirements:**
1. Functional Requirements:
✅ Multiple Elevators: The system should support multiple elevators operating simultaneously.
✅ Handling Requests:

Passengers should be able to request an elevator from a floor (Up/Down request).
Passengers inside the elevator should be able to choose their destination floor.
✅ Efficient Scheduling: The system should decide which elevator should handle a request efficiently (e.g., nearest available elevator).
✅ Elevator Status: Each elevator should have the following states:
Moving Up
Moving Down
Idle
Door Opening/Closing
✅ Handling Edge Cases:
Overloaded elevators should stop accepting new passengers.
Emergency stop mechanism for safety.
Power failure handling.
2. Non-Functional Requirements:

⚡ Scalability: Should handle large buildings with multiple floors and many elevators.

⚡ Concurrency: Multiple users can interact with the system simultaneously.

⚡ Low Latency: Quick response to floor requests.

⚡ Fault Tolerance: Should recover from failures and resume operation smoothly.


**Logic**

🔹 Elevator Enhancements:

Used a PriorityQueue to sort requests dynamically based on the elevator’s current direction.
Ensures requests are processed efficiently (ascending for UP, descending for DOWN).

🔹 Scheduler Improvements:

Chooses the nearest available elevator that is either idle or moving in the request's direction.
Ensures elevators avoid unnecessary direction changes, improving efficiency.

🔹 processRequests() Logic:

Continuously polls requests from the queue until all are processed.
Opens and closes doors efficiently to simulate real-world behavior.

Design Patterns Used in the Elevator System
This implementation incorporates several key design patterns:

1. Singleton Pattern
Where Used: RequestManager and Scheduler can be designed as Singletons to ensure only one instance is created and shared across the system.
Purpose: Ensures controlled access to shared resources like elevator assignment logic.
2. Strategy Pattern
Where Used: The Scheduler class employs the Strategy Pattern to implement various scheduling algorithms like LOOK, SCAN, or FCFS.
Purpose: This pattern allows you to switch between different scheduling strategies without modifying the core logic.
✅ Improvement Idea: Refactor Scheduler with an interface like SchedulingStrategy to make it more flexible.

java
Copy
Edit
interface SchedulingStrategy {
    Elevator findBestElevator(List<Elevator> elevators, int floor, Direction direction);
}
3. Command Pattern
Where Used: The addRequest() method in the Elevator class acts as a Command, queueing floor requests for later execution.
Purpose: It separates the request from the execution logic, improving flexibility and scalability.
4. Observer Pattern
Where Used: The DisplayPanel can be improved to implement the Observer Pattern, where it subscribes to elevator state changes and updates the display accordingly.
Purpose: Keeps the UI updated without tightly coupling it to the elevator logic.
5. Factory Pattern (Potential Enhancement)
Where Used (Improvement Idea): The creation of elevators inside ElevatorSystem can follow the Factory Pattern for better scalability when adding different elevator types (e.g., Freight Elevator, Passenger Elevator, etc.).
Summary of Design Patterns
✅ Singleton → For RequestManager and Scheduler instances
✅ Strategy → For flexible scheduling algorithms
✅ Command → For request queuing and execution logic
✅ Observer → For real-time UI updates via DisplayPanel
✅ Factory (Potential Improvement) → For scalable elevator creation