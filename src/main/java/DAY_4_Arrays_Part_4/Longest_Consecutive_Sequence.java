package DAY_4_Arrays_Part_4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Longest_Consecutive_Sequence {

    /**
     * 1. Brute force using sorting
     *
     * Time Complexity: O(n log n)
     *   - Due to Arrays.sort(nums), which is O(n log n)
     *   - The for-loop is O(n)
     *   - Overall: O(n log n)
     *
     * Space Complexity: O(1)
     *   - Only a constant amount of extra space is used (variables like max, currMax)
     *   - Sorting is done in-place
     */
    public int longestConsecutive1(int[] nums) {
        Arrays.sort(nums);
        int max = 1;
        int currMax = 1;
        int n = nums.length;

        if (n == 0) return 0;

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] == nums[i]) {
                continue;
            } else if (nums[i - 1] + 1 == nums[i]) {
                currMax++;
            } else {
                max = Math.max(max, currMax);
                currMax = 1;
            }
        }

        return Math.max(max, currMax);
    }

    /**
     * 2. Optimize using HashSet
     *
     * Why do we get TLE (Time Limit Exceeded) when using `for (int num : nums)`?
     *
     * - Iterating over every element in the original array means we process duplicates multiple times.
     * - The array might contain many duplicate elements.
     * - For each number, we perform checks and potentially run a while loop to count consecutive sequences.
     * - This repeated work on duplicates leads to excessive computations and causes TLE.
     *
     * Using a HashSet to iterate over unique elements helps avoid this redundant processing,
     * significantly reducing the number of iterations and preventing TLE.
     */

    public int longestConsecutive2(int[] nums) {
        int longest = 0;
        Set<Integer> seen  = new HashSet<>();

        for(int num : nums) {
            seen.add(num);
        }

        for (int num : nums) {
            if (!seen.contains(num-1)) {
                int temp = 1;
                while (seen.contains(num + temp)) {
                    temp++;
                }
                longest = Math.max(temp, longest);
            }
        }

        return longest;
    }

    /**
     * 3. Optimized final solution
     *
     * Time Complexity: O(n)
     * - Adding elements to the HashSet takes O(n).
     * - Each number is checked once in the for loop.
     * - The while loop runs at most n times in total across all sequences.
     *
     * Space Complexity: O(n)
     * - HashSet stores all unique elements from the input array.
     */
    public int longestConsecutive3(int[] nums) {
        int longest = 0;
        Set<Integer> seen  = new HashSet<>();

        // Add all numbers to the HashSet
        for(int num : nums) {
            seen.add(num);
        }

        // Iterate over unique numbers
        for (int num : seen) {
            // Only start counting if 'num' is the start of a sequence
            if (!seen.contains(num - 1)) {
                int temp = 1;
                // Count consecutive numbers starting from 'num'
                while (seen.contains(num + temp)) {
                    temp++;
                }
                longest = Math.max(temp, longest);
            }
        }

        return longest;
    }


}
