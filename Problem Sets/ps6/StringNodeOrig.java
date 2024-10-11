/*
 * StringNodeOrig.java
 *
 * Computer Science 112
 * 
 * **** Do NOT put your work in this file. ****
 * Rather, all of your work should go in StringNode.java.
 * 
 * This is an extra copy of the original version of the class, 
 * so that you can compare the behavior of the original methods 
 * to the behavior of your revised methods.
 */

import java.io.*;
import java.util.*;

/**
 * A class for representing a string using a linked list.
 * Each character of the string is stored in a separate node.  
 *
 * This class represents one node of the linked list.  The string as a
 * whole is represented by storing a reference to the first node in
 * the linked list. The methods in this class are static methods that
 * take a reference to a string linked-list as a parameter. This
 * approach allows us to use recursion to write many of the methods,
 * and it also allows the methods to handle empty strings, which are 
 * represented using a value of null.
 */
public class StringNodeOrig {
    private char ch;
    private StringNodeOrig next;

    /**
     * Constructor
     */
    public StringNodeOrig(char c, StringNodeOrig n) {
        this.ch = c;
        this.next = n;
    }

    /**
     * getNode - private helper method that returns a reference to
     * node i in the given linked-list string.  If the string is too
     * short or if the user passes in a negative i, the method returns null.
     */
    private static StringNodeOrig getNode(StringNodeOrig str, int i) {
        if (i < 0 || str == null) {    // base case 1: not found
            return null;
        } else if (i == 0) {           // base case 2: just found
            return str;
        } else {
            return getNode(str.next, i - 1);
        }
    }

    /*****************************************************
     * Public methods (in alphabetical order)
     *****************************************************/

    /**
     * charAt - returns the character at the specified index of the
     * specified linked-list string, where the first character has
     * index 0.  If the index i is < 0 or i > length - 1, the method
     * will end up throwing an IllegalArgumentException.
     */
    public static char charAt(StringNodeOrig str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("the string is empty");
        } 
          
        StringNodeOrig node = getNode(str, i);

