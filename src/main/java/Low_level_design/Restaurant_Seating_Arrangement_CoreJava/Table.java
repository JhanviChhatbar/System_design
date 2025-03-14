package Low_level_design.Restaurant_Seating_Arrangement_CoreJava;

public class Table implements Comparable<Table>{
    public final int size; // number of chairs
    private int availableSeats;

    public Table(int size) {
        this.size = size;
        this.availableSeats = size; // Table is initially empty
    }

    public boolean canAccommodate(ClientsGroup group) {
        return group.size <= availableSeats;
    }

    public boolean isEmpty() {
        return availableSeats == size; // Table is empty if all seats are available
    }

    public boolean isFull() {
        return availableSeats == 0; // Table is full if no seats are available
    }

//    public boolean seatGroup(ClientsGroup group) {
//        if (canAccommodate(group) && isEmpty()) {
//            availableSeats -= group.size; // Reduce the available seats
//            return true;
//        }
//        return false;
//    }

    public void clear() {
        // Restore the available seats to the table size
        availableSeats = size;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public int compareTo(Table other) {
        return Integer.compare(this.size, other.size); // Sort by table size in ascending order
    }
}
