
/* lab10 task1 */

public class StackClient<T> {
    public static void main(String[] args) {
        Stack<String> l = new LLStack<String>();  
        // generic data type implementation
        // T as a placeholder

        // The original stack
        l.push("b");
        l.push("c");
        l.push("a");
        l.push("e");
        l.push("f");

        System.out.println(l.toString());
        System.out.println();

        // revised stack
        l.pop();
        l.push("x");
        l.push("w");
        String str = l.pop();  // why no need for a type cast?
        /**
         * The Stack implementations are generic, and we specify that 
           l is a stack of Strings:
                Stack<String> l = new LLStack<String>();
           As a result, l's version of pop() has a return type of String.
        */
        l.pop();
        l.push("z");
        l.push(str);

        System.out.println(l.toString());
    }
}