        if (node != null) {
            return node.ch;     
        } else {
            throw new IllegalArgumentException("invalid index: " + i);
        }
    }

    /**
     * convert - converts a standard Java String object to a linked-list
     * string and returns a reference to the linked-list string
     */
    public static StringNodeOrig convert(String s) {
        if (s.length() == 0) {
            return null;
        }

        StringNodeOrig firstNode = new StringNodeOrig(s.charAt(0), null);
        StringNodeOrig prevNode = firstNode;
        StringNodeOrig nextNode;

        for (int i = 1; i < s.length(); i++) {
            nextNode = new StringNodeOrig(s.charAt(i), null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }

        return firstNode;
    }
    
    /**
     * copy - returns a copy of the given linked-list string
     */
    public static StringNodeOrig copy(StringNodeOrig str) {
        if (str == null) {
            return null;
        }

        // make a recursive call to copy the rest of the list
        StringNodeOrig copyRest = copy(str.next);
           
        // create and return a copy of the first node, 
        // with its next field pointing to the copy of the rest
        return new StringNodeOrig(str.ch, copyRest);
    }

    /**
     * deleteChar - deletes character i in the given linked-list string and
     * returns a reference to the resulting linked-list string
     */
    public static StringNodeOrig deleteChar(StringNodeOrig str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("string is empty");
        } else if (i < 0) { 
            throw new IllegalArgumentException("invalid index: " + i);
        } else if (i == 0) { 
            str = str.next;
        } else {
            StringNodeOrig prevNode = getNode(str, i-1);
            if (prevNode != null && prevNode.next != null) {
                prevNode.next = prevNode.next.next;
            } else {
                throw new IllegalArgumentException("invalid index: " + i);
            }
        }

        return str;
    }

    /**
     * insertChar - inserts the character ch before the character
     * currently in position i of the specified linked-list string.
     * Returns a reference to the resulting linked-list string.
     */
    public static StringNodeOrig insertChar(StringNodeOrig str, int i, char ch) {
        StringNodeOrig newNode, prevNode;

        if (i < 0) { 
            throw new IllegalArgumentException("invalid index: " + i);
        } else if (i == 0) {
            newNode = new StringNodeOrig(ch, str);
            str = newNode;
        } else {
            prevNode = getNode(str, i - 1);
            if (prevNode != null) {
                newNode = new StringNodeOrig(ch, prevNode.next);
                prevNode.next = newNode;
            } else {
                throw new IllegalArgumentException("invalid index: " + i);
            }
        }

        return str;
    }

    /**
     * insertSorted - inserts character ch in the correct position
     * in a sorted list of characters (i.e., a sorted linked-list string)
     * and returns a reference to the resulting list.
     */
    public static StringNodeOrig insertSorted(StringNodeOrig str, char ch) {
        StringNodeOrig newNode, trail, trav;

        // Find where the character belongs.
        trail = null;
        trav = str;
        while (trav != null && trav.ch < ch) {
            trail = trav;
            trav = trav.next;
        }

        // Create and insert the new node.
        newNode = new StringNodeOrig(ch, trav);
        if (trail == null) {
            // We never advanced the prev and trav references, so
            // newNode goes at the start of the list.
            str = newNode;
        } else { 
            trail.next = newNode;
        }
            
        return str;
    }

    /**
     * length - recursively determines the number of characters in the
     * linked-list string to which str refers
     */
    public static int length(StringNodeOrig str) {
        if (str == null) {
            return  0;
        } else {
            return 1 + length(str.next);
        }
    }

    /**
     * numOccur - find the number of occurrences of the character
     * ch in the linked list to which str refers
     */
    public static int numOccur(StringNodeOrig str, char ch) {
        if (str == null) {
            return 0;
        }
     
        int numInRest = numOccur(str.next, ch);
        if (str.ch == ch) {
            return 1 + numInRest;
        } else {
            return numInRest;
        }
    }

    /**
     * print - recursively writes the specified linked-list string to
     * System.out
     */
    public static void print(StringNodeOrig str) {
        if (str == null) {
            return;
        } else {
            System.out.print(str.ch);
            print(str.next);
        }
    }

    /**
     * printReverse - recursively writes the reverse of the specified 
     * linked-list string to System.out
     */
    public static void printReverse(StringNodeOrig str) {
        if (str == null) {
            return;
        } else {
            printReverse(str.next);
            System.out.print(str.ch);
        }
    }
    
    /**
     * read - reads a string from an input stream and returns a
     * reference to a linked list containing the characters in the string
     */
    public static StringNodeOrig read(InputStream in) throws IOException { 
        char ch = (char)in.read();

        if (ch == '\n') {    // string ends when we hit a newline character
            return null;         
        } else {
            StringNodeOrig restOfString = read(in);
            StringNodeOrig first = new StringNodeOrig(ch, restOfString);
            return first;
        }
    }
    
    /**
     * removeFirst - takes the linked-list string specified by str and
     * removes the first occurrence (if any) of the character ch in
     * that string.
     */
    public static StringNodeOrig removeFirst(StringNodeOrig str, char ch) { 
        StringNodeOrig trav = str;
        StringNodeOrig trail = null;  // "trailing" ref; stays one behind trav
        
        while (trav != null && trav.ch != ch) {
            trail = trav;
            trav = trav.next;
        }
                
        if (trav == null) {
            // If trav if null, that means ch was not found. 
            // We simply return str, since the linked list was unchanged.
            return str;
        } else if (trail == null) {
            // If trav is not null and trail is still null, that means the 
            // first occurrence of ch is in the first node. Because of this,  
            // we return a reference to the second node, because it is now the 
            // new first node in the linked list.
            return str.next;
        } else {
            // Remove the node containing ch by updating the previous node.
            trail.next = trav.next;
            
            // The original first node is still the first node,
            // so we just return str.
            return str;
        }
    }
    
    /**
     * toString - creates and returns the Java string that
     * the current StringNodeOrig represents.  Note that this
     * method -- unlike the others -- is a non-static method.
     * Thus, it will not work for empty strings, since they
     * are represented by a value of null, and we can't use
     * null to invoke this method.
     */
    public String toString() {
        String str = "";
        
        StringNodeOrig trav = this;   // start trav on the current node    
        while (trav != null) {
            str = str + trav.ch;
            trav = trav.next;
        }
         
        return str;
    }
    
    /**
     * toUpperCase - converts all of the characters in the specified
     * linked-list string to upper case.  Modifies the list itself,
     * rather than creating a new list.
     */
    public static void toUpperCase(StringNodeOrig str) {        
        StringNodeOrig trav = str; 
        while (trav != null) {
            trav.ch = Character.toUpperCase(trav.ch); 
            trav = trav.next;
        }
    } 
              
    public static void main(String[] args) throws IOException {
        StringNodeOrig copy, str, str1, str2;
        String line;
        int n;
        char ch;

        // convert, print, and toUpperCase
        str = StringNodeOrig.convert("fine");
        System.out.print("Here's a string: "); 
        StringNodeOrig.print(str);
        System.out.println();
        System.out.print("Here it is in upper-case letters: "); 
        StringNodeOrig.toUpperCase(str);
        StringNodeOrig.print(str);
        System.out.println();
        System.out.println();

        Scanner in = new Scanner(System.in);
        
        // read, toString, length, and printReverse.
        System.out.print("Type a string: ");
        String s = in.nextLine();
        str1 = StringNodeOrig.convert(s);
        System.out.print("Your string is: "); 
        System.out.println(str1);        // implicit toString call
        System.out.print("\nHere it is reversed: ");  
        StringNodeOrig.printReverse(str1);
        System.out.println("\nIts length is " + StringNodeOrig.length(str1) + 
            " characters.");

        // charAt
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to get (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        try {
            ch = StringNodeOrig.charAt(str1, n);
            System.out.println("That character is " + ch);
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }
           
        // deleteChar and copy
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to delete (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        copy = StringNodeOrig.copy(str1);
        try {
            str1 = StringNodeOrig.deleteChar(str1, n);
            StringNodeOrig.print(str1);
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }
        System.out.print("\nUnchanged copy: ");
        StringNodeOrig.print(copy);
        System.out.println();

        // insertChar
        n = -1;
        while (n < 0) {
            System.out.print("\nWhat # character to insert before (>= 0)? ");
            n = in.nextInt();
            in.nextLine();
        }
        System.out.print("What character to insert? ");
        line = in.nextLine();
        ch = line.charAt(0);
        try {
            str1 = StringNodeOrig.insertChar(str1, n, ch);
            StringNodeOrig.print(str1);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }
        
        // removeFirst
        System.out.print("\nWhat character to remove first occurrence of? ");
        line = in.nextLine();
        ch = line.charAt(0);
        try {
            str1 = StringNodeOrig.removeFirst(str1, ch);
            StringNodeOrig.print(str1);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("The string is too short.");
        }
        
        // insertSorted
        System.out.print("\nType a string of chars in alphabetical order: ");
        s = in.nextLine();
        str2 = StringNodeOrig.convert(s);
        System.out.print("What character to insert in order? ");
        line = in.nextLine();
        str2 = StringNodeOrig.insertSorted(str2, line.charAt(0));
        StringNodeOrig.print(str2);
        System.out.println();

        in.close();
    }
}
