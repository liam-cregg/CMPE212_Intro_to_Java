// class that extends Threaded, contains all threaded fasteners with a length attribute
public abstract class OuterThreaded extends Threaded {
    private static final long serialVersionUID = -6180543096571738719L;
    private double length;

    public OuterThreaded(String material, String finish, double unitPrice, int numUnits,
                         String thread, double length) throws IllegalFastener {
        super(material, finish, unitPrice, numUnits, thread);
        if (!isLengthOK(length))
            throw new IllegalFastener("Illegal length value");
        this.length = length;
    }

    public boolean isLengthOK(double length) {
        if(length < 0.5)
            return false;
        else if(length < 6)
            return length % 0.25 == 0;
        else if(length < 11)
            return length % 0.5 == 0;
        else if(length <= 20)
            return length % 1 == 0;
        return false;
    }

    // add length attribute to toString
    @Override
    public String toString() {
        return length + "\" long, "+ super.toString();
    }
}
