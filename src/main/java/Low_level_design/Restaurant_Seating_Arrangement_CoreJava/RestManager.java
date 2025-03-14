package Low_level_design.Restaurant_Seating_Arrangement_CoreJava;

import java.util.*;

public class RestManager {
    private List<Table> tables;
    private Queue<ClientsGroup> queue;
    private Map<ClientsGroup, Table> seatingMap; // Track which table a group is seated at

    public RestManager(List<Table> tables) {
        this.tables = new ArrayList<>(tables);
        this.queue = new LinkedList<>();
        this.seatingMap = new HashMap<>();
        Collections.sort(tables);
    }

    public void onArrive(ClientsGroup group) {
        Table table = findTableForGroup(group);
        if (table != null) {
            //table.seatGroup(group);
            seatingMap.put(group, table);
            checkQueue(); // Attempt to seat any waiting groups
        } else {
            queue.add(group);
        }
    }

    public void onLeave(ClientsGroup group) {
        Table table = seatingMap.remove(group);
        if (table != null) {
            table.clear(); // Make the table available again
            checkQueue(); // Attempt to seat any waiting groups
        } else {
            queue.remove(group); // If the group is not seated, it must be in the queue
        }
    }

    public Table lookup(ClientsGroup group) {
        return seatingMap.get(group);
    }

    private Table findTableForGroup(ClientsGroup group) {
        // Try to find an empty table that exactly matches the group size
        for (Table table : tables) {
            if (table.isEmpty() && table.size == group.size) {
                return table;
            }
        }
        // If no exact match, find a table with enough space for the group
        for (Table table : tables) {
            if (!table.isFull() && table.canAccommodate(group)) {
                return table;
            }
        }
        return null; // No suitable table found
    }

    private void checkQueue() {
        List<ClientsGroup> queueList = new ArrayList<>(queue);
        // Sort the list by group size (ascending order)
        queueList.sort(Comparator.comparingInt(g -> g.size));

        Iterator<ClientsGroup> iterator = queueList.iterator();
        while (iterator.hasNext()) {
            ClientsGroup group = iterator.next();
            Table table = findTableForGroup(group);
            if (table != null) {
                //table.seatGroup(group);
                seatingMap.put(group, table);
                iterator.remove(); // Remove the group from the queue
            }
        }
    }
}
