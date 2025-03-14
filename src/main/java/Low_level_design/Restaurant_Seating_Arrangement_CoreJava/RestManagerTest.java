package Low_level_design.Restaurant_Seating_Arrangement_CoreJava;

import java.util.*;

public class RestManagerTest {
    public static void main(String[] args) {
        // Create tables
        List<Table> tables = Arrays.asList(
                new Table(2),
                new Table(4),
                new Table(6),
                new Table(5),
                new Table(6),
                new Table(5),
                new Table(3)

        );

        // Create the RestManager with these tables
        RestManager manager = new RestManager(tables);

        // Test: Add and seat clients
        ClientsGroup group1 = new ClientsGroup(2); // Should fit at the 2-seat table
        ClientsGroup group2 = new ClientsGroup(9); // Should fit at the 4-seat table
        ClientsGroup group3 = new ClientsGroup(9); // Should queue, no 3-seat table

        manager.onArrive(group1);
        manager.onArrive(group2);
        manager.onArrive(group3);

        // Verify seating
        assert manager.lookup(group1) != null : "Group1 should be seated";
        assert manager.lookup(group2) != null : "Group2 should be seated";
        assert manager.lookup(group3) == null : "Group3 should be in the queue";

        // Test: Remove a group and check if queue is processed
        manager.onLeave(group1);
        // Group3 should now be seated at the 4-seat table if properly implemented
        assert manager.lookup(group3) != null : "Group3 should now be seated";

        // Test: Remove group2 and check if the table is cleared
        manager.onLeave(group2);
        assert manager.lookup(group2) == null : "Group2 should no longer be seated";

        // Add more clients to see if seating logic works correctly after clearing
        ClientsGroup group4 = new ClientsGroup(2); // Should fit at the now available 2-seat table
        manager.onArrive(group4);
        assert manager.lookup(group4) != null : "Group4 should be seated";

        System.out.println("All tests passed.");
    }
}

