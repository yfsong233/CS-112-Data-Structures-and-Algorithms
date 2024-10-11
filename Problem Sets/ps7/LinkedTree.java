/*
 * LinkedTree.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Yufeng Song
 *     username: jyfsong
 */

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
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }
    
    /*
     * Prints the keys of the tree in the order given by a preorder
     * traversal.  Invokes the recursive preorderPrintTree method to
     * do the work.
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
     * Prints the keys of the tree in the order given by a postorder
     * traversal.  Invokes the recursive postorderPrintTree method to
     * do the work.
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
     * Prints the keys of the tree in the order given by an inorder
     * traversal.  Invokes the recursive inorderPrintTree method to do
     * the work.
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
     * Searches for the specified key in the tree.  If it finds it, it
     * returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual
     * search.
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
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
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
                toDeleteChild = toDelete.right;  // null if no children
            }
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }
    
    /**
     * sumKeysTo() takes an integer key as its only parameter 
     * and uses iteration to determine and return the sum of the keys 
     * on the path from the root node to the node with the specified key,
     * including the key itself.
     * 
     * It should return 0 if the specified key is not found in the tree.
     */
    public int sumKeysTo(int key) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Node trav = root;
        while (trav != null) {
            sum += trav.key;
            if (key == trav.key) {
                return sum;
            } else if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        return 0;
    }

    /**
     * numLeafNodesInTree() takes a reference to a Node object as its only parameter
     * and uses recursion to find and return the number of leaf nodes 
     * in the binary search tree or subtree whose root node is specified by the parameter.
     * 
     * Make sure that the method correctly handles empty trees/subtrees
     */
    private static int numLeafNodesInTree(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return numLeafNodesInTree(node.left) + numLeafNodesInTree(node.right);
    }

    /**
     * numLeafNodes() takes no parameters 
     * and returns the number of leaf nodes in the entire tree.
     * It serves as a "wrapper" method for numLeafNodesInTree().
     * 
     * It should make the initial call to that method
     *  – passing in the root of the tree as a whole
     *  - and it should return whatever value that method returns.
     */
    public int numLeafNodes() {
        return numLeafNodesInTree(root);
    }

    /**
     * deleteSmallest() uses iteration to find and delete 
     * the node containing the smallest key in the tree;
     * it should also return the value of the key whose node was deleted.
     * 
     * If the tree is empty when the method is called, the method should return -1.
     */
    public int deleteSmallest() {
        if (root == null) {
            return -1;
        }
        
        Node parent = null, trav = root;

        while (trav.left != null) {  // find the smallest node
            parent = trav;
            trav = trav.left;
        }
        
        if (parent == null) {  // parent has not been advanced
            if (trav.right != null) {
                root = trav.right;
            } else {
                root = null;
            }
        } else if (trav.right != null) {  // the smallest node has a right subtree
            parent.left = trav.right;
        } else {  
            parent.left = null;  // leaf node; trav is not the root
        }

        return trav.key;
    }


    public static void main(String[] args) {
        /*
         * Add at least two unit tests for each method that you write.
         * Test a variety of different cases. 
         * Follow the same format that we used in the previous problem.
         * We have given you some preliminary code for the first test below.
         */

        System.out.println("--- Testing sumKeysTo ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 6, ...");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);

	    // add the rest of the test here
            System.out.println("\nfor the tree from problem 6:");
            System.out.println("sum to 13 = " + tree.sumKeysTo(13));  // sum to 13 = 76
            System.out.println("sum to 56 = " + tree.sumKeysTo(56));  // sum to 56 = 135
            System.out.println("sum to 37 = " + tree.sumKeysTo(37));  // sum to 37 = 37
            System.out.println("sum to 50 = " + tree.sumKeysTo(50));  // sum to 50 = 0
	    
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();    // include a blank line between tests


        System.out.println("--- Testing sumKeysTo ---");
        System.out.println();
        System.out.println("(1) Testing on tree 1 ...");
        try {
            LinkedTree tree1 = new LinkedTree();
            int[] keys = {10, 19, 35, 2, 79, 18, 16};
            tree1.insertKeys(keys);

            System.out.println("\nfor tree 1:");
            System.out.println("sum to 18 = " + tree1.sumKeysTo(18));  // sum to 18 = 47
            System.out.println("sum to 79 = " + tree1.sumKeysTo(79));  // sum to 79 = 143
            System.out.println("sum to 2 = " + tree1.sumKeysTo(2));  // sum to 2 = 12
            System.out.println("sum to 10 = " + tree1.sumKeysTo(10));  // sum to 10 = 10
            System.out.println("sum to 1 = " + tree1.sumKeysTo(1));  // sum to 1 = 0
	    
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();


        System.out.println("--- Testing sumKeysTo ---");
        System.out.println();
        System.out.println("(2) Testing on tree 2 ...");
        try {
            LinkedTree tree2 = new LinkedTree();
            int[] keys = {11, 9, 15, 12, 7, 4, 10};
            tree2.insertKeys(keys);

            System.out.println("\nfor tree 2:");
            System.out.println("sum to 10 = " + tree2.sumKeysTo(10));  // sum to 10 = 30
            System.out.println("sum to 4 = " + tree2.sumKeysTo(4));  // sum to 4 = 31
            System.out.println("sum to 15 = " + tree2.sumKeysTo(15));  // sum to 15 = 26
            System.out.println("sum to 11 = " + tree2.sumKeysTo(11));  // sum to 11 = 11
            System.out.println("sum to 3 = " + tree2.sumKeysTo(3));  // sum to 3 = 0
	    
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();


        System.out.println("--- Testing sumKeysTo ---");
        System.out.println();
        System.out.println("(3) Testing on tree 3 ...");
        try {
            LinkedTree tree3 = new LinkedTree();
            int[] keys = {8, 5, 100, 7, 36, 6, 72};
            tree3.insertKeys(keys);

            System.out.println("\nfor tree 3:");
            System.out.println("sum to 72 = " + tree3.sumKeysTo(72));  // sum to 72 = 216
            System.out.println("sum to 7 = " + tree3.sumKeysTo(7));  // sum to 7 = 20
            System.out.println("sum to 8 = " + tree3.sumKeysTo(8));  // sum to 8 = 8
            System.out.println("sum to 45 = " + tree3.sumKeysTo(45));  // sum to 45 = 0
	    
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
                           
        System.out.println();




        System.out.println("--- Testing numLeafNodes ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 6, ...");
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println(tree.numLeafNodes());  // 0

            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            System.out.println(tree.numLeafNodes());  // 4
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("--- Testing numLeafNodes ---");
        System.out.println();
        System.out.println("(1) Testing on tree 1");
        try {
            LinkedTree tree1 = new LinkedTree();
            System.out.println(tree1.numLeafNodes());  // 0

            int[] keys = {10, 19, 35, 2, 79, 18, 16};
            tree1.insertKeys(keys);
            System.out.println(tree1.numLeafNodes());  // 3
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("--- Testing numLeafNodes ---");
        System.out.println();
        System.out.println("(2) Testing on tree 2");
        try {
            LinkedTree tree2 = new LinkedTree();
            System.out.println(tree2.numLeafNodes());  // 0

            int[] keys = {11, 9, 15, 12, 7, 4, 10};
            tree2.insertKeys(keys);
            System.out.println(tree2.numLeafNodes());  // 3
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("--- Testing numLeafNodes ---");
        System.out.println();
        System.out.println("(3) Testing on tree 3");
        try {
            LinkedTree tree3 = new LinkedTree();
            System.out.println(tree3.numLeafNodes());  // 0

            int[] keys = {8, 5, 100, 7, 36, 6, 72};
            tree3.insertKeys(keys);
            System.out.println(tree3.numLeafNodes());  // 2
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("--- Testing numLeafNodes ---");
        System.out.println();
        System.out.println("(4) Testing on tree 4");
        try {
            LinkedTree tree4 = new LinkedTree();
            System.out.println(tree4.numLeafNodes());  // 0

            int[] keys = {8};
            tree4.insertKeys(keys);
            System.out.println(tree4.numLeafNodes());  // 1
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();




        System.out.println("--- Testing deleteSmallest ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 6, ...");
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.deleteSmallest());  // empty tree: -1
            System.out.println();

            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            tree.levelOrderPrint();
            // 37
            // 26 42
            // 13 35 56
            // 30 47 70
            System.out.println();

            System.out.println("first deletion: " + tree.deleteSmallest());  // first deletion: 13
            tree.levelOrderPrint();
            // 37
            // 26 42
            // 35 56
            // 30 47 70
            System.out.println();

            System.out.println("second deletion: " + tree.deleteSmallest());  // second deletion: 26
            tree.levelOrderPrint();
            // 37
            // 35 42
            // 30 56
            // 47 70
            System.out.println();

            System.out.println("third deletion: " + tree.deleteSmallest());  // third deletion: 30
            tree.levelOrderPrint();
            // 37
            // 35 42
            // 56
            // 47 70
            System.out.println();

            System.out.println("fourth deletion: " + tree.deleteSmallest());  // fourth deletion: 35
            tree.levelOrderPrint();
            // 37
            // 42
            // 56
            // 47 70
            System.out.println();

            System.out.println("fifth deletion: " + tree.deleteSmallest());  // fifth deletion: 37
            tree.levelOrderPrint();
            // 42
            // 56
            // 47 70
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();



        System.out.println("--- Testing deleteSmallest ---");
        System.out.println();
        System.out.println("(1) Testing on tree 1");
        try {
            LinkedTree tree1 = new LinkedTree();
            System.out.println("empty tree: " + tree1.deleteSmallest());  // empty tree: -1
            System.out.println();

            int[] keys = {10, 19, 35, 2, 79, 18, 16};
            tree1.insertKeys(keys);
            tree1.levelOrderPrint();
            // 10
            // 2 19
            // 18 35
            // 16 79
            System.out.println();

            System.out.println("first deletion: " + tree1.deleteSmallest());  // first deletion: 2
            tree1.levelOrderPrint();
            // 10
            // 19
            // 18 35
            // 16 79
            System.out.println();

            System.out.println("second deletion: " + tree1.deleteSmallest());  // second deletion: 10
            tree1.levelOrderPrint();
            // 19
            // 18 35
            // 16 79
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("--- Testing deleteSmallest ---");
        System.out.println();
        System.out.println("(2) Testing on tree 2");
        try {
            LinkedTree tree2 = new LinkedTree();
            System.out.println("empty tree: " + tree2.deleteSmallest());  // empty tree: -1
            System.out.println();

            int[] keys = {11, 9, 15, 12, 7, 4, 10};
            tree2.insertKeys(keys);
            tree2.levelOrderPrint();
            // 11
            // 9 15
            // 7 10 12
            // 4
            System.out.println();

            System.out.println("first deletion: " + tree2.deleteSmallest());  // first deletion: 4
            tree2.levelOrderPrint();
            // 11
            // 9 15
            // 7 10 12
            System.out.println();

            System.out.println("second deletion: " + tree2.deleteSmallest());  // second deletion: 7
            tree2.levelOrderPrint();
            // 11
            // 9 15
            // 10 12
            System.out.println();

            System.out.println("third deletion: " + tree2.deleteSmallest());  // third deletion: 9
            tree2.levelOrderPrint();
            // 11
            // 10 15
            // 12
            System.out.println();

            System.out.println("fourth deletion: " + tree2.deleteSmallest());  // fourth deletion: 10
            tree2.levelOrderPrint();
            // 11
            // 15
            // 12
            System.out.println();

            System.out.println("fifth deletion: " + tree2.deleteSmallest());  // fifth deletion: 11
            tree2.levelOrderPrint();
            // 15
            // 12
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("--- Testing deleteSmallest ---");
        System.out.println();
        System.out.println("(3) Testing on tree 3");
        try {
            LinkedTree tree3 = new LinkedTree();
            System.out.println("empty tree: " + tree3.deleteSmallest());  // empty tree: -1
            System.out.println();

            int[] keys = {8, 5, 100, 7, 36, 6, 72};
            tree3.insertKeys(keys);
            tree3.levelOrderPrint();
            // 8
            // 5 100
            // 7 36
            // 6 72
            System.out.println();

            System.out.println("first deletion: " + tree3.deleteSmallest());  // first deletion: 5
            tree3.levelOrderPrint();
            // 8
            // 7 100
            // 6 36
            // 72
            System.out.println();

            System.out.println("second deletion: " + tree3.deleteSmallest());  // second deletion: 6
            tree3.levelOrderPrint();
            // 8
            // 7 100
            // 36
            // 72
            System.out.println();

            System.out.println("third deletion: " + tree3.deleteSmallest());  // third deletion: 7
            tree3.levelOrderPrint();
            // 8
            // 100
            // 36
            // 72
            System.out.println();

            System.out.println("fourth deletion: " + tree3.deleteSmallest());  // fourth deletion: 8
            tree3.levelOrderPrint();
            // 100
            // 36
            // 72
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("--- Testing deleteSmallest ---");
        System.out.println();
        System.out.println("(4) Testing on an empty list");
        try {
            LinkedTree tree4 = new LinkedTree();
            System.out.println("empty tree: " + tree4.deleteSmallest());  // empty tree: -1
            System.out.println();

            int[] keys = {};
            tree4.insertKeys(keys);
            tree4.levelOrderPrint();
            System.out.println();

            System.out.println("first deletion: " + tree4.deleteSmallest());  // first deletion: -1
            tree4.levelOrderPrint();
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

    }
}
