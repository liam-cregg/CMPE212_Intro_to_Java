/**
 * Formatted with Google Java Style Guide
 * @author Liam - 16LBC1
 */

import java.util.Random;
import java.util.Scanner;

/**
 * Plays the game of Pig against the computer.
 * When starting, must click on the line below the initial prompt before hitting enter.
 * Then play the game only using the keyboard, if you move the cursor it will affect the input buffer.
 */
public class Assignment1 {

    static Random GENERATOR = new Random(System.currentTimeMillis());
    static final Scanner SCREEN = new Scanner(System.in);
    static final String[] DICE_ARRAY = {"", "one", "two", "three", "four", "five", "six"};
    static final String[] CONDITIONS = {"DOUBLE ONES :) Must roll again!", "DOUBLES :) Must roll again!", "SINGLE ONE :( Turn sum is zero!"};

    public static void main(String[] args) {
        int playerScore = 0;
        int computerScore = 0;
        boolean playersTurn = true;
        System.out.println("Welcome to Pig! You go first, press <enter> to start:");
        SCREEN.nextLine();
        for (int i = 0; !endGame(computerScore, playerScore); i++) {
            if(playersTurn) {
                playerScore = playTurn(playerScore, "Human");
                playersTurn = false;
            }
            else {
                computerScore = playTurn(computerScore, "Computer");
                playersTurn = true;
            }
            System.out.print(
                    "\nPlayer's sum is: " + playerScore +
                    " , Computer's sum is " + computerScore);
            // ask for confirmation if game not over
            if(!endGame(computerScore, playerScore) && playersTurn) {
                System.out.println(". Press <enter> to start round " + (i + 1));
                SCREEN.nextLine();
            }
            else
                System.out.println("\n");
        }
        if(playerScore >= 100)
            System.out.println("Human wins!");
        else
            System.out.println("Computer wins!");
    }

    /**
     * Gives the end condition of the game.
     * @param computerScore     current score of the computer
     * @param playerScore       current score of the player
     * @return                  true if game over
     */
    public static boolean endGame(int computerScore, int playerScore) {
        return (computerScore >= 100 || playerScore >= 100);
    }
    /**
     * Get's the user input, allowing 'y' or 'n'
     * @return the string entered by the user
     */
    public static boolean getInput() {
        String userString = null;
        while (true) {
            System.out.print("Roll again? Enter y or n: ");
            userString = SCREEN.nextLine();
            // check if valid input
            switch(userString) {
                case "y":
                case "Y":
                    return true;
                case "n":
                case "N":
                    return false;
                default:
                    System.out.println("\"" + userString + "\" is not a legal input, please enter 'y' or 'n'!");
            }
        } // end input loop
    }

    /**
     * The computer must decide whether to continue playing the game.
     * It's strategy is to stop playing if it's turn score is high enough to win, or over 20.
     * This strategy was modified from the initial 40 requirement
     * because otherwise it was very difficult for the computer to win.
     * @param currentScore  the total score of the computer
     * @param turnScore     the turn score of the user
     * @return              a boolean value, true if computer decides to replay
     */
    public static boolean getComputerInput(int currentScore, int turnScore) {
        return !((currentScore + turnScore >= 100) || turnScore > 20);
    }

    /**
     * Determines if there are any special game conditions met by the dice roll.
     * Double ones  -> add 25 to turn score
     * Doubles      -> double value of dice and add to turn score
     * Single one   -> identified by -1, code handled in playTurn method
     * Regular roll -> identified by 0, code handles in playTurn method
     * @param diceValues    the array of rolled values
     * @param turnScore     the current turn score
     * @return              the additional score, or the 0/-1 identifier
     */
    public static int checkSpecial(int[] diceValues, int turnScore) {
        // check if doubles
        if (diceValues[0] == diceValues[1]) {
            // check if double ones (first element in conditions list)
            if (diceValues[0] == 1) {
                System.out.println(CONDITIONS[0]);
                return 25;
            }
            // must be regular doubles (second element in conditions list)
            else {
                System.out.println(CONDITIONS[1]);
                return (2 * (diceValues[0] + diceValues[1]));
            }
        }
        // check if single one (third element in conditions list)
        else if (diceValues[0] == 1 || diceValues[1] == 1) {
            System.out.println(CONDITIONS[2]);
            return -1;
        }
        // must be regular roll (not a special condition)
        else
            return 0;
    }

    /**
     * Randomly rolls dice and returns an array with 2 integers, each between 1 and 6
     * @return an array of integers, each corresponding to an index of DICE_ARRAY
     */
    public static int[] rollDice(int[] diceValues) {
        diceValues[0] = GENERATOR.nextInt(6) + 1;
        diceValues[1] = GENERATOR.nextInt(6) + 1;
        return diceValues;
    }

    /**
     * Plays one turn of the game, checking if there are any special conditions met and adjusting score accordingly.
     * @param currentScore  the current score of the player (human or computer)
     * @param player        the name of the player (human or computer) as a String
     * @return              the new total score of the player (human or computer)
     */
    public static int playTurn(int currentScore, String player) {
        int[] diceValues = new int[2];
        int condition;
        int turnScore = 0;
        boolean replay = true;
        System.out.println(player + "\'s turn:");
        do {
            // store dice rolls in array
            diceValues = rollDice(diceValues);
            System.out.println(player + " rolled " + DICE_ARRAY[diceValues[0]] +
                               " and " + DICE_ARRAY[diceValues[1]]);
            condition = checkSpecial(diceValues, turnScore);
            // if regular roll
            if (condition == 0) {
                turnScore += diceValues[0] + diceValues[1];
                System.out.println(player + "'s turn sum is: " + turnScore +
                                   " and game sum would be: " + (currentScore + turnScore));
                if (player.equals("Human")) {
                    replay = getInput();
                }
                else {
                    replay = getComputerInput(currentScore, turnScore);
                }
            }
            // if single one
            else if (condition == -1) {
                replay = false;
                turnScore = 0;
            }
            // else must be doubles or double ones, must go again
            // condition = bonus score
            else {
                turnScore += condition;
                System.out.println(player + "'s turn sum is: " + turnScore +
                                   " and game sum would be: " + (currentScore + turnScore));
            }
        } while (replay); // end do-while loop

        System.out.println("Turn over");
        return currentScore + turnScore;
    }
}

