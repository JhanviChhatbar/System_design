package Design_patterns.builder;

// Product
public class House {
    private String foundation;
    private String structure;
    private String roof;

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    @Override
    public String toString() {
        return "House [Foundation: " + foundation + ", Structure: " + structure + ", Roof: " + roof + "]";
    }
}

