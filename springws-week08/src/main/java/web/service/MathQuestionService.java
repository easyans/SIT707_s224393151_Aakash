package web.service;

public class MathQuestionService {

    /**
     * Calculate Q1 result (Addition).
     * Returns Double.NaN if inputs are invalid or empty.
     */
    public static double q1Addition(String number1, String number2) {
        if (number1 == null || number1.trim().isEmpty() ||
            number2 == null || number2.trim().isEmpty()) {
            return Double.NaN;
        }
        try {
            double result = Double.valueOf(number1) + Double.valueOf(number2);
            return result;
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    /**
     * Calculate Q2 result (Subtraction).
     * Returns Double.NaN if inputs are invalid or empty.
     */
    public static double q2Subtraction(String number1, String number2) {
        if (number1 == null || number1.trim().isEmpty() ||
            number2 == null || number2.trim().isEmpty()) {
            return Double.NaN;
        }
        try {
            double result = Double.valueOf(number1) - Double.valueOf(number2);
            return result;
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    /**
     * Calculate Q3 result (Multiplication).
     * Returns Double.NaN if inputs are invalid or empty.
     */
    public static double q3Multiplication(String number1, String number2) {
        if (number1 == null || number1.trim().isEmpty() ||
            number2 == null || number2.trim().isEmpty()) {
            return Double.NaN;
        }
        try {
            double result = Double.valueOf(number1) * Double.valueOf(number2);
            return result;
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }
}