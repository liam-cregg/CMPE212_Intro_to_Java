// a concrete class that extends Bolt
public class CarriageBolt extends Bolt {

    private static final long serialVersionUID = 2800557674623857230L;

    public CarriageBolt(double length, String thread, String material,
                        String finish, double unitPrice, int numUnits) throws IllegalFastener {
        super(material, finish, unitPrice, numUnits, thread, length);
    }

    //adds specific fastener name
    @Override
    public String toString() {
        return "Carriage Bolt, " + super.toString();
    }
}
