/*
 * StringRecursion.java - ps5, pr6 (Recursion and strings)
 * Write methods that recursively process strings
 * 
 * The use of iteration (i.e., for, while, or do-while loops) is not allowed.
 * The only built-in String methods to use are charAt, length, equals, and substring. 
 * Do not use any global variables — i.e., variables that are declared outside of a method.
 * Do not write any additional “helper” methods that assist the required methods.
 * 
 * Completed by Yufeng Song (jyfsong@bu.edu)
 */

public class StringRecursion {

    /* 
     * printWithSpaces() prints the individual characters in the string str, separated by spaces.
     * 
     * The last printed character should be followed by a space.
     * Special cases: If the value null or the empty string ("") are passed in as the parameter, 
     *                the method should just print a newline and return.
     */
    public static void printWithSpaces(String str) { 
        if (str == null || str.length() == 0) {
            System.out.println();
            return;
        }
        System.out.print(str.charAt(0) + " ");
        printWithSpaces(str.substring(1));
    }

    /*
     * reflect() takes a string str and uses recursion 
     * to create and return a “reflected” version of the string 
     * in which the original string is followed by the characters of the string in reverse order.
     * 
     * Special cases: If the value null or the empty string ("") are passed in as the parameter, 
     *                the method should just return an empty string.
     */
    public static String reflect(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        String reflected = "";
        if (str.length() == 1) {
            reflected += str + str;
            return reflected;
        } else {
            String reflectRest = reflect(str.substring(1));
            return str.charAt(0) + reflectRest + str.charAt(0);
        }
    }

    /*
     * numDiff() takes two strings str1 and str2 and uses recursion
     * to determine and return the number of differences between the two strings
     * — i.e., the number of positions at which the two strings have different characters.
     * If one string is longer than the other, all of its extra characters should count as differences.
     * 
     * Assume neither parameter is null.
     */
    public static int numDiff(String str1, String str2) {
        if (str1.length() == 0 || str2.length() == 0) {
            return str1.length() + str2.length();
        } else {
            int diffRest = numDiff(str1.substring(1), str2.substring(1));
            if (str1.charAt(0) != str2.charAt(0)) {
                return 1 + diffRest;
            } return diffRest;
        }
    }

    /* 
     * indexOf() uses recursion to find and return 
     * the index of the first occurrence of the character ch in the string str, 
     * or -1 if ch does not occur in str.
     * 
     * Special cases: If the empty string ("") or the value null is passed in as the second parameter,
     *                the method should return -1.
     */
    public static int indexOf(char ch, String str) {
        if (str == null || str.length() == 0) {
            return -1;
        } else if (str.charAt(0) == ch) {
            return 0;
        } else {
            int indexRest = indexOf(ch, str.substring(1));
            if (indexRest == -1) {   // concrete cases matter!
                return -1;
            } return 1 + indexRest;
        }
    }

    public static void main(String[] args) {
        // /* 1. Testing printWithSpaces() */
        // String strSpace = "space";
        // String str = "SchoolDayGoodbye";
        // printWithSpaces(strSpace); // s p a c e 
        // printWithSpaces(str);  // S c h o o l D a y G o o d b y e 
        // printWithSpaces("t");  // t 
        // printWithSpaces("");  // print a newline

        // /* 2. Testing reflect() */
        // String str2 = "method";
        // String str3 = "aBc";  
        // String str4 = "S";
        // System.out.println(reflect(str2).equals("methoddohtem"));  // true
        // System.out.println(reflect(str3).equals("aBccBa"));  // true
        // System.out.println(reflect(str4).equals("SS"));  // true
        // System.out.println(reflect(""));  // (an empty String)

        // /* 3. Testing numDiff() */
        // System.out.println(numDiff("alien", "allen") == 1);
        // System.out.println(numDiff("alien", "alone") == 3);
        // System.out.println(numDiff("same", "same") == 0);
        // System.out.println(numDiff("same", "sameness") == 4);
        // System.out.println(numDiff("some", "sameness") == 5);
        // System.out.println(numDiff("", "abc") == 3);
        // System.out.println(numDiff("abc", "") == 3);

        // /* 4. Testing indexOf() */
        // System.out.println(indexOf('b', "Rabbit") == 2);
        // System.out.println(indexOf('P', "Rabbit") == -1);
        // System.out.println(indexOf('b', "RaBBit") == -1);
        // System.out.println(indexOf('r', "guitar") == 5);
        // System.out.println(indexOf('r', "Happy Birthday!") == 8);
    }
}
