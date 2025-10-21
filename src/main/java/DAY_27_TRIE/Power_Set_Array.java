package DAY_27_TRIE;

import java.util.ArrayList;
import java.util.List;

public class Power_Set_Array {

    /**
     *
     * Time Complexity: O(2^N * N)
     * ----------------------------
     * - There are 2^N possible subsets for an array of length N.
     * - For each subset, we make a copy of the current list (O(N) in the worst case)
     *   before adding it to the result.
     * - Hence, total time = O(2^N * N)
     *
     * Space Complexity: O(N)
     * -----------------------
     * - The recursion stack depth is at most N (one call per element).
     * - The 'current' list holds up to N elements at a time.
     * - The final 'ans' list takes O(2^N * N) space, but since it's required output,
     *   we exclude it from auxiliary space calculation.
     * - Therefore, auxiliary space = O(N)
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(nums, 0, ans, new ArrayList<>());
        return ans;
    }

    private void helper(int[] nums, int i, List<List<Integer>> ans, List<Integer> current) {
        // Base case: if we've processed all elements, add current subset
        if (i == nums.length) {
            ans.add(new ArrayList<>(current)); // copy current subset
            return;
        }

        // Include the current element
        current.add(nums[i]);
        helper(nums, i + 1, ans, current);

        // Exclude the current element (backtrack)
        current.removeLast();
        helper(nums, i + 1, ans, current);
    }
}
