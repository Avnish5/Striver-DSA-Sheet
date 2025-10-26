package DAY_9_Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination_sum_2 {

    /**
     * Time Complexity: O(2^n)
     * - In worst case, each element has two choices: include or exclude
     * - Actual complexity is better due to pruning (currSum > target) and duplicate skipping
     * - More precise: O(2^n × k) where k is average length of valid combinations (for copying to result)
     *
     * Space Complexity: O(n)
     * - Recursion stack depth: O(n) - maximum depth is the longest valid combination
     * - Current combination list (curr): O(n) - stores at most n elements
     * - Output list (ans) not counted as it's required by the problem
     * - Total auxiliary space: O(n)
     */
    public void helper(int[] candidates, int target, List<List<Integer>> ans,
                       List<Integer> curr, int index, int currSum) {
        // ✅ Base case
        if (currSum == target) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // 🚫 Pruning: if currSum > target, no need to go further (since array is sorted)
        if (currSum > target || index >= candidates.length) return;

        // ✅ Choose current element
        curr.add(candidates[index]);
        helper(candidates, target, ans, curr, index + 1, currSum + candidates[index]);
        curr.removeLast();

        // ✅ Skip duplicates
        int next = index + 1;
        while (next < candidates.length && candidates[next] == candidates[index]) {
            next++;
        }

        // ✅ Not choose current element
        helper(candidates, target, ans, curr, next, currSum);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates); // necessary to skip duplicates efficiently
        List<List<Integer>> ans = new ArrayList<>();
        helper(candidates, target, ans, new ArrayList<>(), 0, 0);
        return ans;
    }
}
