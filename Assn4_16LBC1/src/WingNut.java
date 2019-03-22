// concrete class extending InnerThreaded
public class WingNut extends InnerThreaded {

    private static final long serialVersionUID = -232124967685852935L;

    public WingNut(String thread, String material, String finish,
                   double unitPrice, int numUnits) throws IllegalFastener {
        super(material, finish, unitPrice, numUnits, thread);
    }

    // adds specific fastener name
    @Override
    public String toString() {
        return "Wing Nut, " + super.toString();
    }
}
