package DAY_13_STACK_AND_QUEUE;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Next_Greater_Element {

    /**
     * 1. Brute Force
     *
     * Time Complexity:
     *   - Building map: O(n)
     *   - For each element in nums1, worst-case we scan entire nums2: O(n * m)
     *   Total: O(n * m)
     *
     * Space Complexity:
     *   - HashMap storing nums2 elements → O(n)
     *   - Output array → O(m)
     *   Total: O(n)
     */
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {

        // Map each value in nums2 to its index, so lookup is O(1)
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums2.length;
        int m = nums1.length;

        for (int i = 0; i < n; i++) {
            map.put(nums2[i], i);
        }

        // Result array initialized with -1 (meaning no next greater found)
        int[] ans = new int[m];
        Arrays.fill(ans, -1);

        int k = 0; // Pointer for nums1

        // For every element in nums1, search for next greater in nums2
        while (k < m) {

            // Find where nums1[k] appears in nums2
            int index = map.get(nums1[k]);

            // Linearly scan right side of nums2
            for (int i = index + 1; i < n; i++) {

                // First element greater than nums1[k] found
                if (nums2[index] < nums2[i]) {
                    ans[k] = nums2[i];
                    break; // Stop, no need to check further
                }
            }
            k++;
        }

        return ans;
    }

    /**
     * 2. Monotonic Decreasing Stack
     *
     * Time Complexity:  O(n + m)
     *   - O(n) to build next greater mapping for nums2
     *   - O(m) to answer queries for nums1
     *
     * Space Complexity: O(n)
     *   - HashMap stores next greater for each element in nums2
     *   - Stack used during processing
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {

        // Map to store: number -> next greater number
        Map<Integer, Integer> nge = new HashMap<>();

        // Monotonic Decreasing Stack:
        // Stack stores numbers from nums2 in decreasing order
        Stack<Integer> s = new Stack<>();

        int n = nums2.length;
        int m = nums1.length;

        int[] ans = new int[m];

        // Step 1: Build Next Greater Element for nums2
        for (int i = 0; i < n; i++) {

            // If current number is greater than the stack top,
            // then it is the "next greater" for stack top.
            while (!s.isEmpty() && nums2[i] > s.peek()) {
                nge.put(s.pop(), nums2[i]);
            }

            // Push the current number into stack
            // (maintains decreasing order)
            s.push(nums2[i]);
        }

        // Remaining elements have no next greater → assign -1
        while (!s.isEmpty()) {
            nge.put(s.pop(), -1);
        }

        // Step 2: Build answer for nums1 using computed map
        for (int i = 0; i < m; i++) {
            ans[i] = nge.get(nums1[i]);
        }

        return ans;
    }
}
