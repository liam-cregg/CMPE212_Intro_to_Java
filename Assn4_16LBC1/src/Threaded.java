import java.util.Set;
// contains all fasteners with a thread attribute
public abstract class Threaded extends Fastener {
    private static final long serialVersionUID = 2172849882773438408L;
    private String thread;
    private static final Set<String> VALID_THREADS = Set.of("#8-13", "#8-15", "#8-32", "#10-13", "#10-24",
            "#10-32", "1/4-20", "5/16-18", "3/8-16", "7/16-14", "1/2-13", "5/8-11", "3/4-10");

    public Threaded(String material, String finish, double unitPrice, int numUnits, String thread) throws IllegalFastener {
        super(material, finish, unitPrice, numUnits);
        if(!isThreadOK(thread))
            throw new IllegalFastener("Illegal thread value");
        this.thread = thread;
    }

    private boolean isThreadOK(String thread) {
        return VALID_THREADS.contains(thread.toLowerCase());
    }

    // add thread attribute to toString
    @Override
    public String toString() {
        return thread + " thread, " + super.toString();
    }
}
