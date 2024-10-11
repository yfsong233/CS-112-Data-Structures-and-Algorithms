/* 
 * ArrayBag.java
 * 
 * Computer Science 112
 *
 * version for Lab 4
 *
 * For PS 4, please use the separate version that we have provided there.
 */

/**
 * An implementation of a bag data structure using an array.
 */
public class ArrayBag {
    /** 
     * The array used to store the items in the bag.
     * The advantage of Polymorphism applies.
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
     * We cannot have a getItem() method for accessing a specific item in the bag,
     * b/c a bag doesn't have positions
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

    /*
     * hasMoreRoom - takes as a parameter another ArrayBag called other,
     * returns true if the called ArrayBag has more room left for items
     * (i.e., more unused array elements) than other does,
     * and false otherwise.
     * 
     * If other is null, the method should throw an IllegalArgumentException.
     */
    public boolean hasMoreRoom(ArrayBag other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        int thisRoomLeft = this.items.length - this.numItems;
        int otherRoomLeft = other.items.length - other.numItems;
        
        return (thisRoomLeft > otherRoomLeft);

    }
    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Add your test code below.
        ArrayBag b = new ArrayBag(DEFAULT_MAX_SIZE);
        b.add("don't blink");
        b.add("baggy");
        System.out.println(b);  // {don't blink, baggy}

        /*
         * String s = b.grab();
         * Error Message: "Bad types in assignment, from Object to String."
         * 
         * Explanation: This makes sense because the return type of grab is Object, 
         * so the compiler thinks we are trying to assign something of type Object 
         * to a variable of type String, and Object is not a subclass of String.
         * 
         * Solution: use a type cast to reassure the compiler that the assignment will be valid.
         * If the item being returned were not a String object, an exception would be thrown.
         */ 
        String s = (String)b.grab();
        System.out.println(s);

        // Testing for hasMoreRoom()
        ArrayBag b1 = new ArrayBag(10);
        ArrayBag b2 = new ArrayBag(12);
        System.out.println(b2.hasMoreRoom(b1));  // true
        
        b2.add("hello");
        b2.add("world");
        System.out.println(b2.hasMoreRoom(b1));  // false

	
    }
}
