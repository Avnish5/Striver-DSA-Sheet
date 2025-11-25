package DAY_13_STACK_AND_QUEUE;

import java.util.ArrayList;
import java.util.Stack;

public class Next_Greater_Element_GFG {
    /**
     * Finds the Next Greater Element (NGE) for every element in the array.
     * An element's NGE is the first element to its right which is greater than it;
     * if no such element exists, the value is -1.
     *
     * Approach: Monotonic Stack (Optimized)
     *
     * Time Complexity:
     *   - Each element is pushed and popped from the stack at most once → O(n)
     *   - Total: O(n)
     *
     * Space Complexity:
     *   - Stack stores indices -> O(n)
     *   - Output ArrayList -> O(n)
     *   Total: O(n)
     */
    public ArrayList<Integer> nextLargerElement(int[] arr) {

        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();

        // Initialize all results to -1
        for (int i = 0; i < n; i++) {
            list.add(-1);
        }

        // Traverse array
        for (int i = 0; i < n; i++) {

            // While stack is not empty AND current element is the NGE of top index
            while (!st.isEmpty() && arr[i] > arr[st.peek()]) {
                list.set(st.pop(), arr[i]);
            }

            // Push current index onto the stack
            st.push(i);
        }

        return list;
    }

}
