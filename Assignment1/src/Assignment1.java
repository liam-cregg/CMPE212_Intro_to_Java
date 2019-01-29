/**
 * Formatted with Google Java Style Guide
 * @author Liam - 16LBC1
 */

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Plays the game of Pig against the computer.
 */
public class Assignment1 {

    static Random GENERATOR = new Random(System.currentTimeMillis());
    static final Scanner SCREEN = new Scanner(System.in);
    static final String[] DICE_ARRAY = {"one", "two", "three", "four", "five", "six"};
    static final String[] CONDITIONS = {"DOUBLE ONES", "DOUBLES", "SINGLE ONE :("};

    public static void main(String[] args) {
        int playerScore = 0;
        int computerScore = 0;
        System.out.println("Welcome to Pig! You will start");
        //TODO Integrate methods

    }

    /**
     * Get's the user input, allowing 'y' or 'n'
     * @return the string entered by the user
     */
    public static String getInput() {
        String userString = null;
        boolean inputOK = false;
        while (!inputOK) {
            System.out.print("Enter y or n: ");
            userString = SCREEN.nextLine();
            // check if valid input
            if(userString.equals("y") || userString.equals("n"))
                inputOK = true;
            else
                System.out.println("\"" + userString + "\" is not a legal input, please enter 'y' or 'n'!");
        } // end input loop
        return userString;
    }

    public static int rollDice() {
        return GENERATOR.nextInt(6);
    }

    public static int playTurn(int currentScore, boolean humanTurn) {
        int[] diceValues = new int[2];
        int condition;
        if(humanTurn) {
            System.out.println("Human's turn:");
            do {
                // store dice rolls in array
                diceValues[0] = rollDice();
                diceValues[1] = rollDice();
                System.out.println("You rolled " + DICE_ARRAY[diceValues[0]] + " and " + DICE_ARRAY[diceValues[1]]);
                condition = checkSpecial(diceValues);
                // check if regular roll
                if(condition == -1) {
                    //TODO Update values, ask if go again
                }
                if{CONDITIONS[condition].equals("DOUBLE ONES")
                    //TODO Make go again
                }
                if(CONDITIONS[condition].equals("DOUBLES")) {
                    //TODO Make go again
                }
                if(CONDITIONS[condition].equals("SINGLE ONE :("){
                    //TODO Make exit
                }
            }
        }
    }

    public static int checkSpecial(int[] diceValues) {
        // check if doubles
        if(diceValues[0] == diceValues[1]) {
            // check if double ones (first element in conditions list)
            if(diceValues[0] == 1) {
                return 0;
            }
            // must be regular doubles (second element in conditions list)
            else
                return 1;
        }
        // check if single one (third element in conditions list)
        else if (diceValues[0] == 1 || diceValues[1] == 1)
            return 2;
        // must be regular roll (not a special condition)
        else
            return -1;
        }
    }
