package Design_patterns.builder;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        // Build an Igloo
        HouseBuilder iglooBuilder = new IglooHouseBuilder();
        CivilEngineer engineer = new CivilEngineer(iglooBuilder);
        engineer.constructHouse();
        House igloo = engineer.getHouse();
        System.out.println("Igloo: " + igloo);

        // Build a Wooden House
        HouseBuilder woodenBuilder = new WoodenHouseBuilder();
        engineer = new CivilEngineer(woodenBuilder);
        engineer.constructHouse();
        House woodenHouse = engineer.getHouse();
        System.out.println("Wooden House: " + woodenHouse);
    }
}

