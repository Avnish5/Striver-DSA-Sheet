package DAY_4_Arrays_Part_4;

import java.util.HashMap;
import java.util.Map;

public class Longest_Subarray_with_Sum_K {

    /**
     * 1. Brute-force Approach:
     *
     * Time Complexity: O(n^2)
     * - Outer loop runs n times.
     * - Inner loop runs up to n times for each i.
     * - Total operations: O(n * n) in worst case.
     *
     * Space Complexity: O(1)
     * - No extra space used except a few variables.
     */
    public int longestSubarray1(int[] arr, int k) {
        int max = 0;
        int n = arr.length;

        // Iterate over each starting index
        for (int i = 0; i < n; i++) {
            int sum = arr[i];
            int currMax = 1;

            // If single element equals k
            if (sum == k) {
                max = Math.max(max, currMax);
            }

            // Check subarrays starting from i
            for (int j = i + 1; j < n; j++) {
                sum += arr[j];  // Add next element
                currMax++;      // Increase length

                if (sum == k) {
                    max = Math.max(max, currMax);
                }
            }
        }

        return max;
    }

    /**
     * 2. Optimal approach using HashMap
     *
     * Time Complexity: O(n)
     *   - We traverse the array once and perform O(1) operations per element.
     *
     * Space Complexity: O(n)
     *   - In the worst case, we may store all prefix sums in the hash map.
     */
    public int longestSubarray2(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();  // Stores first occurrence of each prefix sum
        int prefixSum = 0;  // Running sum of elements
        int res = 0;        // Result: length of the longest subarray with sum k

        for (int i = 0; i < n; i++) {
            prefixSum += arr[i];  // Update prefix sum

            // Case 1: If prefix sum equals k, the subarray from 0 to i sums to k
            if (prefixSum == k) {
                res = i + 1;
            }
            // Case 2: If (prefixSum - k) is found in the map, there exists a subarray with sum k
            else if (map.containsKey(prefixSum - k)) {
                int prevIndex = map.get(prefixSum - k);
                res = Math.max(res, i - prevIndex);  // Update result if this subarray is longer
            }

            // Only store the first occurrence of each prefix sum
            if (!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }
        }

        return res;
    }


}
