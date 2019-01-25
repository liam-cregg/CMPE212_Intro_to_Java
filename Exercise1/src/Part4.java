import java.util.InputMismatchException;
import java.util.Scanner;

public class Part4 {

    public static void main(String[] args) {
        int userNum = 0;
        //String userString;
        Scanner screen = new Scanner(System.in);
        boolean inputOK = false;
        String dump = null;
        while (!inputOK) {
            System.out.print("Enter a number: ");
            try {
                userNum = screen.nextInt();
                dump = screen.nextLine();
                inputOK = true;
            } catch (InputMismatchException e) {
                dump = screen.nextLine();
                System.out.println("\"" + dump + "\" is not a legal integer, please try again!");
            } // end try-catch block
        } // end input loop
            userNum = userNum + 20;
            System.out.println("Your number plus 20 is " + userNum);
            //System.out.print("Enter a string: ");
            //userString = screen.nextLine();
            //userString = screen.nextLine();
            //System.out.println("You typed: " + userString);
            System.out.println("Program is done!");
        screen.close();
    } //end main method
} //end Part4 class
