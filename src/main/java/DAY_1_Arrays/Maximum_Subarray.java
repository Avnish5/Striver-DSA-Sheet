package DAY_1_Arrays;

public class Maximum_Subarray {

    /**
     * Time Complexity: O(n)
     * - We traverse the array once.
     * - Each index does constant work (comparisons & additions).
     * - Therefore, total time is linear.
     *
     * Space Complexity: O(1)
     * - No extra array or data structure is used.
     * - Only constant variables (currentSum, maxSum).
     * - Hence, auxiliary space remains constant.
     */
    public int maxSubArray(int[] nums) {

        // Initialize both current sum and max sum with the first element
        int currentSum = nums[0];
        int maxSum = nums[0];

        // Traverse the array starting from index 1
        for (int i = 1; i < nums.length; i++) {

            // Either start a new subarray at nums[i] or extend the previous subarray
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Update global maximum if current subarray sum is larger
            maxSum = Math.max(maxSum, currentSum);
        }

        // Return the maximum subarray sum found
        return maxSum;
    }

}
