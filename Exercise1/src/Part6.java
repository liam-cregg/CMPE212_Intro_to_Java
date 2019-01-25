import javax.swing.*;
import java.awt.*;

/**
 * @author Liam
 */

public class Part6 {

    /**
     * uses GUI input to get name and prints it
     * @param arg number of arguments to main
     */
    public static void main(String[] arg){
        String userInput;
        boolean inputOK = false;
        int userNum = 0;
        while(!inputOK) {
            try {
                userInput = JOptionPane.showInputDialog("Enter a number");
                userNum = Integer.parseInt(userInput);
                inputOK = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "That wasn't an acceptable input, try again:");
            } // end try-catch
        } // end while
        JOptionPane.showMessageDialog(null, "Your number is " + userNum);
    } // end main method
}
