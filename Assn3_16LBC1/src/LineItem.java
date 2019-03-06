import java.io.Serializable;

public class LineItem implements Comparable<LineItem>, Serializable {

    private static final long serialVersionUID = 858475072685585096L;
    private int numPizzas;
    private Pizza pizzaType;

    LineItem(int num, Pizza type) throws IllegalPizza {
        setNumber(num);
        setPizza(type);
    }

    LineItem(Pizza type) throws IllegalPizza {
        setNumber(1);
        setPizza(type);
    }

    public void setNumber(int num) throws IllegalPizza {
        if(num < 1 || num > 100)
            throw new IllegalPizza("Invalid number of pizzas!");
        this.numPizzas = num;
    }
    private void setPizza(Pizza type) throws IllegalPizza {
        if(type == null)
            throw new IllegalPizza("Invalid pizza!");
        pizzaType = type;
    }

    public int getNumber() {
        return this.numPizzas;
    }

    public Pizza getPizza() {
        return this.pizzaType;
    }

    public double getCost() {
        double price = 0;
        price += this.numPizzas * this.pizzaType.getCost();
        if(this.numPizzas >= 20)
            return price * 0.9;
        else if(this.numPizzas >= 10)
            return price * 0.95;
        return price;
    }

    @Override
    public String toString() {
        String s = "";
        if(this.numPizzas < 10)
            s = " ";
        s += String.format("%d ", this.getNumber()) + this.pizzaType.toString();
        return s;
    }

    @Override
    public int compareTo(LineItem other) {
        return (int)(other.getCost() - this.getCost());
    }
}
