// extends Exception, provides a default and custom message
public class IllegalFastener extends Exception {
    public IllegalFastener() {
        super("Illegal fastener");
    }
    public IllegalFastener(String message) {
        super(message);
    }
}
