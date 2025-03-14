package Low_level_design.Restaurant_Seating_Arrangement;

import java.util.ArrayList;

public class RestaurantSimulator {
    public static void main(String[] args) {
        // Example of initializing tables
        ArrayList<Table> tableList = new ArrayList<>();
        tableList.add(new Table(1, 2));
        tableList.add(new Table(2, 3));
        tableList.add(new Table(3, 4));
        tableList.add(new Table(4, 5));
        tableList.add(new Table(5, 6));

        RestManager manager = new RestManager(tableList);

        // Test the system
        manager.arrive(1, 2);  // Group 1 with 2 people arrives
        manager.arrive(2, 4);  // Group 2 with 4 people arrives
        manager.arrive(3, 6);  // Group 3 with 6 people arrives
        manager.leave(2);      // Group 2 leaves the queue
        manager.tableAvailable(1);  // Table 1 becomes available
    }
}
