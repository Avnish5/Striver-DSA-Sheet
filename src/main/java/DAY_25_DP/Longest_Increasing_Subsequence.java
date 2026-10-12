package DAY_25_DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Longest_Increasing_Subsequence {

    private int recurionWithMemoization(int prev, int curr, int[] nums, int[][] memo ) {

        // Base case: if we have processed all elements
        if (curr >= nums.length) {
            return 0;
        }

        if(prev != -1 && memo[prev][curr] != -1 ) {
            return memo[prev][curr];
        }

        // Case 1: Exclude the current element
        // Move to next index without changing 'prev'
        int exclude = recurionWithMemoization(prev, curr + 1, nums, memo);

        int include = 0;

        // Case 2: Include the current element
        // Allowed only if:
        // 1) No previous element is chosen (prev == -1)
        // 2) Current element is greater than previously chosen element
        if (prev == -1 || nums[prev] < nums[curr]) {
            include = 1 + recurionWithMemoization(curr, curr + 1, nums, memo);
        }

        if(prev != -1) {
            return memo[prev][curr] = Math.max(exclude, include);
        }

        // Return the maximum length between include and exclude choices
        return Math.max(exclude, include);
    }


/**
 * Recursive helper function to compute the Longest Increasing Subsequence (LIS)
 * using recursion + memoization.
 *
 * Time Complexity:
 *  - O(n^2)
 *    There are at most n * n states for (prev, curr).
 *    Each state is computed once due to memoization.
 *
 * Space Complexity:
 *  - O(n^2) for memo table
 *  - O(n) recursion stack in the worst case
 */
    public int lengthOfLIS(int[] nums) {
        int[][] memo = new int[nums.length][nums.length];

        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        return recurionWithMemoization(-1, 0, nums, memo);
    }

    /**
     * 3. DP
     *
     * Time Complexity:
     * ----------------
     * O(n^2)
     * - Outer loop runs n times
     * - Inner loop runs up to n times
     *
     * Space Complexity:
     * -----------------
     * O(n)
     * - dp array of size n
     */

    public int lengthOfLISDP(int[] nums) {
        int n = nums.length;

        // dp[i] = length of LIS ending at index i
        int[] dp = new int[n];

        // Each element is at least a subsequence of length 1
        Arrays.fill(dp, 1);

        int ans = 1;

        // Build the dp array
        for(int i = 0; i < n; i++) {

            // Check all previous elements
            for(int j = 0; j < i; j++) {

                // If current element can extend increasing subsequence
                if(nums[j] < nums[i]) {

                    // Update dp[i] by extending subsequence at j
                    dp[i] = Math.max(dp[i], dp[j] + 1);

                    // Update global maximum LIS
                    ans = Math.max(dp[i], ans);
                }
            }
        }

        return ans;
    }


    /*
     * 4. Binary Search / Patience Sorting
     *
     * Approach:
     * ----------
     * Maintain an array/list `arr` where:
     *
     * arr[i] = smallest possible tail of an increasing
     *          subsequence of length (i + 1).
     *
     * For every number:
     * - Find the first element >= nums[i] using lower bound.
     * - If no such element exists, add nums[i].
     * - Otherwise, replace that element with nums[i].
     *
     * We use lower bound (first element >= target) because
     * the subsequence must be strictly increasing, so duplicates
     * should replace an existing value instead of increasing the size.
     *
     *
     * Time Complexity:
     * ----------------
     * O(n log n)
     * - We process each of the n elements once.
     * - For every element, binary search takes O(log n).
     *
     *
     * Space Complexity:
     * -----------------
     * O(n)
     * - `arr` can contain at most n elements.
     */

    public int lengthOfLIS4(int[] nums) {
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {

            // Find the first index where arr[index] >= nums[i].
            // This is called lower bound.
            int index = binarySearch(arr, nums[i]);

            // If nums[i] is greater than every element in arr,
            // it can extend the increasing subsequence.
            if (index == arr.size()) {
                arr.add(nums[i]);
            }
            else {
                // Replace the existing value with nums[i].
                // A smaller tail gives us a better chance to
                // build a longer increasing subsequence later.
                arr.set(index, nums[i]);
            }
        }

        // Size of arr represents the length of LIS.
        return arr.size();
    }


    /*
     * Lower Bound Binary Search
     *
     * Returns the first index where:
     *
     * arr[index] >= target
     *
     * If every element is smaller than target,
     * returns arr.size().
     */
    public int binarySearch(List<Integer> arr, int target) {

        // Inclusive search range: [left, right]
        int left = 0;
        int right = arr.size() - 1;

        // Default result means:
        // target is greater than every element.
        int result = arr.size();

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr.get(mid) < target) {

                // arr[mid] is too small.
                // Search on the right side.
                left = mid + 1;

            }
            else {

                // arr[mid] can be the answer because
                // arr[mid] >= target.
                result = mid;

                // But we need the FIRST such position,
                // so continue searching on the left.
                right = mid - 1;
            }
        }

        return result;
    }


}
