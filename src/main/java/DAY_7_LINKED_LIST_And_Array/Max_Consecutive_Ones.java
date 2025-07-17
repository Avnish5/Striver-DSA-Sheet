package DAY_7_LINKED_LIST_And_Array;

public class Max_Consecutive_Ones {

    /**
     * 1
     *
     * Time Complexity: O(n) — traverse the array once.
     * Space Complexity: O(1) — only a few variables used.
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        // innerMax tracks current streak of consecutive 1s
        int innerMax = 0;

        // ans stores the maximum streak found so far
        int ans = 0;

        // Loop through each element in the array
        for (int i : nums) {
            if (i == 1) {
                // If 1 is found, increase current streak
                innerMax++;
            } else {
                // If 0 is found, compare and update max streak
                ans = Math.max(innerMax, ans);

                // Reset current streak
                innerMax = 0;
            }
        }

        // Final check in case the array ends with 1s
        return Math.max(innerMax, ans);
    }

}
