import java.io.Serializable;
import java.util.Set;

// generic Fastener, top of hierarchy
// only contains attributes/methods needed for all fasteners
public abstract class Fastener implements Serializable {
    private static final long serialVersionUID = -2296809657609875759L;
    private String material;
    private String finish;
    private double unitPrice;
    private int numUnits;

    private static final Set<String> VALID_MATERIALS = Set.of("brass", "stainless steel", "steel");
    private static final Set<String> VALID_STEEL_FINISHES = Set.of("chrome", "hot dipped galvanized", "plain", "yellow zinc", "zinc");
    private static final int MAX_NUM = 10000;
    private static final int MIN_NUM = 1;

    public Fastener(String material, String finish, double unitPrice, int numUnits) throws IllegalFastener {
        if(!isMaterialOK(material))
            throw new IllegalFastener("Illegal material value");
        this.material = material.toLowerCase(); // to avoid improper capitalization
        if(!isFinishOK(finish, this.material))
            throw new IllegalFastener("Illegal finish value");
        this.finish = finish.toLowerCase(); // to avoid improper capitalization
        if(!isPriceOK(numUnits))
            throw new IllegalFastener("Illegal price");
        this.unitPrice = unitPrice;
        if(!isNumOK(numUnits))
            throw new IllegalFastener("Illegal number of units");
        this.numUnits = numUnits;
    }

    // accessor to get cost of order
    public double getOrderCost(int num) {
        return this.unitPrice * num;
    }

    // this method will be overridden as new attributes are introduced
    @Override
    public String toString() {
        return material + ", with a " + finish + " finish. " + numUnits +
                " in a unit, " + "$" + unitPrice + " per unit.";
    }

    private boolean isMaterialOK(String material) {
        return VALID_MATERIALS.contains(material.toLowerCase());
    }

    // this method will get overridden depending on valid finishes
    public boolean isFinishOK(String finish, String material) {
        if (material.equals("steel"))
            return VALID_STEEL_FINISHES.contains(finish.toLowerCase()); // check if in set of valid finishes
        return finish.equalsIgnoreCase("plain");
    }

    // these methods are private since they are never overridden
    private boolean isNumOK(int numUnits) {
        return numUnits <= MAX_NUM && numUnits >= MIN_NUM;
    }

    private boolean isPriceOK(double price) {
        return price > 0;
    }
}
