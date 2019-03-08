/*
 * This is a "helper" utility class by Alan McLeod.
 * It contains robust methods that can be used to:
 * 		- obtain an int from the user
 * 		- obtain a double from the user
 * 		- obtain a String from the user
 * The methods to obtain a number from the user can optionally
 * accept the following parameters:
 * 		- the low legal limit
 * 		- the high legal limit
 * 		- a String prompt
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class IOHelper {

    private static Scanner screenInput = new Scanner(System.in);

    /**
     * Gets the integer value entered by the user
     * @param low Lower bound
     * @param prompt Message to give
     * @param high Upper bound
     * @return The number entered
     */
    public static int getInt(int low, String prompt, int high) {
        int numFromUser = 0;
        String dummy;
        boolean numericEntryOK;
        do {
            System.out.print(prompt);
            numericEntryOK = false;
            try {
                numFromUser = screenInput.nextInt();
                //clear keyboard buffer
                screenInput.nextLine();
                numericEntryOK = true;
            } catch (InputMismatchException e) {
                dummy = screenInput.nextLine();
                System.out.println(dummy + " is not an integer!");
                numFromUser = low;
            } // end try-catch
            // Indicate to the user why he is being prompted again.
            if (numFromUser < low || numFromUser > high) {
                System.out.println("The number is outside the legal limits.");
            }
        } while (!numericEntryOK || numFromUser < low || numFromUser > high);
        return numFromUser;
    } // end full parameter getInt method

    public static int getInt() {
        int low = Integer.MIN_VALUE;
        int high = Integer.MAX_VALUE;
        String prompt = "Please enter any integer: ";
        return getInt(low, prompt, high);
    } // end no parameter getInt method

    public static int getInt(String prompt) {
        int low = Integer.MIN_VALUE;
        int high = Integer.MAX_VALUE;
        return getInt(low, prompt, high);
    } // end one parameter getInt method

    public static int getInt(int low, String prompt) {
        int high = Integer.MAX_VALUE;
        return getInt(low, prompt, high);
    } // end two parameter getInt method

    public static int getInt(String prompt, int high) {
        int low = Integer.MIN_VALUE;
        return getInt(low, prompt, high);
    } // end two parameter getInt method

    /**
     * Gets the string entered by the user
     * @param prompt Message to give
     * @return The String entered
     */
    public static String getString(String prompt) {
        String userText;
        System.out.print(prompt);
        do {
            userText = screenInput.nextLine();
        } while(userText.equals("")); // allows user to enter unlimited empty lines
        return userText;
    } // end one parameter getString method

} // end IOHelper class
