package Design_patterns.builder;

public class WoodenHouseBuilder implements HouseBuilder {
    private House house = new House();

    public void buildFoundation() {
        house.setFoundation("Concrete and wood poles");
    }

    public void buildStructure() {
        house.setStructure("Wooden walls");
    }

    public void buildRoof() {
        house.setRoof("Wooden roof with shingles");
    }

    public House getHouse() {
        return house;
    }
}