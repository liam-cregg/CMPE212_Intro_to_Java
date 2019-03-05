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

    public Pizza(LegalPizzaChoices.Size sz, LegalPizzaChoices.Cheese cz,
                 LegalPizzaChoices.Topping pa, LegalPizzaChoices.Topping pp,
                 LegalPizzaChoices.Topping hm) {
        size = sz;
        cheese = cz;
        ham = hm;
        if (hm == LegalPizzaChoices.Topping.Single) {
            pineapple = pa;
            pepper = pp;
        } else {
            if (pa == LegalPizzaChoices.Topping.Single || pp == LegalPizzaChoices.Topping.Single) {
                System.out.println("Cannot have pineapple or green pepper if not having ham, sorry :(. Excluding these toppings");
                pineapple = LegalPizzaChoices.Topping.None;
                pepper = LegalPizzaChoices.Topping.None;
            }
        }
    }

    public Pizza() {
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
        String s = size + "pizza, " + cheese + "cheese";
        if(pineapple == LegalPizzaChoices.Topping.Single)
            s += ", pineapple";
        if(pepper == LegalPizzaChoices.Topping.Single)
            s += ", green pepper";
        if(ham == LegalPizzaChoices.Topping.Single)
            s += ", ham";
        s += ". Cost: $" + getCost() + " each.";
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
        catch(ExceptionInInitializerError e) {
            System.out.println(e.getMessage());
        }
        return pizzaCopy;
    }
}
