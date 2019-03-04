
public class LineItem {

    public int numPizzas;
    private Pizza pizzaType;

    LineItem(int num, Pizza type) throws IllegalPizza {
        setNum(num);
        pizzaType = type;
    }

    LineItem(Pizza type) {
        numPizzas = 1;
        pizzaType = type;
    }

    public void setNum(int num) throws IllegalPizza {
        if(num <= 0 || num >= 100)
            throw new IllegalPizza("Invalid number of pizzas!");
        this.numPizzas = num;
    }

    public int getNumPizzas() {
        return this.numPizzas;
    }

    public Pizza getPizzaType() {
        return this.pizzaType;
    }

    public double getLinePrice() {
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
        String s = null;
        if(this.numPizzas < 100)
            s += " ";
        if(this.numPizzas < 10)
            s += " ";
        s += this.pizzaType.toString();
        return s;
    }


}
