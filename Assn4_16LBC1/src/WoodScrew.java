import java.util.Set;
// concrete class extending Screw
// adds point attribute
public class WoodScrew extends Screw {
    private static final long serialVersionUID = 1960856774052365873L;
    private String point;
    private Set<String> validPoints = Set.of("double cut", "sharp", "type 17");

    public WoodScrew(double length, String thread, String material,String finish, String head,
                     String drive, String point, double unitPrice, int numUnits) throws IllegalFastener {
        super(material, finish, unitPrice, numUnits, thread, length, head, drive);
        if (!isPointOK(point))
            throw new IllegalFastener("Illegal point value");
        this.point = point.toLowerCase();
    }

    private boolean isPointOK(String point) {
        return validPoints.contains(point.toLowerCase());
    }

    // adds point and specific fastener name to toString
    @Override
    public String toString() {
        return "Wood Screw, " + point + " point, " + super.toString();
    }
}
