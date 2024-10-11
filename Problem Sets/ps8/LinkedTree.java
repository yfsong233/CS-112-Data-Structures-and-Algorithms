/*
 * LinkedTree.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Yufeng Song
 *     username: jyfsong
 */

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        private Node parent;     // reference to the parent. NOT YET MAINTAINED!
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }
    
    /*
     * Prints the keys of the tree in the order given by a preorder traversal.
     * Invokes the recursive preorderPrintTree method to do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder traversal.
     * Invokes the recursive postorderPrintTree method to do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder traversal.
     * Invokes the recursive inorderPrintTree method to do the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
    
    /*
     * Searches for the specified key in the tree.
     * If it finds it, it returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
            newNode.parent = null;
        } else if (key < parent.key) {
            parent.left = newNode;
            newNode.parent = parent;
        } else {
            parent.right = newNode;
            newNode.parent = parent;
        }
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array from 
     * left to right, adding a node for each of them to the tree. 
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if it has no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
                if (root != null) {
                    root.parent = null;
                }
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
                if (toDeleteChild != null) {
                    toDeleteChild.parent = parent;
                }
            } else {
                parent.right = toDeleteChild;
                if (toDeleteChild != null) {
                    toDeleteChild.parent = parent;
                }
            }
        }
    }

    /*
     * PS 8: Problem 1
     * public "wrapper" method that makes the initial call 
     * to the recursive method for testing if there are any
     * keys greater than v in the tree.
     */
    public boolean anyGreater(int v) {
        // make the first call to the recursive method,
        // passing in the root of the tree as a whole
        return anyGreaterInTree(root, v);
    }
    
    /*
     * PS 8: Problem 1
     * Initial recursive method for testing if there are any
     * keys greater than v in the tree.
     * 
     * In problem 1.3, you will revise this to take advantage
     * of the fact that the tree is a binary search tree.
     */
    // private static boolean anyGreaterInTree(Node root, int v) {
    //     if (root == null) {    
    //         return false;
    //     } else {
    //         boolean anyGreaterInLeft = anyGreaterInTree(root.left, v);
    //         boolean anyGreaterInRight = anyGreaterInTree(root.right, v);
    //         return (root.key > v || anyGreaterInLeft || anyGreaterInRight);
    //     }
    // }    
    
    /* 
     * anyGreaterInTree - a revised version that takes advantage of
     * the fact tht the tree is a binary search tree
     */
    private static boolean anyGreaterInTree(Node root, int v) {
        if (root == null) {
            return false;
        } else if (root.key > v) {
            return true;
        } else {
            boolean anyGreater = anyGreaterInTree(root.right, v);
            return anyGreater;
        }
    }
    
    
    /* Returns a preorder iterator for this tree. */
    public LinkedTreeIterator preorderIterator() {
        return new PreorderIterator();
    }
    
    /* 
     * inner class for a preorder iterator 
     * IMPORTANT: You will not be able to actually use objects from this class
     * to perform a preorder iteration until you have modified the LinkedTree
     * methods so that they maintain the parent fields in the Nodes.
     */
    private class PreorderIterator implements LinkedTreeIterator {
        private Node nextNode;
        
        private PreorderIterator() {
            // The traversal starts with the root node.
            nextNode = root;
        }
        
        public boolean hasNext() {
            return (nextNode != null);
        }
        
        public int next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            
            // Store a copy of the key to be returned.
            int key = nextNode.key;
            
            // Advance nextNode.
            if (nextNode.left != null) {
                nextNode = nextNode.left;
            } else if (nextNode.right != null) {
                nextNode = nextNode.right;
            } else {
                // We've just visited a leaf node.
                // Go back up the tree until we find a node
                // with a right child that we haven't seen yet.
                Node parent = nextNode.parent;
                Node child = nextNode;
                while (parent != null &&
                       (parent.right == child || parent.right == null)) {
                    child = parent;
                    parent = parent.parent;
                }
                
                if (parent == null) {
                    nextNode = null;  // the traversal is complete
                } else {
                    nextNode = parent.right;
                }
            }
            
            return key;
        }
    }   
    

    /**
     * Cases for a preorder iterator
     * Case 1: The current node has a left child (e.g., if the current
                node is a, b, or e in the example). A preorder traversal visits
                the root, then it recursively traverses the left subtree, then it
                recursively traverses the right subtree. Therefore, if the current
                node has a left child, then the next node to be visited is the
                left child, since it is the root of the left subtree.

     * Case 2: The current node does not have a left child, but it does have
                a right child (e.g., if the current node is f). In this case,
                because there is no left subtree, the traversal goes next to the right
                subtree, and thus the next node to be visited is the current node's right
                child, since it is the root of the right subtree.

     * Case 3: The current node is a leaf node (e.g., if the current
                node is d, j, i or g). In this case, we need to go back up the
                tree, looking for nodes that have yet to be visited.

     * Because a preorder traversal visits a node as soon as it
        encounters it (i.e., the root is visited first) and then visits
        the left subtree, the only unvisited nodes are in right subtrees
        of nodes that we've already visited. Thus, we can trace back up
        using parent references until we find a node with a right child
        *on a different path* from the one that we took to get to the
        current node.
    */

    /* Returns an inorder iterator for this tree. */
    public LinkedTreeIterator inorderIterator() {
        return new InorderIterator();
    }
    
    /* 
     * inner class for an inorder iterator 
     */
    private class InorderIterator implements LinkedTreeIterator {
        private Node nextNode;

        private InorderIterator() {
            // find the first valid node for inorder traversal and assign nextNode to it
            Node trav = root;
            if (trav == null) {
                throw new IllegalArgumentException();
            }
            while (trav.left != null) {
                trav = trav.left;
            }
            nextNode = trav;
        }

        public boolean hasNext() {  // must be set to public as the interface demands
            return (nextNode != null);
        }

        public int next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
           
            int key = nextNode.key;  // Store a copy of the key to be returned.

            // Advance nextNode.
            if (nextNode.right != null) {  // nextNode has a right child
                Node r = nextNode.right;
                while (r.left != null) {
                    r = r.left;
                }
                nextNode = r;
            } else {  // nextNode does NOT have a right child
                Node p = nextNode.parent;
                Node c = nextNode;
                
                while (p != null && c == p.right) {
                    c = p;
                    p = p.parent;
                }
                
                if (p == null) {
                    nextNode = null;
                    return key;
                } else if (c == p.left) {
                    c = p;
                    p = p.parent;
                    nextNode = c;
                }
            }
            
            return key;
        }

    }

    public static void main(String[] args) {
        System.out.println("--- Testing anyGreaterInTree ---");
        System.out.println();
        System.out.println("(0) Testing on tree 1-15, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {15, 10, 3, 1, 12, 13, 18, 16, 22, 29, 20};
            tree.insertKeys(keys);
            System.out.println(tree.anyGreater(29));  // false
            System.out.println(tree.anyGreater(40));  // false
            System.out.println(tree.anyGreater(100));  // false
            System.out.println(tree.anyGreater(22));  // true
            System.out.println(tree.anyGreater(18));  // true
            System.out.println(tree.anyGreater(10));  // true

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing anyGreaterInTree ---");
        System.out.println();
        System.out.println("(1) Testing on tree 1-36, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {15, 10, 3, 1, 13, 20, 28, 36};
            tree.insertKeys(keys);
            System.out.println(tree.anyGreater(29));  // true
            System.out.println(tree.anyGreater(40));  // false
            System.out.println(tree.anyGreater(100));  // false
            System.out.println(tree.anyGreater(22));  // true
            System.out.println(tree.anyGreater(18));  // true
            System.out.println(tree.anyGreater(10));  // true

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing anyGreaterInTree ---");
        System.out.println();
        System.out.println("(2) Testing on tree from coursepack, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {26, 12, 4, 7, 18, 32, 38};
            tree.insertKeys(keys);
            System.out.println(tree.anyGreater(29));  // true
            System.out.println(tree.anyGreater(40));  // false
            System.out.println(tree.anyGreater(100));  // false
            System.out.println(tree.anyGreater(22));  // true
            System.out.println(tree.anyGreater(18));  // true
            System.out.println(tree.anyGreater(10));  // true

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing InorderIterator ---");
        System.out.println();
        System.out.println("(0) Testing on tree 1-29, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {15, 10, 3, 1, 12, 13, 18, 16, 22, 29, 20};
            tree.insertKeys(keys);
            LinkedTreeIterator iter = tree.inorderIterator();
            while (iter.hasNext()) {
                int key = iter.next();
                System.out.print(key + " ");  // 1 3 10 12 13 15 16 18 20 22 29
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing InorderIterator ---");
        System.out.println();
        System.out.println("(1) Testing on tree 1-36, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {15, 10, 3, 1, 13, 20, 18, 36};
            tree.insertKeys(keys);
            LinkedTreeIterator iter = tree.inorderIterator();
            while (iter.hasNext()) {
                int key = iter.next();
                System.out.print(key + " ");  // 1 3 10 13 15 18 20 36
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing InorderTraversal ---");
        System.out.println();
        System.out.println("(2) Testing on tree from coursepack, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {26, 12, 4, 7, 18, 32, 38};
            tree.insertKeys(keys);
            LinkedTreeIterator iter = tree.inorderIterator();
            while (iter.hasNext()) {
                int key = iter.next();
                System.out.print(key + " ");  // 4, 7, 12, 18, 26, 32, 38
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing InorderTraversal ---");
        System.out.println();
        System.out.println("(3) Testing on empty tree, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {};
            tree.insertKeys(keys);
            LinkedTreeIterator iter = tree.inorderIterator();  // IllegalArgumentException
            while (iter.hasNext()) {
                int key = iter.next();
                System.out.print(key + " ");
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing InorderTraversal ---");
        System.out.println();
        System.out.println("(4) Testing on tree with one node, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {9};
            tree.insertKeys(keys);
            LinkedTreeIterator iter = tree.inorderIterator();
            while (iter.hasNext()) {
                int key = iter.next();
                System.out.print(key + " ");  // 9
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing InorderTraversal ---");
        System.out.println();
        System.out.println("(5) Testing on almost linear tree 1-6, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {1, 2, 3, 4, 5, 6};
            tree.insertKeys(keys);
            LinkedTreeIterator iter = tree.inorderIterator();
            while (iter.hasNext()) {
                int key = iter.next();
                System.out.print(key + " ");  // 1 2 3 4 5 6
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

        System.out.println("--- Testing InorderTraversal ---");
        System.out.println();
        System.out.println("(6) Testing on almost linear tree 6-1, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {6, 5, 4, 3, 2, 1};
            tree.insertKeys(keys);
            LinkedTreeIterator iter = tree.inorderIterator();
            while (iter.hasNext()) {
                int key = iter.next();
                System.out.print(key + " ");  // 1 2 3 4 5 6
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }                 
        System.out.println();

    }
}
