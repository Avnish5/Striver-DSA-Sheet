package DAY_13_STACK_AND_QUEUE;

import java.util.ArrayList;
import java.util.Stack;

public class Next_Smaller_Element {

    class Solution {
        /**
         * Time Complexity:
         *   - Each element is pushed and popped from the stack at most once → O(n)
         *   - Total: O(n)
         *
         * Space Complexity:
         *   - Stack stores indices → O(n)
         *   - Output ArrayList → O(n)
         *   Total: O(n)
         */
        static ArrayList<Integer> nextSmallerEle(int[] arr) {
            int n = arr.length;

            // Stack to store indices whose NSE hasn't been found yet
            Stack<Integer> st = new Stack<>();

            // Output ArrayList initialized to -1
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                ans.add(-1);
            }

            // Traverse the array
            for (int i = 0; i < n; i++) {
                // While current element is smaller than element at index on top of stack
                while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
                    ans.set(st.pop(), arr[i]); // Update NSE for index on top
                }

                // Push current index onto the stack
                st.push(i);
            }

            return ans;
        }
    }

}
