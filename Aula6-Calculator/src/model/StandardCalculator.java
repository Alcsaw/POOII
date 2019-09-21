package model;

/**
 *
 * @author m98567
 * @github Alcsaw
 */
import java.util.ArrayList;

/**
 * Model for Standard mode of the Calculator program.
 * This class handles basic arithmetic operations (+, -, *, /).
 */
public class StandardCalculator {

    private double result;
    private final double precision = 0.0001;
    //Numbers this close to zero are treated as if equal to zero.

    
    public static void main(String[] args) throws Exception {
        StandardCalculator stdCalc = new StandardCalculator();
    }

    
    public StandardCalculator() {
        result = 0;
    }


    /**
     * The core of a calculator. Input errors
     * throws ArithmeticException, OperationFormatException.
     * @param operations - ArrayList of operations
     * @return Double - the result of the calculation
     * @throws java.lang.ArithmeticException
     * @throws model.StandardCalculator.OperationFormatException
     */
    public double doCalculation(ArrayList<String> operations)
            throws ArithmeticException, OperationFormatException {
        
        ArrayList<String> formattedOperations = handleOperationsArray(operations);
        
        while (formattedOperations.size() > 1) {
            
            double n1 = Double.parseDouble(formattedOperations.get(0));
            String op = formattedOperations.get(1);
            double n2 = Double.parseDouble(formattedOperations.get(2));
            
            formattedOperations.remove(0);
            formattedOperations.remove(0);
            formattedOperations.remove(0);
            
            formattedOperations.add(0, Double.toString(evaluate(op, n1, n2)));
        }
        
        return Double.parseDouble(formattedOperations.get(0));
    }
    
    
    /**
     * Organizes the numbers, grouping them into unique indexes so when necessary.
     * Also, it checks if every operation mark is between 2 numbers.
     * @param operations - ArrayList of operations
     * @throws java.lang.ArithmeticException
     * @throws model.Calculator.OperationFormatException
    */
    private ArrayList<String> handleOperationsArray(ArrayList<String> operations)
            throws OperationFormatException {
        
        if (operations.isEmpty())
            throw new OperationFormatException(operations, "Operations list is empty.");
        
        ArrayList<String> formattedOperations = new ArrayList<>();
        
        String number = "";
        String operator;
        
        for (String digit : operations) {
            if (digit.equals("+") ||
                digit.equals("-") ||
                digit.equals("*") ||
                digit.equals("/"))
            {
                operator = digit;
                
                if (number.length() == 0)
                    throw new OperationFormatException(operations,
                            "Operation starts with an operator. Must be a number!");
                if (! operations.iterator().hasNext())
                    throw new OperationFormatException(operations,
                            "There must be a number after the operator!");
                
                formattedOperations.add(number);
                formattedOperations.add(operator);
                number = "";
            } else {
                number += digit;
            }
        }
        
        formattedOperations.add(number);
        
        return formattedOperations;
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
