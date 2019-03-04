/**
 * Used to limit pizza configuration choices.
 *
 * @author Alan McLeod
 * @version 1.0
 */
public class LegalPizzaChoices {

        // Cannot use lower case double for a member, so we'll make them all capitalized:
        public enum Size {Small, Medium, Large};
        public enum Cheese {Single, Double, Triple};
        // Use this enum for the green pepper, pineapple and ham toppings:
        public enum Topping {None, Single};

    } // end LegalPizzaChoices
