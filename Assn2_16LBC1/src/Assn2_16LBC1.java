
import java.io.*;

/**
 * @author Liam Cregg - 16LBC1 - 20054881
 * @version 1.0
 */
public class Assn2_16LBC1 {

    private static final int NUM_LINES = 1000;
    private static final int NUM_MOTORS = 7;

    /**
     * Runs the data processing for the motor data contained in the "Logger.csv" file.
     * and writes this processed data to csv files.
     * @param args standard args to main
     */
    public static void main(String[] args) {
        System.out.println("Analyzing data from Logger.csv\n");
        writeReport(getAnalyzedData(readData("Logger.csv")));
        System.out.println("Finished analyzing data.\n");
    }

    /**
     * Reads the data from the file and stores it in a 2D array. Uses the storeLine method below.
     * @param fileName name of the csv file to be read.
     * @return pointer to array
     */
    private static double[][] readData(String fileName) {
        double[][] data = new double[NUM_MOTORS][NUM_LINES];
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            for(int i = 0; i < NUM_LINES; i++) {
                line = br.readLine();
                String[] current_line = line.split(",");
                storeLine(current_line, data, i);
            }
        }
        catch(IOException | NumberFormatException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return data;
    }

    /**
     * Reads one line of the csv file and saves the values to the corresponding spots in the array.
     * @param currentLine string containing current line of csv file
     * @param data pointer to target array
     * @param lineNum current line number
     * @throws NumberFormatException if data can't be parsed by parseDouble
     */
    private static void storeLine(String[] currentLine, double[][] data, int lineNum)
    throws NumberFormatException {
        for(int motor = 0; motor < NUM_MOTORS; motor++) {
            data[motor][lineNum] = Double.parseDouble(currentLine[motor + 1]);
        }
    }

    /**
     * Analyzes data from array and stores corresponding reports.
     * @param data pointer to array of data
     * @return array of strings, containing reports
     */
    private static String[] getAnalyzedData(double[][] data) {
        String[] report = new String[NUM_MOTORS];
        for(int motor = 0; motor < NUM_MOTORS; motor++) {
            report[motor] = "start (sec), finish (sec), current (amps)\r\n";
            analyzeData(data[motor], motor, report);
            isUsed(motor, report);
        }
        return report;
    }

    /**
     * Takes data for an individual motor, generates statistics, and adds to relevant report.
     * @param motorData pointer to array containing data of one motor
     * @param motor column index of motor (motor # - 1)
     * @param report pointer to array of strings containing reports
     */
    private static void analyzeData(double[] motorData, int motor, String[] report) {
        boolean motorOn = false;
        int startTime = 0;
        int endTime = 0;
        double avgCurrent;
        boolean currentExceeded;
        for(int i = 0; i < NUM_LINES; i++) {
            if(turningOn(motorData[i], motorOn)) {
                startTime = i;
                motorOn = true;
            }
            if(turningOff(motorData[i], motorOn)) {
                endTime = i-1;
                motorOn = false;
                avgCurrent = calcAvgCurrent(motorData, startTime, endTime);
                currentExceeded = checkCurrentExceeded(motorData, startTime, endTime);
                addToReport(report, motor, startTime, endTime, avgCurrent, currentExceeded);
            }
        }
    }

    /**
     * Checks if a motor is turning on (if it was off and now is above 1 amp).
     * @param current current of the motor (in amps)
     * @param motorOn status of the motor
     * @return true if turning on, false if not
     */
    private static boolean turningOn(double current, boolean motorOn) {
        return (current > 1 && !motorOn);
    }

    /**
     * Checks if motor is turning off (if it was on and now is below 1 amp).
     * @param current current of the motor (in amps)
     * @param motorOn status of the motor
     * @return true if turning off, false if not
     */
    private static boolean turningOff(double current, boolean motorOn) {
        return (current <= 1 && motorOn);
    }

    /**
     * Looks at data between start and end times and checks if current is ever above 8 amps.
     * @param motorData pointer to array containing data of one motor
     * @param start start time for the activity of the motor
     * @param end end time for the activity of the motor
     * @return true if current was exceeded, false if not
     */
    private static boolean checkCurrentExceeded(double[] motorData, int start, int end) {
        for (int i = start; i < end; i++)
            if(motorData[i] > 8)
                return true;
        return false;
    }

    /**
     * Calculates the average current between the start and end times.
     * @param motorData pointer to array containing data of one motor
     * @param start start time for activity of the motor
     * @param end end time for the activity of the motor
     * @return average current over activity period
     */
    private static double calcAvgCurrent(double[] motorData, int start, int end) {
        double sum = 0;
        for(int i = start; i <= end; i++) {
            sum += motorData[i];
        }
        return (sum / (end  - start + 1));
    }

    /**
     * Adds the statistics from the analyzed data to the relevant report.
     * @param report string array of reports
     * @param motor column index of motor (motor # - 1)
     * @param start start time for activity of the motor
     * @param end end time for the activity of the motor
     * @param current average current over the activity period
     * @param currentExceeded true if the current threshold was exceeded
     */
    private static void addToReport(String[] report, int motor, int start, int end, double current, boolean currentExceeded) {
        report[motor] += (start + ", " + end + ", " + String.format("%.3f", current));
        if(currentExceeded)
            report[motor] += (", ***Current Exceeded***\r\n");
        else
            report[motor] += ("\r\n");
    }

    /**
     * Checks if a motor is used.
     * @param motor column index of motor
     * @param report string array of reports
     */
    private static void isUsed(int motor, String[] report) {
        if(report[motor].equals("start (sec), finish (sec), current (amps)\r\n"))
            report[motor] = "Not used.\r\n";
    }

    /**
     * Writes the reports to csv files.
     * @param report string array of reports
     */
    private static void writeReport(String[] report) {
        for(int motor = 0; motor < NUM_MOTORS; motor++) {
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("Motor" + (motor+1) + ".csv"))) {
                bw.write(report[motor]);
            }
            catch(IOException e) {
                System.err.println(e.getMessage());
                System.exit((1));
            }
        }
    }
}