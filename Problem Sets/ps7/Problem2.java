public class Problem2 {
    // public static LLList scale(int factor, ArrayList vals) {
    //     LLList scaled = new LLList();

    //     for (int i = 0; i < vals.length(); i++) {
    //         int val = (Integer)vals.getItem(i);
    //         scaled.addItem(val*factor, i);
    //     }

    //     return scaled;
    // }

    public static LLList scale(int factor, ArrayList vals) {
        LLList newList = new LLList();
        
        for (int i = vals.length()-1; i >= 0; i--) {
            int value = (Integer)vals.getItem(i);  // O(1)
            newList.addItem(value*factor, 0);
        }	
    
        return newList;
    }
    

    public static void main(String[] args) {
        Integer[] valsArray = {3, 7, 8, 2, 1, 9};
        ArrayList vals = new ArrayList(valsArray);
        System.out.println("The ArrayList is " + vals);
        LLList scaled = scale(2, vals);
        System.out.println("The scaled LLList is " + scaled);  // {6, 14, 16, 4, 2, 18}
        System.out.println();

        Integer[] Array1 = {1, 2, 3, 4, 3, 2, 1}; 
        ArrayList AList1 = new ArrayList(Array1);
        System.out.println("The ArrayList is " + AList1);
        LLList LList1 = scale(3, AList1);
        System.out.println("The scaled LLList is " + LList1);  // {3, 6, 9, 12, 9, 6, 3}
        System.out.println();

        Integer[] Array2 = {5};
        ArrayList AList2 = new ArrayList(Array2);
        System.out.println("The ArrayList is " + AList2);
        LLList LList2 = scale(5, AList2);
        System.out.println("The scaled LLList is " + LList2);  // {25}
        System.out.println();

        Integer[] Array3 = {};
        ArrayList AList3 = new ArrayList(Array3);
        System.out.println("The ArrayList is " + AList3);
        LLList LList3 = scale(3, AList3);
        System.out.println("The scaled LLList is " + LList3);
        System.out.println();

    }
}