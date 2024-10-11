/*
 * Lab 10, Task 2
 * CS 112
 */

public class Lab10Task2 {
    /*
     * isLeftDelim - takes a single character ch and returns true
     * if it is a left delimiter -- a (, [, or { -- and false otherwise.
     */
    public static boolean isLeftDelim(char ch) {
        return (ch == '(' || ch == '[' || ch == '{');
    }
    
    /*
     * isRightDelim - takes a single character ch and returns true
     * if it is a right delimiter -- a ), ], or } -- and false otherwise.
     */
    public static boolean isRightDelim(char ch) {
        return (ch == ')' || ch == ']' || ch == '}');
    }
    
    /*
     * matches - takes two characters left and right, and checks to see 
     * if they are matching left and right delimiters, returning true
     * if they are, and false if they are not.
     */
    public static boolean matches(char left, char right) {
        if (left == '(' && right == ')') {
            return true;
        } else if (left == '[' && right == ']') {
            return true;
        } else if (left == '{' && right == '}') {
            return true;
        } else {
            return false;
        }
    }

    /*
     * isBalanced - takes a String expr that represents a mathematical 
     * expression and determines if its delimiters are properly balanced, 
     * returning true if they are and false otherwise.
     */
    public static boolean isBalanced(String expr) {
        Stack<Character> stack = new LLStack<Character>();  // lab10 2-2
        
        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            if (isLeftDelim(ch)) {
                stack.push(ch);
            } else if (isRightDelim(ch)) {
                if (stack.isEmpty()) {
                    return false;
                } 
                char top = stack.pop();
                if (! matches(top, ch)) {
                    return false;
                }
            }
        }
        
        // At the end of processing the expression, the stack will
        // be empty if and only if the delimiters were balanced.
        
        if (stack.isEmpty()) {
            return true;
        } return false;

    }

    /**
     * lab10 2-2
     * The error stems from the fact that 
     * generic classes in Java require you to use reference types
     * – types that represent objects. 
     * Therefore, we can’t use a primitive type like char when we declare or create an instance of a generic collection.
     * Instead, Java provides a “wrapper class” for each primitive type 
     * that allows us to treat primitive values as if they were objects. 
     * In the case of char, the corresponding wrapper class is called Character.
    */

    public static void main(String[] args) {
        
        // /* Test on isLeftDelim && isRightDelim */
        // System.out.println(Lab10Task2.isLeftDelim('('));  // true
        // System.out.println(Lab10Task2.isLeftDelim('{'));  // true
        // System.out.println(Lab10Task2.isLeftDelim(']'));  // false
        // System.out.println(Lab10Task2.isRightDelim(']'));  // true
        // System.out.println(Lab10Task2.isRightDelim('}'));  // true
        // System.out.println(Lab10Task2.isRightDelim('('));  // false
        // System.out.println(Lab10Task2.matches('[', ']'));  // true
        // System.out.println(Lab10Task2.matches('{', '}'));  // true
        // System.out.println(Lab10Task2.matches('(', '}'));  // false

        /* Test on isBalanced */
        System.out.println( Lab10Task2.isBalanced("[(3 + 2) * 7] / 2") );  // true

        System.out.println( Lab10Task2.isBalanced("[(3 + 2] * 7) / 2") );  // false

        System.out.println( Lab10Task2.isBalanced("[(({}))]") );  // true

        System.out.println( Lab10Task2.isBalanced("[(({))]}") );  // false

        System.out.println( Lab10Task2.isBalanced("((((())") );  // false

        System.out.println( Lab10Task2.isBalanced("(())))") );  // false
    } 
}
        
