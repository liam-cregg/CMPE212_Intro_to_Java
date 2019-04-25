package PizzaApp;
/**
 * Standard exception class, to be thrown if a Pizza object is illegal
 */
public class IllegalPizza extends IllegalArgumentException {
    /**
     * Throws an exception with a message
     * @param message The message to be given when the exception is thrown
     */
    public IllegalPizza(String message) {
        super(message);
    }
}
