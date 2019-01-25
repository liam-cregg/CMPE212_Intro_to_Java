import javax.swing.*;

/**
 * This class can read different types of input depending on the method called
 * and output this input to the screen.
 * The 3 methods are getInt, getDouble, and getString.
 * These methods can take high, low, amd prompt values.
 *
 * @author Liam
 */

public class IOHelper {

    /**
     * Reads an integer from the user based on acceptable range parameters.
     * @param low The lower boundary for acceptable input
     * @param prompt The prompt for the user to input something
     * @param high The upper boundary of acceptable input
     * @return The integer entered by the user
     */
    public static int getInt(int low, String prompt, int high) {

        int userNum = 0;
        boolean inputOK = false;
        String userInput;

        while(!inputOK) {
            try {
                userInput = JOptionPane.showInputDialog(prompt);
                userNum = Integer.parseInt(userInput);
                if(userNum >= low && userNum <=high)
                    inputOK = true;
                if(!inputOK)
                    JOptionPane.showMessageDialog(null, "Not within range :(");
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "That wasn't an integer, try again muchacho");
            } // end try-catch
        } // end while loop

        return userNum;

    } // end 3-input getInt method

    /**
     * Reads and displays integer input only above a certain value
     * @param low The lower boundary for acceptable input
     * @param prompt The prompt to the user to input something
     * @return The integer entered by the user
     */
    public static int getInt(int low, String prompt) {
        return getInt(low, prompt, Integer.MAX_VALUE);
    } // end low-only getInt method

    /**
     * Reads and displays user input only below a certain value
     * @param prompt The prompt to the user to input something
     * @param high The upper boundary for an acceptable input
     * @return The integer entered by the user
     */
    public static int getInt(String prompt, int high) {
        return getInt(Integer.MIN_VALUE, prompt, high);
    } // end high-only getInt method

    /**
     * Reads and displays user input
     * @param prompt The prompt to the user to input something
     * @return The integer entered by the user
     */
    public static int getInt(String prompt) {
        return getInt(Integer.MIN_VALUE, prompt, Integer.MAX_VALUE);
    } // end prompt-only getInt method

    /**
     *
     * @return The integer entered by the user
     */
    public static int getInt() {

        return getInt(Integer.MIN_VALUE, "Input an integer:", Integer.MAX_VALUE);
    } // end empty getInt method

    /**
     * Reads a double from the user based on acceptable range parameters.
     * @param low The lower boundary for acceptable input
     * @param prompt The prompt for the user to input something
     * @param high The upper boundary of acceptable input
     * @return The double entered by the user
     */
    public static double getDouble(double low, String prompt, double high) {

        double userNum = 0;
        boolean inputOK = false;
        String userInput;

        while(!inputOK) {
            try {
                userInput = JOptionPane.showInputDialog(prompt);
                userNum = Double.parseDouble(userInput);
                if(userNum >= low && userNum <= high)
                    inputOK = true;
                if(!inputOK)
                    JOptionPane.showMessageDialog(null, "Not within range :(");
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "That wasn't a double, try again muchacho");
            } // end try-catch
        } // end while loop

        return userNum;

    } // end 3-input getDouble method

    /**
     * Reads and displays double input only above a certain value
     * @param low The lower boundary for acceptable input
     * @param prompt The prompt to the user to input something
     * @return The double entered by the user
     */
    public static double getDouble(double low, String prompt) {
        return getDouble(low, prompt, Double.MAX_VALUE);
    } // end low-only getInt method

    /**
     * Reads and displays user input only below a certain value
     * @param prompt The prompt to the user to input something
     * @param high The upper boundary for an acceptable input
     * @return The double entered by the user
     */
    public static double getDouble(String prompt, double high) {
        return getDouble(Double.MIN_VALUE, prompt, high);
    } // end high-only getDouble method

    /**
     * Reads and displays user input
     * @param prompt The prompt to the user to input something
     * @return The double entered by the user
     */
    public static double getDouble(String prompt) {
        return getDouble(Double.MIN_VALUE, prompt, Double.MAX_VALUE);
    } // end prompt-only getDouble method

    /**
     *
     * @return The double entered by the user
     */
    public static double getDouble() {
        return getDouble(Double.MIN_VALUE, "Input an integer:");
    } // end empty getDouble method

    public static String getString(String prompt){
        String userString;
        userString = JOptionPane.showInputDialog(prompt);
        return userString;
    }
}