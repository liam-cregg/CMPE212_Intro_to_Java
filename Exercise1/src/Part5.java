import java.io.IOException;

/**
 * @author Liam
 */

public class Part5 {

    /**
     * reads input from user and puts in a buffer array, then prints it
     * @param args number of args to main
     */
    public static void main (String[] args){
        byte[] buffer = new byte[5];
        int numRead = -1;
        System.out.print("Please enter something: ");
        try{
            numRead = System.in.read(buffer);
        }
        catch(IOException e){
            System.out.println("Should not have got here");
        }
        System.out.println("You typed in " + numRead + " characters.");
        System.out.println("As bytes:");
        for (int i = 0; i < numRead; i++)
            System.out.println(buffer[i]);
    } // end main method
}
