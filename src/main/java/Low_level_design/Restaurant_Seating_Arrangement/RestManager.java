package Low_level_design.Restaurant_Seating_Arrangement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class RestManager {
    private ArrayList<Table>[] tables;
    private Queue<Group> queue;

    public RestManager(ArrayList<Table> tableList) {
        // Initialize tables array with 5 slots (2-6 seats)
        tables = new ArrayList[5];  // Index 0 corresponds to 2-seat tables, index 4 to 6-seat tables
        for (int i = 0; i < 5; i++) {
            tables[i] = new ArrayList<>();
        }

        // Add tables to the appropriate list based on their size
        for (Table table : tableList) {
            int index = table.size - 2;  // Map size 2 to index 0, size 3 to index 1, etc.
            tables[index].add(table);
        }

        queue = new LinkedList<>();
    }

    public void arrive(int groupId, int groupSize) {
        Group group = new Group(groupId, groupSize);

        // Check if there's an available table for this group
        for (int i = groupSize - 2; i < 5; i++) {  // From groupSize to 6-seat tables
            for (Table table : tables[i]) {
                if (table.isEmpty()) {  // Prefer empty tables
                    table.seatGroup(groupSize);
                    System.out.println("Group " + groupId + " seated at table " + table.tableId);
                    return;
                }
            }
        }

        // If no suitable table, add group to the waiting queue
        queue.add(group);
        System.out.println("Group " + groupId + " is waiting in the queue.");
    }

    public void leave(int groupId) {
        // Search for the group in the queue
        for (Group group : queue) {
            if (group.groupId == groupId) {
                queue.remove(group);
                System.out.println("Group " + groupId + " has left the queue.");
                return;
            }
        }
        System.out.println("Group " + groupId + " not found in the queue.");
    }

    public void tableAvailable(int tableId) {
        // Find the table by tableId and mark it as available (empty)
        for (int i = 0; i < 5; i++) {
            for (Table table : tables[i]) {
                if (table.tableId == tableId) {
                    table.vacate();
                    System.out.println("Table " + tableId + " is now available.");

                    // Try to seat waiting groups
                    seatWaitingGroups();
                    return;
                }
            }
        }
        System.out.println("Table " + tableId + " not found.");
    }

    private void seatWaitingGroups() {
        // Iterate over the queue to seat waiting groups
        for (Group group : new LinkedList<>(queue)) {  // Iterate over a copy to avoid modification errors
            for (int i = group.size - 2; i < 5; i++) {  // Look for suitable tables
                for (Table table : tables[i]) {
                    if (table.isEmpty()) {
                        table.seatGroup(group.size);
                        queue.remove(group);
                        System.out.println("Group " + group.groupId + " seated at table " + table.tableId);
                        return;
                    }
                }
            }
        }
        System.out.println("No group was seated, no suitable table available.");
    }
}



