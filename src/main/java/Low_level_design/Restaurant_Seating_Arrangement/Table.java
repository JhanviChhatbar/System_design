package Low_level_design.Restaurant_Seating_Arrangement;

class Table {
    int tableId;
    int size;
    int currentOccupancy;

    public Table(int tableId, int size) {
        this.tableId = tableId;
        this.size = size;
        this.currentOccupancy = 0;
    }

    public boolean canSeatGroup(int groupSize) {
        return this.currentOccupancy + groupSize <= this.size;
    }

    public void seatGroup(int groupSize) {
        this.currentOccupancy += groupSize;
    }

    public boolean isEmpty() {
        return this.currentOccupancy == 0;
    }

    public void vacate() {
        this.currentOccupancy = 0;
    }
}
