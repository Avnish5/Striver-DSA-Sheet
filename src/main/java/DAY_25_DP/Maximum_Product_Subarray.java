package DAY_25_DP;

public class Maximum_Product_Subarray {
    /**
     * 1. Brute-Force
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n²)
     *
     * Explanation:
     * - Two nested loops
     * - Outer loop chooses start index
     * - Inner loop expands subarray and calculates product
     *
     * Space Complexity (SC):
     * ----------------------
     * O(1)
     *
     * Explanation:
     * - Only variables are used
     * - No extra data structures
     */
    public int maxProduct1(int[] nums) {

        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;

        // Fix the starting index of subarray
        for (int i = 0; i < n; i++) {

            int product = 1;

            // Extend subarray from i to j
            for (int j = i; j < n; j++) {

                // Calculate product of current subarray
                product *= nums[j];

                // Update maximum product
                maxProduct = Math.max(maxProduct, product);
            }
        }

        return maxProduct;
    }

    /**
     * 2. Optimized
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n)
     *
     * Explanation:
     * - Single pass through the array
     * - Each element is processed once from the left (prefix)
     *   and once from the right (suffix)
     *
     * Space Complexity (SC):
     * ----------------------
     * O(1)
     *
     * Explanation:
     * - Uses only constant extra variables
     * - No additional data structures
     */
    public int maxProduct2(int[] nums) {

        int n = nums.length;

        // Stores the maximum product found so far
        int ans = Integer.MIN_VALUE;

        // Prefix product (left to right)
        int prefix = 1;

        // Suffix product (right to left)
        int suffix = 1;

        for (int i = 0; i < n; i++) {

            // If prefix becomes 0, reset it
            // because product beyond zero must start fresh
            if (prefix == 0) prefix = 1;

            // If suffix becomes 0, reset it
            if (suffix == 0) suffix = 1;

            // Multiply current element from the left
            prefix = prefix * nums[i];

            // Multiply current element from the right
            suffix = suffix * nums[n - i - 1];

            // Update answer with the maximum of:
            // - current prefix product
            // - current suffix product
            ans = Math.max(ans, Math.max(prefix, suffix));
        }

        return ans;
    }


}
