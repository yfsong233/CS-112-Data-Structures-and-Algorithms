/*
 * Palindrome.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Yufeng Song
 *     username: jyfsong
 */
   
public class Palindrome {
    
    /**
     * isPal() takes a String object as a parameter 
     * and determines if it is a palindrome, 
     * returning true if it is and false if it is not.
     * 
     * A string of length 1 and an empty string should both be considered palindromes; 
     * Throw an exception for null values;
     * Spaces, punctuation, and the cases of the letters 
     * don’t prevent a string from being a palindrome.
     */
    public static boolean isPal(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (s.length() <= 1) {
            return true;
        }
        
        Stack<Character> stack = new LLStack<Character>();
        Queue<Character> queue = new LLQueue<Character>();

        for (int i = 0; i < s.length(); i++) {
            char element = s.charAt(i);
            if (Character.isAlphabetic(element)) {  // can we use this method?
                stack.push(Character.toLowerCase(element));
                queue.insert(Character.toLowerCase(element));
            }
        }

        while ((! stack.isEmpty()) && (! queue.isEmpty())) {
            if (stack.peek() != queue.peek()) {
                return false;
            }
            stack.pop();
            queue.remove();
        }

        return true;
    }
    

    /**
     * The output of each of the unit tests should include:
        -   a header that specifies the test number and a description of what is being tested
        -   the actual return value that you get from that test
        -   the expected return value
        -   whether the actual return value matches the expected return value.
     * Put each test in the context of a try-catch block 
     * so that you can handle any exceptions that are thrown. 
     * Print a blank line between tests.
    */
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */
        
        /* Test case 1 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(1) Testing on \"j!olyN    enY-l -o!J\"");
        try {
            boolean results = isPal("j!olyN    enY-l -o!J");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();

        /* Test case 2 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(2) Testing on \"the,dOgremo!vedev omerg-odeht\"");
        try {
            boolean results = isPal("the,dOgremo!vedev omerg-odeht");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
         
        System.out.println();

        /* Test case 3 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(3) Testing on \"summerv--Acatio//nwHenneh,wnoi.tacavrEmmus\"");
        try {
            boolean results = isPal("summerv--Acatio//nwHenneh,wnoi.tacavrEmmus");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
         
        System.out.println();

        /* Test case 4 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(4) Testing on \"nat/.ur!er!ut   an\"");
        try {
            boolean results = isPal("nat/.ur!er!ut   an");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
         
        System.out.println();

        /* Test case 5 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(5) Testing on \"nat/.tr!er!ut   an\"");
        try {
            boolean results = isPal("nat/.tr!er!ut   an");
            boolean expected = false;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
         
        System.out.println();

        /* Test case 6 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(6) Testing on \"the,dOgremo!vedes omerg-odeht\"");
        try {
            boolean results = isPal("the,dOgremo!vedes omerg-odeht");
            boolean expected = false;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }

        /* Test case 7 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(7) Testing on \"summerv--Bcatio//nwHenneh,wnoi.tacavrEmmus\"");
        try {
            boolean results = isPal("summerv--Bcatio//nwHenneh,wnoi.tacavrEmmus");
            boolean expected = false;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
         
        System.out.println();

        /* Test case 8 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(8) Testing on \"y)eA  hh(ae-Y\"");
        try {
            boolean results = isPal("y)eA  hh(ae-Y");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
         
        System.out.println();

        /* Test case 9 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(9) Testing on \"a\"");
        try {
            boolean results = isPal("a");  // one-character string
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
         
        System.out.println();

        /* Test case 10 */
        System.out.println("--- Testing method isPal ---");
        System.out.println();
        System.out.println("(10) Testing on \"% )\"");
        try {
            boolean results = isPal("% )");  // empty string
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
         
        System.out.println();

        
    }
}