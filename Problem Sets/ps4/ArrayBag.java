/* 
 * ArrayBag.java
 *
 * A blueprint class for objects that represent a bag of other objects --
 * i.e., a collection of items in which the items do not have a position.
 * This implementation uses an array to store to objects in the bag.
 *
 * Computer Science 112
 *
 * modified by: Yufeng Song, jyfsong@bu.edu
 */

import java.util.*;

public class ArrayBag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        this.items = new Object[DEFAULT_MAX_SIZE];
        this.numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.items = new Object[maxSize];
        this.numItems = 0;
    }
    
    /**
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return this.numItems;
    }
    
    /** 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (this.numItems == this.items.length) {
            return false;    // no more room!
        } else {
            this.items[this.numItems] = item;
            this.numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < this.numItems - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
                this.items[this.numItems - 1] = null;
                
                this.numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (this.numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * this.numItems);
        return this.items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[this.numItems];
        
        for (int i = 0; i < this.numItems; i++) {
            copy[i] = this.items[i];
        }
        
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < this.numItems; i++) {
            str = str + this.items[i];
            if (i != this.numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }
    
    /* return the number of additional items that the called ArrayBag has room to store */
    public int roomLeft() {
        return this.items.length - this.numItems;
    }

    /* return true if the called ArrayBag is full, and false otherwise. */
    public boolean isFull() {
        if (this.items.length == this.numItems) {
            return true;
        } return false;
    }
    
    /**
     * This method should increase the maximum capacity of the called ArrayBag by the specified amount by 
     * 1) creating a new array with room to support the new maximum capacity, 
     * 2) copying any existing items into that array, 
     * and 3) replacing the original array with the new one by storing its reference in the called object.
     * 
     * If increment is 0, the method should just return.
     * If increment is negative, the method should throw an IllegalArgumentException.
     */
    public void increaseCapacity(int increment) {
        
        if (increment < 0) {
            throw new IllegalArgumentException();
        } else if (increment == 0) {
            return;  // a void return
        } else {
            Object[] expandedArray = new Object[this.items.length + increment];
            for (int i = 0; i < this.items.length; i++) {
            expandedArray[i] = this.items[i];
            }
            this.items = expandedArray;
        }

    }

    /**
     * This method should attempt to remove from the called ArrayBag 
     * all occurrences of the items found in the parameter other.
     * If the called object contains multiple copies of an item from other, 
     * all of the copies should be removed.
     * The method should return true if one or more items are removed, and false otherwise.
     * 
     * If the parameter is null, the method should throw an IllegalArgumentException.
     * If the parameter is an empty ArrayBag, the method should return false.
     */
    public boolean removeItems(ArrayBag other) {
        int numRemoved = 0;
        if (other == null) {
            throw new IllegalArgumentException();
        } else if (other.numItems == 0) {
            return false;
        } else {
            for (int i = 0; i < this.numItems; i++) {
                // System.out.println(this.items[i]);
                if (other.contains(this.items[i])) {
                    // System.out.println("other contains " + this.items[i]);
                    // for (int j = 0; j < this.numItems; j++) {
                    //     if (this.items[j].equals(this.items[i])) {
                    //         System.out.println("" + this.items[j] + " gets removed");
                    this.remove(this.items[i]);  // will it be regarded as a return in a nested loop?
                    numRemoved++;
                    i--;
                }
            }
        }
        if (numRemoved != 0) {
            return true;
        } return false;
    }

    /**
     * This method should create and return an ArrayBag containing one occurrence of 
     * any item that is found in both the called object and the parameter other.
     * The resulting bag should not include any duplicates.
     * Give the new ArrayBag a maximum size that is equal to the number of items in the bag 
     * with the smaller number of items. 
     * (Give the new ArrayBag a maximum size of 1 if one or both of the original bags are empty.)
     * The order of the items in the returned ArrayBag does not matter.
     * 
     * If there are no items that occur in both bags—including cases in which one or both of the bags are empty
     * the method should return an empty ArrayBag.
     * If the parameter is null, the method should throw an IllegalArgumentException.
     */
    public ArrayBag intersectionWith(ArrayBag other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        
        int smaller = this.numItems;
        boolean change = false;
        if (other.numItems < smaller) {
            smaller = other.numItems;
            change = true;
        }
        
        if (this.numItems == 0 || other.numItems == 0) {
            ArrayBag common = new ArrayBag(1);  
            // be careful of which class contains the method to call
            // can an array with only a null be returned?
            return common;
        } else {
            ArrayBag common = new ArrayBag(smaller);
            if (change == true) {
                for (int i = 0; i < smaller; i++) {
                    if (this.contains(other.items[i])) {
                        if (! common.contains(other.items[i])) {
                            common.add(other.items[i]);
                        }
                    }
                } 
            } else {
                for (int i = 0; i < smaller; i++) {
                    if (other.contains(this.items[i])) {
                        if (! common.contains(this.items[i])) {
                            common.add(this.items[i]);
                        }
                    }
                }
            }
            
            return common;
        }

    }
 
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // // Create a Scanner object for user input.
        // Scanner scan = new Scanner(System.in);
        
        // // Create an ArrayBag named bag1.
        // System.out.print("size of bag 1: ");
        // int size = scan.nextInt();
        // ArrayBag bag1 = new ArrayBag(size);
        // scan.nextLine();    // consume the rest of the line
        
        // // Read in strings, add them to bag1, and print out bag1.
        // String itemStr;        
        // for (int i = 0; i < size; i++) {
        //     System.out.print("item " + i + ": ");
        //     itemStr = scan.nextLine();
        //     bag1.add(itemStr);
        // }
        // System.out.println("bag 1 = " + bag1);
        // System.out.println();
        
        // // Select a random item and print it.
        // Object item = bag1.grab();
        // System.out.println("grabbed " + item);
        // System.out.println();
        
        // // Iterate over the objects in bag1, printing them one per
        // // line.
        // Object[] items = bag1.toArray();
        // for (int i = 0; i < items.length; i++) {
        //     System.out.println(items[i]);
        // }
        // System.out.println();
        
        // // Get an item to remove from bag1, remove it, and reprint the bag.
        // System.out.print("item to remove: ");
        // itemStr = scan.nextLine();
        // if (bag1.contains(itemStr)) {
        //     bag1.remove(itemStr);
        // }
        // System.out.println("bag 1 = " + bag1);
        // System.out.println();
        
        // Testing roomLeft()
        ArrayBag b1 = new ArrayBag(10);
        System.out.println("room before: " + b1.roomLeft());  // room before: 10
        b1.add("hello");
        b1.add("world");
        System.out.println("room after: " + b1.roomLeft());  // room after: 8

        // Testing isFull()
        ArrayBag c1 = new ArrayBag(2);
        System.out.println(c1.isFull());  // false
        c1.add("hello");
        System.out.println(c1.isFull());  // false
        c1.add("world");
        System.out.println(c1.isFull());  // true

        // Testing increaseCapacity()
        ArrayBag d1 = new ArrayBag(10);
        d1.add("hello");
        d1.add("world");
        System.out.println(d1);  // {hello, world}
        System.out.println("room before: " + d1.roomLeft());  // room before: 8

        d1.increaseCapacity(5);
        System.out.println(d1);  // {hello, world}
        System.out.println("room after: " + d1.roomLeft());  // room after: 13

        // Testing removeItems()
        ArrayBag x1 = new ArrayBag(6);
        String[] words1 = {"hello", "you", "how", "are", "you", "today?"};
        for (String w : words1) {
            x1.add(w);
        }        
        System.out.println(x1);  // {hello, you, how, are, you, today?}

        ArrayBag x2 = new ArrayBag(6);
        String[] words2 = {"not", "bad", "how", "are", "you", "doing"};
        for (String w : words2) {
            x2.add(w);
        }        
        System.out.println(x2);  // {not, bad, how, are, you, doing}

        System.out.println(x1.removeItems(x2));  // true
        System.out.println(x1);  // {hello, today?}  // --- {hello, how, you, today?}
        System.out.println(x2);  // {not, bad, how, are, you, doing}

        // Testing intersectionWith()
        ArrayBag y1 = new ArrayBag(10);
        String[] letters1 = {"a", "a", "b", "d", "f", "f", "f", "g"};
        for (String ltr: letters1) {
            y1.add(ltr);
        }
        System.out.println(y1);  // {a, a, b, d, f, f, f, g}

        ArrayBag y2 = new ArrayBag(8);
        String[] letters2 = {"a", "b", "c", "d", "d", "e", "f"};
        for (String ltr: letters2) {
            y2.add(ltr);
        }
        System.out.println(y2);  // {a, b, c, d, d, e, f}

        ArrayBag y3 = y1.intersectionWith(y2);
        System.out.println(y3);  // {a, b, d, f}
        System.out.println(y3.numItems());  // 4
        System.out.println(y3.roomLeft());  // 3
        System.out.println(y1);  // {a, a, b, d, f, f, f, g} // make sure original bags are unchanged
        System.out.println(y2);  // {a, b, c, d, d, e, f}
    }
}
