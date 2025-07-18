package DAY_4_Arrays_Part_4;

import java.util.HashMap;
import java.util.Map;

public class Subarray_Sum_Equals_K {

    /**
     * 1. Optimal Approach using HashMap
     *
     * Time Complexity: O(n)
     *   - We iterate through the array once.
     *   - Map operations (get/put) are O(1) on average.
     *
     * Space Complexity: O(n)
     *   - The HashMap stores prefix sums and their frequencies.
     *   - In worst case, all prefix sums are distinct.
     */
    public int subarraySum(int[] nums, int k) {
        int count  = 0;  // Number of subarrays found with sum = k
        Map<Integer, Integer> map = new HashMap<>();  // Map to store frequencies of prefix sums
        int prefixSum = 0;  // Running prefix sum of elements

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];  // Update prefix sum with current element

            // If prefixSum itself equals k, then subarray [0..i] sums to k
            if (prefixSum == k) {
                count++;
            }

            // Check if there exists a prefix sum that when removed leaves sum k
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);  // Add count of such prefix sums
            }

            // Update frequency of current prefixSum in map
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

}
