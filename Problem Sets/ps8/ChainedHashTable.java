/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name: Yufeng Song
 *     email: jyfsong@bu.edu
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException();
        }
        table = new Node[maxSize];
        numKeys = 0;
    }
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     * 
     * There won't be overflow in a chained hash table.
     * Inserting a duplicate does not change the number of keys
     */
    public boolean insert(Object key, Object value) {
        int hashCode = h1(key);
        Node newNode = new Node(key, value);
        if (table[hashCode] == null) {
            table[hashCode] = newNode;
            numKeys++;
        } else {
            Node trav = table[hashCode];
            while (trav != null && !trav.key.equals(key)) {
                trav = trav.next;
            }
            if (trav == null) {  // key not found
                newNode.next = table[hashCode];
                table[hashCode] = newNode;
                numKeys++;
            } else {  // insert the values of a duplicate
                trav.values.insert(value);
            }

        }
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        int hashCode = h1(key);
        if (table[hashCode] == null) {  // key not found
            return null; 
        } else {
            Node trav = table[hashCode];
            while (trav != null && !trav.key.equals(key)) {
                trav = trav.next;
            }
            if (trav == null) {  // key not found
                return null;
            } 
            return trav.values;
        }
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        int hashCode = h1(key);
        if (table[hashCode] == null) {  // key not found
            return null;
        }
        
        Node trav = table[hashCode];
        Node prev = null;
        Queue<Object> toBeDeleted;

        while (trav != null && !trav.key.equals(key)) {
            prev = trav;
            trav = trav.next;
        }

        if (trav == null) {  // key not found
            return null;
        } else if (prev == null) {  // delete first node
            toBeDeleted = trav.values;
            table[hashCode] = trav.next;
            numKeys--;
        } else { // delete nodes other than the first one
            toBeDeleted = trav.values;
            prev.next = trav.next;
            numKeys--;
        }

        return toBeDeleted;
    }
    
    /*** Add the other required methods here ***/

    /* getNumKeys() - an accessor method for the number of keys */
    public int getNumKeys() {
        return numKeys;
    }

    /* 
     * load() - measures the dergree of fullness;
     * returns the load factor of the table:
     * the number of keys in the table / the size of the table
     */
    public double load() {
        return (double)numKeys/table.length;
    }

    /* 
     * getAllKeys() - obtains all of the keys in the hash table; 
     * returns an array of type Object containing all of the keys in the hash table
     */
    public Object[] getAllKeys() {
        Object[] keys = new Object[numKeys];
        int accessed = 0;
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Node trav = table[i];
                do {
                    keys[accessed] = trav.key;
                    trav = trav.next;
                    accessed++;
                } while (trav != null);
            }
        }

        return keys;
    }

    /*
     * resize() takes an integer representing the new size, 
     * and grows the table to have that new size.
     * 
     * Make sure to rehash the current keys;
     * throw an Exception if the specified new size < the table’s current size;
     * return without doing anything if if the specified new size == the table’s current size
     */
    public void resize(int newSize) {
        if (newSize < table.length) {
            throw new IllegalArgumentException();
        } else if (newSize == table.length) {
            return;
        } else {
            ChainedHashTable newTable = new ChainedHashTable(newSize);
            Object[] keys = getAllKeys();
            
            for (int i = 0; i < keys.length; i++) {
                newTable.insert(keys[i], search(keys[i]));
            }
    
            table = newTable.table;
        }
    }
    

    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        // ChainedHashTable testForCodes = new ChainedHashTable(5);
        // System.out.println("hash code for bubble is " + testForCodes.h1("bubble"));  // 4
        // System.out.println("hash code for shibainu is " + testForCodes.h1("shibainu"));  // 2
        // System.out.println("hash code for diamond is " + testForCodes.h1("diamond"));  // 1
        // System.out.println("hash code for healing is " + testForCodes.h1("healing"));  // 1
        // System.out.println("hash code for love is " + testForCodes.h1("love"));  // 3
        // System.out.println("hash code for manga is " + testForCodes.h1("manga"));  // 1
        // System.out.println("hash code for emerald is " + testForCodes.h1("emerald"));  // 3
        // System.out.println("hash code for fountain pen is " + testForCodes.h1("fountain pen"));  // 3
        // System.out.println("hash code for character is " + testForCodes.h1("character"));  // 0
        
        System.out.println("--- Testing the constructor and insert ---");
        System.out.println();
        System.out.println("(0) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            System.out.println(table.insert("apple", 5));  // true
            System.out.println(table);  // [{apple; howdy}, null, null, {goodbye}, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing the constructor and insert ---");
        System.out.println();
        System.out.println("(1) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            System.out.println(table.insert("apple", 5));  // true
            System.out.println(table.insert("howdy", 20));  // true (inserting a duplicate)
            System.out.println(table.insert("pear", 30));  // true
            System.out.println(table);  // [{apple; howdy}, null, null, {goodbye}, {pear}]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing the constructor and insert ---");
        System.out.println();
        System.out.println("(2) Testing on hash table: crazy diamond, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("shibainu", 20);
            table.insert("diamond", 40);
            System.out.println(table.insert("healing", 60));  // true
            System.out.println(table.insert("diamond", 80));  // true (inserting a duplicate)
            System.out.println(table.insert("love", 100));  // true
            System.out.println(table);  // [null, {healing; diamond}, {shibainu}, {love}, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing the constructor and insert ---");
        System.out.println();
        System.out.println("(3) Testing on hash table: heaven's door, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("love", 10);
            table.insert("manga", 30);
            System.out.println(table.insert("emerald", 50));  // true
            System.out.println(table.insert("fountain pen", 70));  // true
            System.out.println(table.insert("character", 90));  // true
            table.insert("manga", 110);  // inserting a duplicate
            System.out.println(table);  // [{character}, {manga}, null, {fountain pen; emerald; love}, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        // System.out.println("--- Testing the constructor ---");
        // System.out.println();
        // System.out.println("(4) Testing on hash table of negative size, ...");
        // try {
        //     ChainedHashTable table = new ChainedHashTable(-5);  // IllegalArgumentException
        //     table.insert("love", 10);
        //     System.out.println(table.insert("emerald", 50));
        //     System.out.println(table);

        // } catch (Exception e) {
        //     System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        // }                 
        // System.out.println();
    
        System.out.println("--- Testing search ---");
        System.out.println();
        System.out.println("(0) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            System.out.println(table.search("howdy"));  // {15}
            System.out.println(table.search("goodbye"));  // {10}
            System.out.println(table.search("apple"));  // null
            System.out.println(table);  // [{howdy}, null, null, {goodbye}, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing search ---");
        System.out.println();
        System.out.println("(1) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5); 
            table.insert("howdy", 20);  // (inserting a duplicate)
            table.insert("pear", 30); 
            System.out.println(table.search("howdy"));  // {15, 20}
            System.out.println(table.search("goodbye"));  // {10}
            System.out.println(table.search("apple"));  // {5}
            System.out.println(table.search("watermelon"));  // null
            System.out.println(table.search("pear"));  // {30}
            System.out.println(table);  // [{apple; howdy}, null, null, {goodbye}, {pear}]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing search ---");
        System.out.println();
        System.out.println("(2) Testing on hash table: crazy diamond, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("shibainu", 20);
            table.insert("diamond", 40);
            table.insert("healing", 60);
            table.insert("diamond", 80);
            table.insert("love", 100);
            System.out.println(table.search("shibainu"));  // {20}
            System.out.println(table.search("diamond"));  // {40, 80}
            System.out.println(table.search("josuke"));  // null
            System.out.println(table.search("healing"));  // {60}
            System.out.println(table.search("love"));  // {100}
            System.out.println(table);  // [null, {healing; diamond}, {shibainu}, {love}, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing search ---");
        System.out.println();
        System.out.println("(3) Testing on hash table: heaven's door, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("love", 10);
            table.insert("manga", 30);
            table.insert("emerald", 50);
            table.insert("fountain pen", 70);
            table.insert("character", 90);
            table.insert("manga", 110);
            System.out.println(table.search("love"));  // {10}
            System.out.println(table.search("rohan"));  // null
            System.out.println(table.search("manga"));  // {30, 110}
            System.out.println(table.search("emerald"));  // {50}
            System.out.println(table.search("fountain pen"));  // {70}
            System.out.println(table.search("character"));  // {90}
            System.out.println(table);  // [{character}, {manga}, null, {fountain pen; emerald; love}, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing remove ---");
        System.out.println();
        System.out.println("(0) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            System.out.println(table);  // [{howdy}, null, null, {goodbye}, null]
            System.out.println(table.remove("howdy"));  // {15}
            System.out.println(table.remove("goodbye"));  // {10}
            System.out.println(table.remove("apple") == null);  // null
            System.out.println(table);  // [null, null, null, null, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing remove ---");
        System.out.println();
        System.out.println("(1) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5); 
            table.insert("howdy", 20);  // (inserting a duplicate)
            table.insert("pear", 30);
            System.out.println(table);  // [{apple; howdy}, null, null, {goodbye}, {pear}]
            System.out.println(table.remove("howdy"));  // {15, 20}
            System.out.println(table.remove("goodbye"));  // {10}
            System.out.println(table.remove("apple"));  // {5}
            System.out.println(table.remove("watermelon") == null);  // true
            System.out.println(table.remove("pear"));  // {30}
            System.out.println(table);  // [null, null, null, null, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing remove ---");
        System.out.println();
        System.out.println("(2) Testing on hash table: crazy diamond, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("shibainu", 20);
            table.insert("diamond", 40);
            table.insert("healing", 60);
            table.insert("diamond", 80);
            table.insert("love", 100);
            System.out.println(table);  // [null, {healing; diamond}, {shibainu}, {love}, null]
            System.out.println(table.remove("shibainu"));  // {20}
            System.out.println(table.remove("diamond"));  // {40, 80}
            System.out.println(table.remove("josuke") == null);  // true
            System.out.println(table.remove("healing"));  // {60}
            System.out.println(table.remove("love"));  // {100}
            System.out.println(table);  // [null, null, null, null, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing remove ---");
        System.out.println();
        System.out.println("(3) Testing on hash table: heaven's door, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("love", 10);
            table.insert("manga", 30);
            table.insert("emerald", 50);
            table.insert("fountain pen", 70);
            table.insert("character", 90);
            table.insert("manga", 110);
            System.out.println(table);  // [{character}, {manga}, null, {fountain pen; emerald; love}, null]
            System.out.println(table.remove("love"));  // {10}
            System.out.println(table.remove("rohan") == null);  // true
            System.out.println(table.remove("manga"));  // {30, 110}
            System.out.println(table.remove("emerald"));  // {50}
            System.out.println(table.remove("fountain pen"));  // {70}
            System.out.println(table.remove("character"));  // {90}
            System.out.println(table);  // [null, null, null, null, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing getNumKeys ---");
        System.out.println();
        System.out.println("(0) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            System.out.println(table.getNumKeys());  // 2
            System.out.println(table.remove("goodbye"));  // {10}
            System.out.println(table.getNumKeys());  // 1

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing getNumKeys ---");
        System.out.println();
        System.out.println("(1) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5); 
            System.out.println(table.getNumKeys());  // 3
            System.out.println(table.remove("goodbye"));  // {10}
            System.out.println(table.getNumKeys());  // 2
            table.insert("howdy", 20);  // (inserting a duplicate)
            System.out.println(table.getNumKeys());  // 2
            table.insert("pear", 30);
            System.out.println(table.getNumKeys());  // 3

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing getNumKeys ---");
        System.out.println();
        System.out.println("(2) Testing on hash table: crazy diamond, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("shibainu", 20);
            table.insert("diamond", 40);
            table.insert("healing", 60);
            System.out.println(table.getNumKeys());  // 3
            table.insert("diamond", 80);  // (inserting a duplicate)
            System.out.println(table.getNumKeys());  // 3
            table.insert("love", 100);
            System.out.println(table.getNumKeys());  // 4

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing getNumKeys ---");
        System.out.println();
        System.out.println("(3) Testing on hash table: heaven's door, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("love", 10);
            table.insert("manga", 30);
            table.insert("emerald", 50);
            System.out.println(table.getNumKeys());  // 3
            System.out.println(table.remove("manga"));  // {30}
            System.out.println(table.getNumKeys());  // 2
            table.insert("fountain pen", 70);
            table.insert("character", 90);
            table.insert("manga", 110);
            System.out.println(table.getNumKeys());  // 5

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing load ---");
        System.out.println();
        System.out.println("(0) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5); 
            table.insert("howdy", 20);  // (inserting a duplicate)
            table.insert("pear", 30);
            System.out.println(table.load());  // 0.8

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing load ---");
        System.out.println();
        System.out.println("(1) Testing on hash table: crazy diamond, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("shibainu", 20);
            table.insert("diamond", 40);
            table.insert("healing", 60);
            System.out.println(table.load());  // 0.6
            table.insert("diamond", 80);  // (inserting a duplicate)
            System.out.println(table.load());  // 0.6
            table.insert("love", 100);
            System.out.println(table.load());  // 0.8

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing load ---");
        System.out.println();
        System.out.println("(2) Testing on hash table: heaven's door, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            System.out.println(table.load());  // 0.0
            table.insert("love", 10);
            table.insert("manga", 30);
            System.out.println(table.load());  // 0.4
            table.insert("emerald", 50);
            table.insert("fountain pen", 70);
            System.out.println(table.load());  // 0.8
            table.insert("character", 90);
            table.insert("manga", 110);  // (inserting a duplicate)
            System.out.println(table.load());  // 1.0

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing getAllKeys ---");
        System.out.println();
        System.out.println("(0) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5); 
            table.insert("howdy", 20);  // (inserting a duplicate)
            table.insert("pear", 30); 
            System.out.println(table);  // [{apple; howdy}, null, null, {goodbye}, {pear}]
            System.out.println(Arrays.toString(table.getAllKeys()));  // [apple, howdy, goodbye, pear]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing getAllKeys ---");
        System.out.println();
        System.out.println("(1) Testing on hash table: crazy diamond, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("shibainu", 20);
            table.insert("diamond", 40);
            table.insert("healing", 60);
            System.out.println(Arrays.toString(table.getAllKeys()));  // [healing, diamond, shinainu]
            table.insert("diamond", 80);
            table.insert("love", 100);
            System.out.println(Arrays.toString(table.getAllKeys()));  // [healing, diamond, shibainu, love]
            System.out.println(table);  // [null, {healing; diamond}, {shibainu}, {love}, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing getAllKeys ---");
        System.out.println();
        System.out.println("(2) Testing on hash table: heaven's door, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("love", 10);
            table.insert("manga", 30);
            System.out.println(Arrays.toString(table.getAllKeys()));  // [manga, love]
            table.insert("emerald", 50);
            table.insert("fountain pen", 70);
            System.out.println(Arrays.toString(table.getAllKeys()));  // [manga, fountain pen, emerald, love]
            table.insert("character", 90);
            table.insert("manga", 110);
            System.out.println(Arrays.toString(table.getAllKeys()));  // [character, manga, fountain pen, emerald, love]
            System.out.println(table);  // [{character}, {manga}, null, {fountain pen; emerald; love}, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        // ChainedHashTable testForCodes = new ChainedHashTable(7);
        // System.out.println("hash code for howdy is " + testForCodes.h1("howdy"));  // 5
        // System.out.println("hash code for goodbye is " + testForCodes.h1("goodbye"));  // 6
        // System.out.println("hash code for apple is " + testForCodes.h1("apple"));  // 1
        // System.out.println("hash code for pear is " + testForCodes.h1("pear"));  // 5
        // System.out.println("hash code for bubble is " + testForCodes.h1("bubble"));  // 0
        // System.out.println("hash code for shibainu is " + testForCodes.h1("shibainu"));  // 3
        // System.out.println("hash code for diamond is " + testForCodes.h1("diamond"));  // 2
        // System.out.println("hash code for healing is " + testForCodes.h1("healing"));  // 2
        // System.out.println("hash code for love is " + testForCodes.h1("love"));  // 2
        // System.out.println("hash code for manga is " + testForCodes.h1("manga"));  // 6
        // System.out.println("hash code for emerald is " + testForCodes.h1("emerald"));  // 3
        // System.out.println("hash code for fountain pen is " + testForCodes.h1("fountain pen"));  // 6
        // System.out.println("hash code for character is " + testForCodes.h1("character"));  // 6

        System.out.println("--- Testing resize ---");
        System.out.println();
        System.out.println("(0) Testing on hash table from Problem 7-3, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5); 
            table.insert("howdy", 20);  // (inserting a duplicate)
            table.insert("pear", 30); 
            table.insert("bubble", 60);
            System.out.println(table);  // [{apple; howdy}, null, null, {goodbye}, {bubble, pear}]
            table.resize(7);
            System.out.println(table);  // [{bubble}, {apple}, null, null, null, {pear; howdy}, {goodbye}]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing resize ---");
        System.out.println();
        System.out.println("(1) Testing on hash table: crazy diamond, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("shibainu", 20);
            table.insert("diamond", 40);
            table.insert("healing", 60);
            table.insert("diamond", 80);
            table.insert("love", 100);
            System.out.println(table);  // [null, {healing; diamond}, {shibainu}, {love}, null]
            table.resize(7);
            System.out.println(table);  // [null, null, {love; healing; diamond}, {shibainu}, null, null, null]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing resize ---");
        System.out.println();
        System.out.println("(2) Testing on hash table: heaven's door, ...");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            table.insert("love", 10);
            table.insert("manga", 30);
            table.insert("emerald", 50);
            table.insert("fountain pen", 70);
            table.insert("character", 90);
            table.insert("manga", 110);
            System.out.println(table);  // [{character}, {manga}, null, {fountain pen; emerald; love}, null]
            table.resize(7);
            System.out.println(table);  // [null, null, {love}, {emerald}, null, null, {character; fountain pen; manga}]

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

    }
}
