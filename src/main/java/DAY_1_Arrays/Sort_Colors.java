package DAY_1_Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Sort_Colors {

    /**
     * 1.ort Colors (Counting Approach)
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n)
     *
     * Explanation:
     * - One pass to count the numbers.
     * - Another pass to rewrite the array.
     * - Total operations proportional to n.
     *
     * Space Complexity (SC):
     * ----------------------
     * O(1)
     *
     * Explanation:
     * - Only three integer variables are used for counting.
     * - No extra data structures are created.
     */
    public void sortColors(int[] nums) {

        // Counters for each color
        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;

        // First pass: count occurrences of 0, 1, and 2
        for(int num : nums) {
            if(num == 0) zeroCount++;
            if(num == 1) oneCount++;
            if(num == 2) twoCount++;
        }

        // Index to fill the array from start
        int idx = 0;

        // Fill all 0s
        for(int i = 0; i < zeroCount; i++) {
            nums[idx++] = 0;
        }

        // Fill all 1s
        for(int i = 0; i < oneCount; i++) {
            nums[idx++] = 1;
        }

        // Fill all 2s
        for(int i = 0; i < twoCount; i++) {
            nums[idx++] = 2;
        }
    }

    /**
     * Time Complexity: O(n)
     * - Each element is processed at most once.
     *
     * Space Complexity: O(1)
     * - Sorting is done in-place using only index pointers.
     */
    public void sortColors2(int[] nums) {

        // Pointer for placing 0s
        int i = 0;

        // Pointer for scanning the array
        int j = 0;

        // Pointer for placing 2s
        int k = nums.length - 1;

        // Process elements until j crosses k
        while (j <= k) {

            // Case 1: nums[j] == 1 → already in correct middle region
            if (nums[j] == 1) {
                j++;
            }

            // Case 2: nums[j] == 2 → swap with end region and shrink k
            else if (nums[j] == 2) {
                int temp = nums[j];
                nums[j] = nums[k];
                nums[k] = temp;
                k--; // move boundary inward

            }

            // Case 3: nums[j] == 0 → swap with beginning region and expand i & j
            else { // nums[j] == 0
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
                j++;
            }
        }
    }

}
