/*
 * ListClient.java
 * 
 * CS 112 - Lab 8
 */

public class ListClient {
    /**
     * determine whether the list contains a given item
     * using a linear search or sequential search approach
    */
    public static boolean contains(List items, Object item) {
        if (items == null || item == null) {  // check the question carefully what to do with null input
            return false;
        }
    
        for (int i = 0; i < items.length(); i++) {
             Object itemAt = items.getItem(i);    // get item at position i  // the declared type matters
             if (itemAt.equals(item)) {  // what if item is of primary type?
                 return true;
             }
        }
    
        return false;
    }

    /**
     * The time efficiency for contains()
     * If we pass an ArrayList into contains(), each iteration of the loop
       calls the ArrayList version of getItem() to get the item at
       position i. Because arrays have random access, this version of
       getItem() takes a constant number (O(1)) of steps to get the item 
       at position i for any value of i. Therefore:

         - The best case is O(1), when we find the item at the beginning 
           of the list and only perform one iteration of the loop before 
           returning.

         - The worst case is O(n), when we either find the item at the end 
           of the list or never find it. In either case, we perform all 
           n iterations, and each iteration performs a constant number of 
           operations.

         - The average case is also O(n), because on average we perform half 
           of the iterations of the loop, each of which performs a constant 
           number of operations.
     * 
     * If we pass an LLList into contains(), each iteration of the loop 
       calls the LLList version of getItem() to get the item at position i. 
       This version of getItem() calls getNode(i), which starts at the
       beginning of the linked list and walks down to the node at 
       position i. As a result, it takes O(i) steps to get the item at 
       position i. Therefore:

         - The best case is O(1), when we find the item at the beginning of
           the list and only perform one iteration of the loop before
           returning. This iteration calls list.getItem(0), and because
           item 0's node is at the beginning of the linked list, it takes 
           only a small, fixed number of steps to access it.

         - The worst case is O(n^2), when we either find the item at the end 
           of the list or never find it. In either case, we perform all 
           n iterations, and each iteration performs O(i) steps. Thus, the 
           total number of steps is proportional to the sum of the arithmetic 
           sequence 1 + 2 + 3 + ... + n = O(n^2).
      
         - The average case is also O(n^2), because on average we perform half 
           of the iterations of the loop, which means we still get a total 
           number of steps that is roughly proportional to n^2.
    */

    
    /**
     * A revised version of contains() using an iterator 
     * to make contains() efficient for both ArrayList objects and LLList objects.
    */
    public static boolean contains2(List items, Object item) {
        if (items == null || item == null) {
            return false;
        }
        
        ListIterator iter = items.iterator();
        
        while (iter.hasNext()) {
            Object itemAt = iter.next();
            if (itemAt.equals(item)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * If we knew that the list were sorted, we could use binary search.
     * The pseudocode for an iterative implementation of that algorithm:
     * 
     * public static boolean bin_search(List list, Object item) {
        int min = 0;
        int max = list.length();
    
        while (min < max) {
            int mid = (min + max) / 2;
            Object midItem = list.getItem(mid);
            if (midItem.equals(item)) {
                return true;
            } else if (midItem is less than item) {  // CompareTo
                max = mid - 1;    // item must come before position mid
            } else {
                min = mid + 1;   // item must come after position mid
            }
        }
    
        return false;
    }
    */

    /**
     * If we pass in an ArrayList, the worst-case efficiency is O(log n), 
       where n is the number of items in the list. This is because: 

     * Each iteration of the while loop cuts the problem size in half, 
       and it takes O(log n) iterations to get down to a problem size of 1,
       at which point we will either find the item or realize it is not in 
       the list. 

     * Each iteration of the loop performs a constant (O(1)) number of steps, 
       since the ArrayList version of getItem() is O(1).

     * If we pass in an LLList, the worst-case efficiency is O(n log n), 
       where n is the number of items in the list. This is because: 

     * We again need at most O(log n) iterations to determine whether the 
       item is in the list.

     * The LLList version of getItem() takes O(i) steps to get the item 
       at position i. 

     * In the worst case, each iteration of the loop updates min, which 
       means that mid is always >= n/2, and each of the O(log n) calls 
       to list.getItem(mid) requires at least n/2 steps. 

     * Thus, the total number of steps is >= (n/2)(log n) = O(n log n). 

     * Note: Because linked lists don't have random access, binary search 
       is actually less efficient than linear search for items stored in a 
       linked list.
    */


    
    public static void main(String[] args) {
        List vals1 = new LLList();
        
        // Do **NOT** change these lines!
        vals1.addItem("hello", 0);
        vals1.addItem("world!", 0);
        vals1.addItem("how", 1);
        vals1.addItem("are", 1);
        vals1.addItem("you?", 2);
        System.out.println(vals1); // {world!, are, you, how, hello} // no double quotes
        
        // Add lines below to reorder the items in vals1 as instructed,
        // and then print the new contents of vals1.
        // expected result: {hello, world!, how, are, you?}
        String moved = (String) vals1.getItem(4); // type cast
        vals1.removeItem(4);
        vals1.addItem(moved, 0);
        moved = (String) vals1.getItem(4);
        vals1.removeItem(4);
        vals1.addItem(moved, 2);
        System.out.println(vals1);

        System.out.println();
        if (contains(vals1, "hello")) {
            System.out.println("hello is in vals");
        } else {
            System.out.println("hello is NOT in vals");
        }
        
        
    }
}