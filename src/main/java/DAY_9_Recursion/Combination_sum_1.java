package DAY_9_Recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Combination_sum_1 {

    Set<List<Integer>> set = new HashSet<>();

    /**
     * Time Complexity: O(3^n × k)
     * - Each element has three choices: include once, include again, or exclude
     * - This creates a 3-way branching recursion tree with depth up to n
     * - k is the average length of combinations (for copying lists and hashing)
     * - HashSet contains() and add() operations: O(k) for comparing/hashing lists
     * - Note: This approach has logical issues - "include multiple times" doesn't work as intended
     *   because it calls the same recursive case twice with index+1 (not allowing true reuse)
     *
     * Space Complexity: O(n × m + n)
     * - Recursion stack depth: O(n) - can go up to n levels deep
     * - Current combination list (curr): O(n) - stores at most n elements
     * - HashSet (set): O(n × m) where m is number of unique valid combinations
     *   Each list in set takes O(k) space, and there can be up to m unique combinations
     * - Total auxiliary space: O(n × m + n) ≈ O(n × m) dominant term
     */
    public void helper1(int[] candidates, int target, List<List<Integer>> ans,
                       List<Integer> curr, int index) {
        if (target == 0) {
            if(!set.contains(curr)) {
                ans.add(new ArrayList<>(curr));
            }
            return;
        }

        if (target < 0 || index >= candidates.length) return;

        curr.add(candidates[index]);
        //include once
        helper1(candidates, target - candidates[index], ans, curr, index + 1);
        //include multiple times
        helper1(candidates, target - candidates[index], ans, curr, index + 1);

        curr.removeLast();
        //exclude
        helper1(candidates, target, ans, curr, index + 1);


    }

    /**
     * Time Complexity: O(2^t * k) where t is the target, k is the average length
     *
     * Space Complexity: O(k*x)
     */
    public void helper2(int[] candidates, int target, List<List<Integer>> ans,
                        List<Integer> curr, int index) {
        if (index == candidates.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(curr));
            }

            return;
        }

        if(candidates[index] <= target) {
            curr.add(candidates[index]);
            helper2(candidates, target - candidates[index], ans, curr, index);
            curr.removeLast();
        }

        helper2(candidates, target, ans, curr, index + 1);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        helper1(candidates, target, ans, new ArrayList<>(), 0);
        return ans;

    }
}
