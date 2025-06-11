package Design_patterns.builder;

public interface HouseBuilder {
    void buildFoundation();
    void buildStructure();
    void buildRoof();
    House getHouse();
}
