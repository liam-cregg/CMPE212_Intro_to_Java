// currently this class doesn't do anything except for extend OuterThreaded
// but this can be used as a parent class for other bolts
public abstract class Bolt extends OuterThreaded {

    private static final long serialVersionUID = -1390865116604392842L;

    public Bolt(String material, String finish, double unitPrice, int numUnits,
                String thread, double length) throws IllegalFastener {
        super(material, finish, unitPrice, numUnits, thread, length);
    }
}
