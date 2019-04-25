package PizzaApp;
import java.io.Serializable;

/**
 * The LineItem class as used by PizzaEnumOrderSystem.
 * Includes the type of pizza and number of those pizzas in the order
 */
public class LineItem implements Comparable<LineItem>, Serializable {

    private static final long serialVersionUID = 858475072685585096L;
    private int numPizzas;
    private Pizza pizzaType;

    /**
     * Constructor for the LineItem, based on Pizza object and number of pizzas
     * @param num The number of pizzas to be ordered
     * @param type The Pizza object
     * @throws IllegalPizza If the Pizza object is null, or if the number of pizzas is less than 0/greater than 100
     */
    public LineItem(int num, Pizza type) throws IllegalPizza {
        setNumber(num);
        setPizza(type);
    }

    /**
     * Default constructor, sets the number of pizzas to 1
     * @param type The Pizza object
     * @throws IllegalPizza If the Pizza object is null
     */
    public LineItem(Pizza type) throws IllegalPizza {
        setNumber(1);
        setPizza(type);
    }

    /**
     * Mutator for number of pizzas in the LineItem
     * @param num The number of pizzas
     * @throws IllegalPizza If the number is less than 0/greater than 100
     */
    public void setNumber(int num) throws IllegalPizza {
        if(num < 1 || num > 100)
            throw new IllegalPizza("Invalid number of pizzas!");
        this.numPizzas = num;
    }

    // Sets the pizza type of the order. Throws IllegalPizza if the Pizza object is null
    private void setPizza(Pizza type) throws IllegalPizza {
        if(type == null)
            throw new IllegalPizza("Invalid pizza!");
        pizzaType = type;
    }

    /**
     * Gets the number of pizzas in the order
     * @return The number of pizzas
     */
    public int getNumber() {
        return this.numPizzas;
    }

    /**
     * Gets the type of pizza
     * @return The Pizza object
     */
    public Pizza getPizza() {
        return this.pizzaType;
    }

    /**
     * Gets the total cost of the LineItem, including discounts
     * @return The cost of the line
     */
    public double getCost() {
        double price = 0;
        price += this.numPizzas * this.pizzaType.getCost();
        if(this.numPizzas >= 20) // Discount if >=20 pizzas
            return price * 0.9;
        else if(this.numPizzas >= 10) // Discount if between 10 and 20 pizzas
            return price * 0.95;
        return price; // No discount
    }

    /**
     * Converts the LineItem object into a String
     * @return The String of the LineItem
     */
    @Override
    public String toString() {
        String s = "";
        if(this.numPizzas < 10)
            s = " "; // Add space so that numbers line up
        s += String.format("%d ", this.getNumber()) + this.pizzaType.toString();
        return s;
    }

    /**
     * Compares the LineItem with the supplied LineItem on the basis of price, so that ArrayList can list the lines
     * in descending order
     * @param other The LineItem to compare to
     * @return The ordering priority for the ArrayList
     */
    @Override
    public int compareTo(LineItem other) {
        return (int)(other.getCost() - this.getCost()); // Decreasing order
    }
}