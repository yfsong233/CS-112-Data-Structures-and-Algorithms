/*
 * Example of a Roman Numeral Static class
 *
 * CS112
 *
 * Christine Papadakis-Kanaris
 *
 */

public class RomanNumeralStatic {

    /* Method to convert a single roman numeral
     * character into its corresponding decimal value.
     */
    private static int convertSingleRomanNumeral(char numeral) {    
        int val = 0;       // a private method: prevent the client from tampering with the numerals

        if (numeral == 'I') {
            val = 1;
        } else if (numeral == 'V') {
            val = 5;
        } else if (numeral == 'X') {
            val = 10;
        } else if (numeral == 'L') {
            val = 50;
        } else if (numeral == 'C') {
            val = 100;
        } else if (numeral == 'D') {
            val = 500;
        } else if (numeral == 'M') {
            val = 1000;
        }

        return  val;
    }

    /* Method to convert a roman numeral
     * string into its corresponding decimal value.
     */
    public static int convert(String romanNum) {

        int numericalValue = 0, n = 0, n1 = 0;

        for ( int i = 0; i < romanNum.length() - 1; i++ ) {
            /*
             * Convert each adjacent pair of symbols of the string
             * (which is a roman numeral symbol) into its decimal
             * equivalent.
             */
            n = convertSingleRomanNumeral(romanNum.charAt(i));
            n1 = convertSingleRomanNumeral(romanNum.charAt(i+1));

            /* 
             * Apply the rules to accumulate the values
             * of each symbol into the correct decimal
             * equivalent.
             */
            if ( n < n1 ) {
                numericalValue -= n;
            } else {
                numericalValue += n;
            }
        }

        // Add in the value of the last roman numeral symbol.
        // Note, if n1 is 0, the roman numeral is just one symbol.
        if ( n1 == 0 )
            n1 = convertSingleRomanNumeral(romanNum.charAt(0));   // ???

        numericalValue += n1;

        return numericalValue;
    }

    /*
     * Method that accepts two strings representing roman
     * numerals and returns the decimal sum.
     */
    public static int add(String romanNum1, String romanNum2) {
        return convert(romanNum1) + convert(romanNum2);
    }

    /* 
     * Local main method to test the methods of our class
     */
    public static void main(String[] args) {

        System.out.println("Testing convert function:");
        System.out.printf("\t%s converts to %d\n", "X", convert("X"));
        System.out.printf("\t%s converts to %d\n", "LXXXXIX", convert("LXXXXIX"));
        System.out.printf("\t%s converts to %d\n", "CDV", convert("CDV"));
        System.out.printf("\t%s converts to %d\n", "XXVVXCCIIXXV", convert("XXVVXCCIIXXV"));

        System.out.println("Testing add function:");
        System.out.printf("\tadd(%s, %s) = %d\n", "X", "X", add("X", "X"));
        System.out.printf("\tadd(%s, %s) = %d\n", "XI", "CDV", add("XI", "CDV"));
        System.out.printf("\tadd(%s, %s) = %d\n", "LXXXXIX", "I", add("LXXXXIX", "I"));
    }

} // end of class definition
