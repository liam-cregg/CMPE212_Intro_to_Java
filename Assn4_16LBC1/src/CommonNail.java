// concrete class that extends Nail
public class CommonNail extends Nail {

    private static final long serialVersionUID = 6614268071942600259L;

    public CommonNail(String size, double length, double gauge, String finish, double unitPrice, int numUnits) throws IllegalFastener {
        super(finish, unitPrice, numUnits, size, length, gauge);
    }

    // adds specific fastener name
    @Override
    public String toString() {
        return "Common Nail, " + super.toString();
    }
}
