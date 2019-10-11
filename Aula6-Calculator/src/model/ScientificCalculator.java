package model;

/**
 *
 * @author m98567
 * @github Alcsaw
 */
import java.util.ArrayList;

/**
 * Model for Scientific mode of the Calculator program.
 * This class handles more complex arithmetic operations to calculate
 * science, engineering, and mathematics problems.
 */
public class ScientificCalculator {

    private double result;
    private final double precision = 0.0001;
    //Numbers this close to zero are treated as if equal to zero.

    
    public static void main(String[] args) throws Exception {
        ScientificCalculator sciCalc = new ScientificCalculator();
    }

    
    public ScientificCalculator() {
        result = 0;
    }


    /**
     * The core of a calculator. Input errors
     * throws ArithmeticException, OperationFormatException.
     * @param operation - String - The operation to perfom over the inputed number
     * @param numbers - ArrayList of the numbers that form the base for the given operation
     * @return Double - the result of the calculation
     * @throws model.ScientificCalculator.OperationFormatException if numbers can't be read
     */
    public double doCalculation(String operation, ArrayList<String> numbers)
            throws UnsupportedOperationException, OperationFormatException {
        
        String formattedNumber = handleNumbersArray(numbers);
        double number = 0;
        
        try {
            number = Double.parseDouble(formattedNumber);
        } catch (NumberFormatException notaNumber) {
            System.out.println("Could not convert the input to Double. " + number);
        }
        
        switch (operation) {
            case "sqrt":
                System.out.println("sqrt");
                return Math.sqrt(number);
            case "1/x":
                return eval1_x(number);
            case "sin":
                return Math.sin(number);
            case "%":
                return evalPercent(number);
            case "exp":
                return Math.exp(number);
            case "cos":
                return Math.cos(number);
            case "x^3":
                return Math.pow(number, 3);
            case "ln":
                return Math.log1p(number);
            case "tan":
                return Math.tan(number);
            case "x^2":
                return Math.pow(number, 2);
            case "n!":
                return evalFactorial(number);
            case "sec":
                return evalSec(number);
            default:
                throw new UnsupportedOperationException
                            ("The operation " + operation + "is not valid");
        }
    }
    
    
    /**
     * Organizes the numbers, grouping them into unique indexes so when necessary.
     * Also, it checks if every operation mark is between 2 numbers.
     * @param numbers - ArrayList of operations
     * @throws java.lang.ArithmeticException
     * @throws model.Calculator.OperationFormatException
    */
    private String handleNumbersArray(ArrayList<String> numbers)
            throws OperationFormatException {
        
        //TODO: Add call to stdCalc to allow basic operations to be performed before scientific
        if (numbers.isEmpty())
            throw new OperationFormatException(numbers, "Numbers list is empty.");
        
        StringBuilder formattedNumber = new StringBuilder();
        
        //String number = "";
        
        for (String number : numbers) {
            try {
                Double.parseDouble(number);
            } catch (NumberFormatException notANumber) {
                if (! number.equals("."))
                    System.out.println("ERROR: Could not recognize the number");
                //TODO: reset calculator state
            }
            formattedNumber.append(number);
        }
        return formattedNumber.toString();
    }
    
    
    private double eval1_x(double digit) {
        return 1/digit;
    }
    
    
    private double evalPercent(double digit) {
        return digit/100;
    }
    
    
    private double evalFactorial(double digit) {
        //TODO  
        return digit;
    }
    
    
    private double evalSec(double digit) {
        //TODO
        return digit;
    }
    

    /**
     * Returns n1 op n2, provided op is one of '+', '-', '*',or '/'. Any other
     * value of op throws ArithmeticException, UnsupportedOperationException.
     * @param op
     * @param n1
     * @param n2
     * @return the result of the given operation between n1 and n2
     */
    public double evaluate(String op, double n1, double n2)
            throws ArithmeticException, UnsupportedOperationException {
        double operationResult;
        switch (op) {
            case "+":
                operationResult = n1 + n2;
                break;
            case "-":
                operationResult = n1 - n2;
                break;
            case "*":
                operationResult = n1 * n2;
                break;
            case "/":
                if ((-precision < n2) && (n2 < precision)) {
                    throw new ArithmeticException();
                }
                operationResult = n1 / n2;
                break;
            default:
                throw new UnsupportedOperationException
                            ("The operation " + op + "is not valid");
        }
        return operationResult;
    }

    
    public class OperationFormatException extends Exception {
 
        public OperationFormatException () {
            super("ERROR: The entered operation is not in a valid format.\n"
                    + "There must be a number in each side of an operator,"
                    + "i.e: n1 op n2.\n e.g. 2*2\n");
        }

        private OperationFormatException(ArrayList<String> operations, String cause) {
            super("ERROR: The entered operation is not in a valid format.\n"
                    + "There must be a number in each side of an operator,"
                    + "i.e: n1 op n2.\n e.g. 2*2\n"
                    + "Operation: " + operations + "\n"
                    + "Cause:\n" + cause);
        }

    }
}
