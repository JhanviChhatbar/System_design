package Design_patterns.builder;

 // Concrete Builder - Igloo
public class IglooHouseBuilder implements HouseBuilder {
    private House house = new House();

    public void buildFoundation() {
        house.setFoundation("Ice blocks foundation");
    }

    public void buildStructure() {
        house.setStructure("Ice dome structure");
    }

    public void buildRoof() {
        house.setRoof("Ice dome roof");
    }

    public House getHouse() {
        return house;
    }
}

