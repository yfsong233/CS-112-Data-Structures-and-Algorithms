public class lab11Tester {
    public static void main(String[] args) {
        /* 3-1 */
        LinkedTree tree = new LinkedTree();
        tree.insert(30, "this is the root");
        tree.insert(45, "this is one of 30's children");
        tree.insert(15, "this is another of 30's children");
        System.out.println();

        System.out.println( tree.search(45) );  
        // {this is one of 30's children} <-- The data (an LLList of values associated with that key) inside node 45
        System.out.println();

        tree.levelOrderPrint();
        // 30
        // 15 45
        System.out.println();

        /* 3-2 */
        LinkedTree tree2 = new LinkedTree();
        int[] keys = {15, 23, 20, 10, 13, 6, 18, 35, 9, 24};
        tree2.insertKeys(keys);
        tree2.levelOrderPrint();
        // 15 
        // 10 23 
        // 6 13 20 35 
        // 9 18 24
        System.out.println(); 

        tree2.inorderPrint();  // 6 9 10 13 15 18 20 23 24 35
        // An inorder traversal visits the keys in a binary search tree in order.
        System.out.println();

        tree2.delete(6);
        tree2.delete(15);
        tree2.delete(20);
        tree2.levelOrderPrint();
        // 18 
        // 10 23 
        // 9 13 35 
        // 24
        System.out.println();

        /* 3-3 */
        // Traversing both subtrees - recursion
        // Finding a specific node - iteration
        // Therefore use recursion to compute the sum of all of the keys

        /* 3-4 */
        // Test on sumKeys
        LinkedTree tree3 = new LinkedTree();
        int[] keys3 = {8, 4, 10, 2};
        tree3.insertKeys(keys3);
        System.out.println( tree3.sumKeys() );  // 24
        System.out.println();

        /* 3-5 */
        // Test on numEvenAlongPath
        System.out.println( tree3.numEvensAlongPath(5) );  // 2
    }  
}
