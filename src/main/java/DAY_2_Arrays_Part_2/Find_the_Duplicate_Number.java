package DAY_2_Arrays_Part_2;

import java.util.Arrays;

public class Find_the_Duplicate_Number {

    /**
     * 1 - Brute-force approach using sorting to find the duplicate number.
     *
     * Time Complexity: O(n log n)
     *   - Due to sorting the array
     *
     * Space Complexity: O(1) (or O(n) depending on the sorting algorithm used)
     *   - Arrays.sort() uses O(log n) space for primitive types in Java (dual-pivot quicksort)
     */
    public int findDuplicate1(int[] nums) {
        Arrays.sort(nums);

        for (int  i =1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]){
                return nums[i];
            }
        }

        return -1;

    }

    /**
     * 2 - Optimal solution using Floyd's Tortoise and Hare (Cycle Detection) Algorithm.
     *
     * Time Complexity: O(n)
     *   - First loop: finding the meeting point takes at most n steps.
     *   - Second loop: finding the entrance to the cycle also takes at most n steps.
     *
     * Space Complexity: O(1)
     *   - No extra data structures used; only pointers.
     */
    public int findDuplicate2(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        slow = nums[slow];
        fast = nums[nums[fast]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
