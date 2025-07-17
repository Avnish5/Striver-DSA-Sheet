package DAY_4_Arrays_Part_4;

import java.util.*;

public class Four_sum {

    /**
     * 1, Brute-force solution
     * <p>
     * Time Complexity: O(n^4)
     * - Four nested loops each ranging up to n (worst case).
     * <p>
     * Space Complexity: O(k)
     * - k = number of unique quadruplets (due to result storage in a set).
     * - Sorting is in-place, and constant extra space is used beyond output.
     */
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        Set<List<Integer>> resultSet = new HashSet<>();
        int n = nums.length;

        Arrays.sort(nums); // To help avoid duplicates

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];

                        if (sum == target) {
                            List<Integer> quad = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            resultSet.add(quad); // Set prevents duplicates
                        }
                    }
                }
            }
        }

        return new ArrayList<>(resultSet);
    }

    /**
     * 2. Optimized solution using sorting and two pointers.
     * <p>
     * Time Complexity: O(n^3)
     * - Outer two loops: O(n^2)
     * - Two-pointer inner loop: O(n)
     * => Total: O(n^3)
     * <p>
     * Space Complexity: O(k) (unique pairs returned as ans)
     * - No extra data structures used beyond the result list.
     * - Sorting is in-place.
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums); // Sort the array to enable two-pointer approach and duplicate skipping

        // First pointer (i) from 0 to n - 4
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Second pointer (j) from i + 1 to n - 3
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicate values for j
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Two-pointer initialization
                int p = j + 1;
                int q = n - 1;

                // While two pointers do not cross
                while (p < q) {
                    // Calculate the sum; cast to long to avoid integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[p] + nums[q];

                    if (sum > target) {
                        q--; // Decrease sum by moving right pointer left
                    } else if (sum < target) {
                        p++; // Increase sum by moving left pointer right
                    } else {
                        // Found a valid quadruplet
                        List<Integer> smallAns = List.of(nums[i], nums[j], nums[p], nums[q]);
                        ans.add(smallAns);

                        // Move both pointers inward
                        p++;
                        q--;

                        // Skip duplicate elements after p++
                        while (p < q && nums[p] == nums[p - 1]) p++;
                        // Skip duplicate elements after q--
                        while (p < q && nums[q] == nums[q + 1]) q--;
                    }
                }
            }
        }
        return ans;
    }
}