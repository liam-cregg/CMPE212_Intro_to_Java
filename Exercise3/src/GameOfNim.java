/**
 * A class made to initialize the starting conditions for the game of Nim. Uses the IOHelper found in Exercise1
 * @author Liam
 */

public class GameOfNim {
    /**
     * Determines who starts the game
     *
     * @return true if human, false if computer
     */
    public static boolean whosTurn() {
        if (Math.random() > 0.5) {
            System.out.println("You start");
            return true;
        } else {
            System.out.println("The computer starts");
            return false;
        }
    }

    /**
     * Determines the number of starting marbles based on a randomly generated number
     *
     * @return # of marbles
     */
    public static int marblesStart() {
        return (10 + Math.round((float) Math.random() * 90));
    }

    /**
     * Determines if the computer is playing smart or randomly
     *
     * @return 1 if it's smart, 0 if not
     */
    public static boolean isSmart() {
        if (Math.random() > 0.5) {
            System.out.println("The computer is playing smart");
            return true;
        } else {
            System.out.println("The computer is playing randomly");
            return false;
        }
    }

    /**
     * Gets the human's choice, allowing values based on low and high inputs
     *
     * @param prompt
     * @param low
     * @param high
     * @return Number of marbles chosen by human
     */
    public static int getHumanChoice(String prompt, int low, int high) {
        return IOHelper.getInt(low, prompt, high);
    }

    /**
     * Gets the computer's choice, based on number of marbles left
     *
     * @param isSmart
     * @param numLeft
     * @param numMarbles
     * @param power
     * @return
     */
    public static int getComputerChoice(boolean isSmart, int numLeft, int numMarbles, int power) {
        if (isSmart) {
            power = 6;
            numLeft = (int) Math.pow(2, power) - 1;
            // get desired number
            while (numLeft > numMarbles) {
                power--;
                numLeft = (int) Math.pow(2, power) - 1;
            }
            // check if possible to choose this
            if ((numMarbles - numLeft) == 0 || (numLeft - numMarbles) > (numMarbles / 2))
                return 1 + Math.round((float) Math.random() * (numMarbles / 2 - 1));
            else
                return (numMarbles - numLeft);
        } else
            return 1 + Math.round((float) Math.random() * (numMarbles / 2 - 1));
    }

    /**
     * Plays a round of the game, returning the number of marbles left
     *
     * @return # of marbles left
     */
    public static int playTurn(int numMarbles, boolean isSmart, boolean humansTurn, String prompt, int humanChoice, int computerChoice) {
        System.out.println("There are " + numMarbles + " marbles left");
        if (humansTurn) {
            if (numMarbles <= 3)
                prompt = "Choose 1 marble: ";
            else
                prompt = "Choose between 1 and " + (numMarbles / 2) + " marbles:";
            humanChoice = getHumanChoice(prompt, 1, numMarbles / 2);
            numMarbles -= humanChoice;
            if(numMarbles == 1)
                System.out.println("You won!");
        } else {
            computerChoice = getComputerChoice(isSmart, 0, numMarbles, 0);
            System.out.print("The computer has chosen " + computerChoice + " marble");
            if (computerChoice > 1)
                System.out.println("s");
            else
                System.out.println();
            numMarbles -= computerChoice;
            if(numMarbles == 1)
                System.out.println("The computer won :(");
        }
        return numMarbles;
    }

    /**
     * Plays the game using above methods
     */
    public static void playGame() {
        boolean isSmart = isSmart();
        boolean humansTurn = whosTurn();
        int numMarbles = marblesStart();
        String prompt = "";
        int humanChoice = 0, computerChoice = 0;
        while(numMarbles > 1){
            numMarbles = playTurn(numMarbles, isSmart, humansTurn, prompt, humanChoice, computerChoice);
            humansTurn = !humansTurn;
        }
    }

    public static void main(String[] args) {
        playGame();
    }
}
