package DAY_1_Arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Sort_Colors {

    // 1 - Using frequency hashmap
    public void sortColors1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        map.put(0,0);
        map.put(1,0);
        map.put(2,0);

        for (int num : nums) {
            map.put(num, map.get(num) + 1);
        }

        int idx = 0;

        for (int color = 0; color <= 2; color++) {
            int freq = map.get(color);

            for (int i = 0; i < freq; i++) {
                nums[idx] = color;
                idx++;
            }
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
