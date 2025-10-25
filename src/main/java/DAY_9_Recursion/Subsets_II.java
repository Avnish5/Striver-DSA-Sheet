package DAY_9_Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets_II {

    /**
     *
     * 🔹 Time Complexity:  O(2^N * N)
     *   - There are 2^N possible subsets.
     *   - Each subset takes O(N) time to copy into the result list.
     *   - Sorting adds O(N log N), which is negligible compared to 2^N * N.
     *
     * 🔹 Space Complexity: O(N)
     *   - Due to recursion stack depth (max depth = N).
     *   - The output list `ans` is not counted in auxiliary space.
     */
    public void helper(int[] nums, int index, List<List<Integer>> ans, List<Integer> curr) {
        // Base case: when we've considered all elements
        if (index == nums.length) {
            // Add a copy of the current subset to the answer list
            ans.add(new ArrayList<>(curr));
            return;
        }

        //  Choice 1: Include current element in subset
        curr.add(nums[index]);
        helper(nums, index + 1, ans, curr);
        curr.removeLast(); // Backtrack to explore exclusion path

        //  Choice 2: Exclude current element
        // But skip all consecutive duplicates of nums[index]
        int idx = index + 1;
        while (idx < nums.length && nums[idx] == nums[idx - 1]) idx++;

        // Recurse for the next distinct element
        helper(nums, idx, ans, curr);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // Sort to bring duplicates together for skipping
        helper(nums, 0, ans, new ArrayList<>());
        return ans;
    }
}
