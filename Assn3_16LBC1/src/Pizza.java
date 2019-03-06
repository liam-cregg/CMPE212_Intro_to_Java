import java.io.Serializable;

/**
 * @author Liam Cregg - 16LBC1 - 20054881
 * @version 1.0
 */
public class Pizza implements Serializable {

    private static final long serialVersionUID = -3919996667157794031L;
    private LegalPizzaChoices.Size size;
    private LegalPizzaChoices.Cheese cheese;
    private LegalPizzaChoices.Topping pineapple;
    private LegalPizzaChoices.Topping pepper;
    private LegalPizzaChoices.Topping ham;

    public Pizza (LegalPizzaChoices.Size size, LegalPizzaChoices.Cheese cheese,
                 LegalPizzaChoices.Topping pineapple, LegalPizzaChoices.Topping pepper,
                 LegalPizzaChoices.Topping ham) throws IllegalPizza {
        setSize(size);
        setCheese(cheese);
        setToppings(pineapple, pepper, ham);
    }

    private void setSize(LegalPizzaChoices.Size size) throws IllegalPizza {
        if(size == null)
            throw new IllegalPizza("Incomplete order!");
        this.size = size;
    }
    private void setCheese(LegalPizzaChoices.Cheese cheese) throws IllegalPizza {
        if(cheese == null)
            throw new IllegalPizza("Incomplete order!");
        this.cheese = cheese;
    }
    private void setToppings(LegalPizzaChoices.Topping pineapple, LegalPizzaChoices.Topping pepper,
                             LegalPizzaChoices.Topping ham) throws IllegalPizza {
        if(pineapple == null || pepper == null || ham == null)
            throw new IllegalPizza("Incomplete order!");
        this.ham = ham;
        if(this.ham == LegalPizzaChoices.Topping.Single) {
            this.pineapple = pineapple;
            this.pepper = pepper;
        }
        else if(pineapple == LegalPizzaChoices.Topping.Single || pepper == LegalPizzaChoices.Topping.Single) {
            throw new IllegalPizza("Illegal combination of toppings!");
        }
        this.pineapple = pineapple;
        this.pepper = pepper;
    }

    public Pizza() throws IllegalPizza {
        this(LegalPizzaChoices.Size.Small, LegalPizzaChoices.Cheese.Single,
                LegalPizzaChoices.Topping.None, LegalPizzaChoices.Topping.None,
                LegalPizzaChoices.Topping.Single);
    }

    public double getCost() {
        double cost = 7.00;
        // increase cost based on size
        cost += size.ordinal() * 2;
        // increase cost based on # of toppings
        cost += 1.5 * (cheese.ordinal() + pepper.ordinal() + pineapple.ordinal() + ham.ordinal());
        return cost;
    }

    @Override
    public String toString() {
        String s = size + " pizza, " + cheese + " cheese";
        if(pineapple == LegalPizzaChoices.Topping.Single)
            s += ", pineapple";
        if(pepper == LegalPizzaChoices.Topping.Single)
            s += ", green pepper";
        if(ham == LegalPizzaChoices.Topping.Single)
            s += ", ham";
        s += ". Cost: $" + String.format("%.2f",getCost()) + " each.";
        return s;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof Pizza) {
            return (((Pizza) other).size == size && ((Pizza) other).cheese == cheese && ((Pizza) other).ham == ham
                    && ((Pizza) other).pepper == pepper && ((Pizza) other).pineapple == pineapple);
            }
        return false;
    }
    @Override
    public Pizza clone() {
        Pizza pizzaCopy = null;
        try
        {
            pizzaCopy = new Pizza(size, cheese, pineapple, pepper, ham);
        }
        catch(IllegalPizza e) {
            throw new RuntimeException(e);
        }
        return pizzaCopy;
    }
}
