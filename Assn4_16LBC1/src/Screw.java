import java.util.Set;
// extends OuterThreaded, contains any screw
// introduces several new attributes and corresponding checks
public abstract class Screw extends OuterThreaded {
    private static final long serialVersionUID = -2056091709218502L;
    private String head;
    private String drive;
    private static final Set<String> validHeads = Set.of("bugle", "flat", "oval", "pan", "round");
    private static final Set<String> validDrives = Set.of("6-lobe", "philips", "slotted", "square");
    private static final Set<String> moreValidSteelScrewFinishes = Set.of("black phosphate", "acq 1000 hour", "lubricated");

    public Screw(String material, String finish, double unitPrice, int numUnits,
                 String thread, double length, String head, String drive) throws IllegalFastener {
        super(material, finish, unitPrice, numUnits, thread, length);
        if(!isHeadOK(head))
            throw new IllegalFastener("Illegal head value");
        this.head = head.toLowerCase();
        if(!isDriveOK(drive))
            throw new IllegalFastener("Illegal drive value");
        this.drive = drive.toLowerCase();
    }

    private boolean isHeadOK(String head) {
        return validHeads.contains(head.toLowerCase());
    }

    private boolean isDriveOK(String drive) {
        return validDrives.contains(drive.toLowerCase());
    }

    @Override
    public boolean isFinishOK(String finish, String material) {
        if(material.equalsIgnoreCase("steel"))
            return super.isFinishOK(finish, material) || moreValidSteelScrewFinishes.contains(finish.toLowerCase());
        return super.isFinishOK(finish, material);
    }

    // add attributes to toString method
    @Override
    public String toString() {
        return head + " head, " + drive + " drive, " + super.toString();
    }
}
