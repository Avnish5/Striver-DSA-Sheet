package DAY_4_Arrays_Part_4;

import java.util.HashMap;

public class Two_Sum {

    /**
     * 1. Brute Force Approach
     *
     * Time Complexity: O(n^2)
     *   - Two nested loops: outer loop runs n times, inner loop runs up to n times → O(n^2)
     *
     * Space Complexity: O(1)
     *   - No extra space used beyond a few scalar variables → O(1)
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++){
            for (int j = i+1; j < nums.length; j++)
            {
                if (nums[i] + nums[j] == target) return new int[]{i,j};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 2. Optimal Approach using HashMap
     *
     * Time Complexity: O(n)
     *
     * Space Complexity: O(n)
     *   - Because of hashmap
     */
    public int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int check = target - nums[i];
            if(map.containsKey(check)) {
                return new int[]{map.get(check), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }
}
