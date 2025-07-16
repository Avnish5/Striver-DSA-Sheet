package DAY_4_Arrays_Part_4;

import java.util.*;

public class Three_Sum {

    /**
     * 1. Brute Force with Duplicate Removal using HashSet
     *
     * Time Complexity: O(n^3 * log 3) ≈ O(n^3)
     *   - Triple nested loops: O(n^3)
     *   - Sorting each triplet of size 3 inside innermost loop: O(log 3) which is constant
     *   - HashSet contains and add operations are approximately O(1) on average
     *
     * Space Complexity: O(k * 3) ≈ O(k)
     *   - k = number of unique triplets found
     *   - Each triplet of size 3 stored in the answer list and the HashSet
     */
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<List<Integer>> seen = new HashSet<>();
        int size = nums.length;

        for (int i = 0; i < size; i++) {
            for(int j = i+1; j < size; j++) {
                for (int k = j+1; k < size; k++) {
                    int sum = nums[i] + nums[j] + nums[k];

                    if (sum == 0) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        Collections.sort(temp);

                        if (!seen.contains(temp)) {
                            ans.add(temp);
                            seen.add(temp);
                        }

                    }
                }
            }
        }

        return ans;
    }

    List<List<Integer>> ans = new ArrayList<>();

    private void twoSum(int[] nums, int left, int right, int target) {
        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                while (left < right && nums[left] == nums[left + 1]) left++;
                while (left < right && nums[right] == nums[right - 1]) right--;

                List<Integer> smallAns = List.of((-target), nums[left], nums[right]);
                ans.add(smallAns);
                left++;
                right--;
            }
        }
    }

    /**
     * 2. Optimized Approach -  Three Sum using Two Pointer Technique
     *
     * Time Complexity: O(n^2)
     *   - Sorting takes O(n log n)
     *   - Outer loop runs O(n) times
     *   - Inner two-pointer scan runs O(n) in total for each outer loop iteration
     *   → Total: O(n^2)
     *
     * Space Complexity: O(k)
     *   - Ignoring sorting (done in-place)
     *   - Only uses O(k) extra space for storing result triplets
     *   - No extra data structures used for processing (two pointers in-place)
     *   - k = number of unique triplets found
     */
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if (n < 3) return ans;
        Arrays.sort(nums);

        for(int i = 0; i < n; i++) {
            if (i >0 && nums[i] == nums[i-1]) {
                continue;
            }

            int target = - nums[i];
            twoSum(nums, i +1, n-1, target);
        }

        return ans;
    }


    public static  int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> seen = new HashMap<>();
        int max = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (seen.containsKey(c) && seen.get(c) >= start) {
                start = seen.get(c) + 1;
            }
            seen.put(c, i);
            max = Math.max(max, (i - start + 1));
        }



        return max;

    }

}
