public class ps7Tester {
    /* 3-1 */
    public static void remAllStack(Stack<Object> stack, Object item) {
        Stack<Object> newStack = new LLStack<Object>();  // the new operator matters!!!
        while (! stack.isEmpty()) {
            Object top = stack.peek();
            if (! item.equals(top)) {
                newStack.push(top);
            }
            stack.pop();
        }
        while (! newStack.isEmpty()) {
            Object newTop = newStack.peek();
            stack.push(newTop);
            newStack.pop();
        }
        
    }
    
    /* 3-2 */
    public static void remAllQueue(Queue<Object> queue, Object item) {
	
        Queue<Object> newQueue = new LLQueue<Object>();
        while (! queue.isEmpty()) {
            Object first = queue.peek();
            if (! first.equals(item)) {
                newQueue.insert(first);
            }
            queue.remove();
        }
        while (! newQueue.isEmpty()) {
            Object newFirst = newQueue.peek();
            queue.insert(newFirst);
            newQueue.remove();
        }
        
    }
    
    public static void main(String[] args) {
        /* 3-1 */
        Stack<Object> stack = new ArrayStack<Object>(5);
        stack.push(10);
        stack.push(2);
        stack.push(7);
        stack.push(2);
        stack.push(5);
        System.out.println("The original stack is " + stack);  // {5, 2, 7, 2, 10} (from top to bottom) 
        System.out.println();
        remAllStack(stack, 2);
        System.out.println("The updated stack is " + stack);  // {5, 7, 10} (from top to bottom)
        // Important: a method that does not return anything cannot be printed!!!!!
        // Why does pushing an integer without stating Integer in the generic type work?
        System.out.println();
        
        /* 3-2 */
        Queue<Object> queue = new ArrayQueue<Object>(5);
        queue.insert(5);
        queue.insert(2);
        queue.insert(7);
        queue.insert(2);
        queue.insert(10);
        System.out.println("The original queue is " + queue);  // {5, 2, 7, 2, 10} (from top to bottom)
        System.out.println();
        remAllQueue(queue, 2);  
        System.out.println("The updated queue is " + queue); // {5, 7, 10} (from top to bottom)
        System.out.println();


        /* 7-1 */
        String[] letters1 = {"a", "b", "c"};
        LLList list1 = new LLList(letters1);
        System.out.println(list1);  // {a, b, c}
        System.out.println(list1.getLastItem());  // c
        System.out.println();

        /* 7-3 */
        String[] letters2 = {"a", "b", "c", "d", "e"};
        LLList list2 = new LLList(letters2);

        // Add two items to the end of the list.
        list2.addItem("f", 5);
        list2.addItem("g", 6);
        System.out.println(list2.getLastItem());  // g

        // Remove three items from the end of the list.
        list2.removeItem(6);
        list2.removeItem(5);
        list2.removeItem(4);
        System.out.println(list2.getLastItem());  // d
    }
}
