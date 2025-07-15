package DAY_4_Arrays_Part_4;

public class Two_Sum_Sorted_Array {
    /**
     * Two Sum II - Input array is sorted.
     *
     * Time Complexity: O(n)
     *   - Single pass using two pointers moving inward → O(n)
     *
     * Space Complexity: O(1)
     *   - Only constant extra space used → O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;                    // Start pointer at beginning of array
        int right = numbers.length - 1;  // End pointer at end of array

        while (left < right) {
            int sum = numbers[left] + numbers[right];  // Sum of elements at pointers

            if (sum == target) {
                // Found the two numbers whose sum equals the target
                // Return 1-based indices as per problem statement
                return new int[]{left + 1, right + 1};
            } else if (sum > target) {
                // Sum too large, move right pointer left to reduce sum
                right--;
            } else {
                // Sum too small, move left pointer right to increase sum
                left++;
            }
        }

        // No valid pair found
        return new int[]{-1, -1};
    }

}
