package DAY_13_STACK_AND_QUEUE;

import java.util.Stack;

public class Sort_A_Stack {

    /**
     * Sorts the stack in ascending order using recursion.
     *
     * Time Complexity: O(n^2)
     * - Each element is inserted into the sorted stack using insertInSortedOrder(),
     *   which in the worst case takes O(n).
     * - And we do this for all n elements => O(n × n) = O(n^2)
     *
     * Space Complexity: O(n)
     * - Recursion stack holds at most n function calls
     * - No extra data structure used (except recursion)
     */
    public static void sortStack(Stack<Integer> st) {
        // Base: empty stack
        if (st.isEmpty()) {
            return;
        }

        // Remove top
        int top = st.pop();

        // Recursively sort remaining stack
        sortStack(st);

        // Insert popped element back into sorted order
        insertInSortedOrder(st, top);
    }

    // Insert element into already sorted stack
    private static void insertInSortedOrder(Stack<Integer> st, int value) {
        // If stack empty OR top < value → correct position
        if (st.isEmpty() || st.peek() <= value) {
            st.push(value);
            return;
        }

        // Otherwise pop and recurse
        int temp = st.pop();
        insertInSortedOrder(st, value);

        // Put removed element back
        st.push(temp);
    }

}
