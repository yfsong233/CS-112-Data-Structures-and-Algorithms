/*
 * DNode.java
 *
 * Computer Science 112
 */

/*
 * A class for representing a string using a doubly-linked list.
 * Each character of the string is stored in a separate node.  
 *
 * This class represents one node of the linked list.  The string as a
 * whole is represented by storing a reference to the first node in
 * the linked list. Empty strings are represented using a value of null.
 */ 
public class DNode {
    private char ch;
    private DNode prev;
    private DNode next;

    /*
     * Constructor
     */
    public DNode(char c, DNode p, DNode n) {
        this.ch = c;
        this.prev = p;
        this.next = n;
    }

    /*
     * convert - converts a standard Java String object to a doubly-linked
     * list and returns a reference to first node in that doubly-linked list
     */
    public static DNode convert(String s) {
        if (s.length() == 0) {
            return null;
        }

        DNode firstNode = new DNode(s.charAt(0), null, null);
        DNode prevNode = firstNode;
        DNode nextNode;

        for (int i = 1; i < s.length(); i++) {
            nextNode = new DNode(s.charAt(i), prevNode, null);
            prevNode.next = nextNode;
            prevNode = nextNode;
        }

        return firstNode;
    }

    /*
     * removeNexts - takes a reference to the first node in a
     * doubly-linked list, sets all of the next fields in the nodes
     * to null, and returns a reference to the last node in the
     * linked list.
     * 
     * This method can be used to create a scenario like the one envisioned
     * for the addNexts() method that you need to write for Problem 3-3.
     */
    public static DNode removeNexts(DNode first) {
        DNode trav = first;
        DNode trail = null;   // will stay one behind trav

        while (trav != null) {
            // the order of these statements matters!
            trail = trav;
            trav = trav.next;
            trail.next = null;
        }

        // at the end of the loop, trail will be pointing
        // to the last node
        return trail;
    }

    // problem 3-3
    public static void addNexts(DNode last) {
        // add your implementation of the method here
        last.next = null;
        DNode trav = last;
        while (trav.prev != null) {
            trav.prev.next = trav;
            trav = trav.prev;
        }
    }    

    /*
     * toString - creates and returns the Java string that
     * the current DNode represents.  Note that this
     * method is non-static method, and thus it won't work
     * for empty strings, since they are represented by a 
     * value of null, and we can't use null to invoke this method.
     */
    public String toString() {
        String str = "";
        
        DNode trav = this;   // start trav on the current node (this)
        while (trav != null) {
            str = str + trav.ch;
            trav = trav.next;
        }
         
        return str;
    }

    public static void main(String[] args) {
        // set up the initial diagram for problem 3
        DNode str = DNode.convert("set");
        DNode n = str.next;
        DNode m = new DNode('a', null, null);

        // 3-2
        System.out.println("before changes for 3-2: " + str);
        // put your answer for 3-2 here
        n.next.prev = m;
        m.next = n.next;
        n.next = m;
        m.prev = n;

        System.out.println("after changes for 3-2: " + str);

        // 3-3
        DNode str2 = DNode.convert("terriers");
        System.out.println("\ninitial str2: " + str2);
        DNode last = DNode.removeNexts(str2);
        System.out.println("after setting next fields to null: " + str2);
        DNode.addNexts(last);
        System.out.println("after calling addNexts(): " + str2);
    }
}
