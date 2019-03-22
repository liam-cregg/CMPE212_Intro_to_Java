import java.util.Set;
// Nail class that extends Fastener, adds several different attributes and overrides several methods
public abstract class Nail extends Fastener {
    private static final long serialVersionUID = -5689156280089268096L;
    private String size;
    private double length;
    private double gauge;
    private static final Set<String> VALID_SIZES = Set.of("6d", "8d", "10d", "12d", "16d", "60d");
    private static final Set<String> VALID_NAIL_FINISHES = Set.of("bright", "hot dipped galvanized");
    private static final Set<Double> VALID_LENGTHS = Set.of(2D, 2.5, 3D, 3.25, 3.5, 6D); // D added to cast to double
    private static final Set<Double> VALID_GAUGES = Set.of(2D, 8D, 9D, 10.25, 11.5); // D added to cast to double



    public Nail(String finish, double unitPrice, int numUnits, String size,
                double length, double gauge) throws IllegalFastener {
        super("Steel", finish, unitPrice, numUnits); // material must be steel
        if(!isSizeOK(size))
            throw new IllegalFastener("Illegal size value");
        this.size = size;
        if(!isLengthOK(length))
            throw new IllegalFastener("Illegal length value");
        this.length = length;
        if(!isGaugeOK(gauge))
            throw new IllegalFastener("Illegal gauge value");
        this.gauge = gauge;
    }

    @Override
    public boolean isFinishOK(String finish, String material) {
        return VALID_NAIL_FINISHES.contains(finish.toLowerCase()); // material parameter not used as all nails are steel
    }

    public boolean isSizeOK(String size) {
        return VALID_SIZES.contains(size.toLowerCase());
    }

    public boolean isLengthOK(double length) {
        return VALID_LENGTHS.contains(length);
    }

    public boolean isGaugeOK(double gauge) {
        return VALID_GAUGES.contains(gauge);
    }

    // add attributes to toString
    @Override
    public String toString() {
        return size + " size, " + length + "\" long, " + gauge + " gauge, " + super.toString();
    }
}
