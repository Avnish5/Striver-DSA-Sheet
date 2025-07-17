package DAY_7_LINKED_LIST_And_Array;

public class Trapping_Rain_Water {

    /**
     * 1. Brute Force Approach
     *
     * Time Complexity: O(n^2) - for each element, we traverse left and right to find max
     * Space Complexity: O(1) - no extra space used (excluding input)
     */
    public int trap1(int[] height) {
        int waterTrapped = 0;               // Total water trapped
        int n = height.length;              // Total number of bars

        // Skip the first and last bar since water can't be trapped at the ends
        for (int i = 1; i < n - 1; i++) {
            int maxLeft = 0;               // Maximum height to the left of current bar (including current)
            int maxRight = 0;              // Maximum height to the right of current bar (including current)

            // Find the tallest bar on the left side of i (including i)
            for (int j = i; j >= 0; j--) {
                maxLeft = Math.max(maxLeft, height[j]);
            }

            // Find the tallest bar on the right side of i (including i)
            for (int j = i; j < n; j++) {
                maxRight = Math.max(maxRight, height[j]);
            }

            // Water trapped on top of current bar is min(maxLeft, maxRight) - height[i]
            // This represents the vertical space above the bar that can hold water
            waterTrapped += Math.min(maxLeft, maxRight) - height[i];
        }

        return waterTrapped;
    }

    private int[] leftMaxArray(int[] height, int n) {
        int[] leftMaxArr = new int[n];
        leftMaxArr[0] = height[0];

        for (int i = 1; i < n; i++) {
            leftMaxArr[i] = Math.max(height[i], leftMaxArr[i-1]);
        }

        return leftMaxArr;
    }

    private int[] rightMaxArray(int[] height, int n) {
        int[] rightMaxArr = new int[n];
        rightMaxArr[n-1] = height[n-1];

        for (int i = n-2; i >= 0; i--) {
            rightMaxArr[i] = Math.max(height[i], rightMaxArr[i+1]);
        }

        return rightMaxArr;
    }

    /**
     * 2. Optimal approach using precomputed arrays.
     *
     * Time Complexity: O(n) - single pass to create leftMax and rightMax arrays, and one pass to compute result
     * Space Complexity: O(n) - two extra arrays of size n for leftMax and rightMax
     */
    public int trap2(int[] height) {
        int waterTrapped = 0;
        int n = height.length;

        // Precompute maximum height to the left of each bar
        int[] leftMaxArr = leftMaxArray(height, n);

        // Precompute maximum height to the right of each bar
        int[] rightMaxArr = rightMaxArray(height, n);

        // Calculate trapped water at each bar using the precomputed max heights
        for (int i = 0; i < n; i++) {
            // Water trapped on top of the current bar is the minimum of max heights on both sides minus current height
            waterTrapped += Math.min(leftMaxArr[i], rightMaxArr[i]) - height[i];
        }
        return waterTrapped;
    }

    /**
     * 3. More Optimized using Two-pointer approach.
     *
     * Time Complexity: O(n) - single pass through the array
     * Space Complexity: O(1) - only constant extra variables used
     */
    public int trap3(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int waterTrapped = 0;

        int maxL = 0, maxR = 0;

        while (left < right) {
            maxL = Math.max(maxL, height[left]);
            maxR = Math.max(maxR, height[right]);

            if (maxL < maxR) {
                waterTrapped +=maxL - height[left];
                left++;
            } else {
                waterTrapped += maxR - height[right];
                right--;
            }
        }

        return waterTrapped;
    }


}
