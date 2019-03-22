// currently this class doesn't do anything except for extend Threaded
// but is used as a parent class of additional nuts, etc.
public abstract class InnerThreaded extends Threaded {

    private static final long serialVersionUID = 1834988163880204328L;

    public InnerThreaded(String material, String finish, double unitPrice,
                         int numUnits, String thread) throws IllegalFastener {
        super(material, finish, unitPrice, numUnits, thread);
    }
}
