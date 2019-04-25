package PizzaApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Controller {

    private static final ObservableList<String> SIZE_OPTIONS = FXCollections.observableArrayList("Small", "Medium", "Large");
    private static final ObservableList<String> CHEESE_OPTIONS = FXCollections.observableArrayList("Single", "Double", "Triple");
    private LegalPizzaChoices.Cheese cheese = LegalPizzaChoices.Cheese.Single;
    private LegalPizzaChoices.Size size = LegalPizzaChoices.Size.Small;
    private LegalPizzaChoices.Topping ham = LegalPizzaChoices.Topping.None;
    private LegalPizzaChoices.Topping pineapple = LegalPizzaChoices.Topping.None;
    private LegalPizzaChoices.Topping pepper = LegalPizzaChoices.Topping.None;
    private double pizzaCost = 7;
    private double itemCost = 0;
    private double totalCost = 0;
    private int num;
    private static ArrayList<LineItem> orders = new ArrayList<>();

    @FXML
    private CheckBox pepperBox;

    @FXML
    private TextField number;

    @FXML
    private TextField costField;

    @FXML
    private TextField itemCostField;

    @FXML
    private TextArea orderField;

    @FXML
    private ChoiceBox<String> sizeChoice;

    @FXML
    private CheckBox pineappleBox;

    @FXML
    private CheckBox hamBox;

    @FXML
    private ChoiceBox<String> cheeseChoice;

    @FXML
    private ImageView image;

    @FXML
    void initialize() {
        cheeseChoice.setItems(CHEESE_OPTIONS);
        cheeseChoice.setValue("Single");
        cheeseChoice.getSelectionModel().selectedItemProperty().addListener(
                (ov, old_choice, new_choice) -> {
            if (new_choice.equals("Single"))
                cheese = LegalPizzaChoices.Cheese.Single;
            if (new_choice.equals("Double"))
                cheese = LegalPizzaChoices.Cheese.Double;
            if (new_choice.equals("Triple"))
                cheese = LegalPizzaChoices.Cheese.Triple;
            updatePizzaCost();
        });

        sizeChoice.setItems(SIZE_OPTIONS);
        sizeChoice.setValue("Small");
        sizeChoice.getSelectionModel().selectedItemProperty().addListener(
                (ov, old_choice, new_choice) -> {
            if (new_choice.equals("Small"))
                size = LegalPizzaChoices.Size.Small;
            if (new_choice.equals("Medium"))
                size = LegalPizzaChoices.Size.Medium;
            if (new_choice.equals("Large"))
                size = LegalPizzaChoices.Size.Large;
            updatePizzaCost();
        });

        number.textProperty().addListener(
                (ov, old_value, new_value) -> {
                    if (!new_value.matches("|100|[1-9][0-9]?")) {
                        number.setText(old_value);
                    }
                    updateLineCost();
                });

        pepperBox.setDisable(true);
        pineappleBox.setDisable(true);
        costField.setText("$" + pizzaCost);
        itemCostField.setText("$" + itemCost);
        number.setText("1");
        image.setImage(new Image("PizzaApp/art.jpg"));
    }

    @FXML
    private void pepperClick() {
        if (pepperBox.isSelected())
            pepper = LegalPizzaChoices.Topping.Single;
        else
            pepper = LegalPizzaChoices.Topping.None;
        updatePizzaCost();
    }

    @FXML
    private void pineappleClick() {
        if (pineappleBox.isSelected())
            pineapple = LegalPizzaChoices.Topping.Single;
        else
            pineapple = LegalPizzaChoices.Topping.None;
        updatePizzaCost();
    }

    @FXML
    private void hamClick() {
        if (hamBox.isSelected()) {
            ham = LegalPizzaChoices.Topping.Single;
            pepperBox.setDisable(false);
            pineappleBox.setDisable(false);
        } else {
            ham = LegalPizzaChoices.Topping.None;
            pepperBox.setDisable(true);
            pineappleBox.setDisable(true);
        }
        updatePizzaCost();
    }

    // allows for selection of dropdown options using the ENTER key
    @FXML
    private void cheeseDropDown(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
            cheeseChoice.show();
    }

    @FXML
    private void sizeDropDown(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
            sizeChoice.show();
    }

    // modified from PizzaOrderSystem
    private static int searchOrders(Pizza pizza) {
        for (int line = 0; line < orders.size(); line++)
            if (orders.get(line).getPizza().equals(pizza))
                return line;
        return -1;
    }

    @FXML
    private void addClick() {
        if(!number.getText().isEmpty()) {
            num = Integer.parseInt(number.getText());
            Pizza currentItem = new Pizza(size, cheese, pineapple, pepper, ham);
            LineItem order = new LineItem(num, currentItem);
            int orderLocation = searchOrders(currentItem);
            if (orderLocation < 0)
                orders.add(order);
            else {
                LineItem item = orders.get(orderLocation);
                item.setNumber(item.getNumber() + num);
            }
            displayOrder();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Please enter a valid number of pizzas");
            alert.showAndWait();
        }
    }

    private void updatePizzaCost() {
        Pizza currentItem = new Pizza(size, cheese, pineapple, pepper, ham);
        pizzaCost = currentItem.getCost();
        costField.setText("$" + pizzaCost);
        updateLineCost();
    }

    private void updateLineCost() {
        if(!number.getText().isEmpty())
            itemCostField.setText("$" + Integer.parseInt(number.getText()) * pizzaCost);
        else
            itemCostField.setText("$0.00");
    }

    private void displayOrder() {
        StringBuilder orderText = new StringBuilder();
        int lineNumber = 1;
        for(LineItem line : orders) {
            orderText.append(lineNumber++);
            orderText.append("\t");
            orderText.append(line.toString() + " Line cost: " + itemCostField.getText());
            orderText.append("\n");
            totalCost += line.getCost();
        }
        orderText.append("Total cost: $" + totalCost);
        orderField.setText(orderText.toString());
    }

}
