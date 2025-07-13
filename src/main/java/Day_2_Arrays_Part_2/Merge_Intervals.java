package Day_2_Arrays_Part_2;

import java.util.*;

public class Merge_Intervals {

    /**
     *
     * Time Complexity: O(n log n)
     *   - O(n log n) for sorting the intervals based on start time
     *   - O(n) for iterating through the intervals
     *
     * Space Complexity: O(n)
     *   - For the output list `merged` which can store up to n intervals
     *   - Sorting uses O(log n) space if done in-place, or more depending on implementation
     */
    public int[][] merge(int[][] intervals) {
        System.out.println();
        // Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();

        // Start with the first interval
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] last = merged.get(merged.size() - 1);
            int[] current = intervals[i];

            // Check if current interval overlaps with last merged interval
            if (current[0] <= last[1]) {
                // Merge by updating the end time to the max end time
                last[1] = Math.max(last[1], current[1]);
            } else {
                // No overlap, add current interval as is
                merged.add(current);
            }
        }

        // Convert the list back to 2D array
        return merged.toArray(new int[merged.size()][]);
    }

}
