package DAY_3_Arrays_Part_3;

import java.util.HashMap;
import java.util.Map;

public class Majority_Element {

    /**
     * 1. Brute-Force Using Hash-Map
     *
    * Time Complexity: O(n)
    *   - Iterate through all elements once to count occurrences → O(n)
    *
    * Space Complexity: O(n)
    *   - HashMap stores up to n unique elements → O(n)
    */
    public int majorityElement1(int[] nums) {
         int mid = (nums.length / 2) + 1;
        Map<Integer, Integer> map = new HashMap<>();

        for (int val : nums) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            if (map.get(val) >= mid) return val;
        }

        return -1;
    }

    /**
     * 2. Optimal Approach Using Boyer-Moore Majority Vote Algorithm
     *
     * Time Complexity: O(n)
     *   - Single pass through the array to find the majority element → O(n)
     *
     * Space Complexity: O(1)
     *   - Uses only a few variables, no extra data structures → O(1)
     */
    public int majorityElement2(int[] nums) {
        // Initialize the first element as the majority candidate
        int count = 1;
        int majority = nums[0];

        // Iterate through the rest of the array
        for (int i = 1; i < nums.length; i++) {
            // If current element matches the candidate, increment the count
            if (majority == nums[i]) {
                count++;
            }
            // If the count drops to zero, update the majority candidate
            else if (count == 0) {
                majority = nums[i];
                count = 1;
            }
            // If it doesn't match, decrement the count
            else {
                count--;
            }
        }

        // Return the majority element found
        return majority;
     }
}
