package DAY_11_Binary_Search;

public class Single_Element_In_A_Sorted_Array {

    /**
     * 1. Brute-Force
     *
     * Time Complexity (TC): O(n)
     * - The array is traversed once to find the single element.
     *
     * Space Complexity (SC): O(1)
     * - No extra space is used apart from constant variables.
     */
    public int singleNonDuplicate1(int[] nums) {
        int n = nums.length;

        // Edge case: if array has only one element
        if (n == 1) return nums[0];

        // Traverse the array to find the single element
        for (int i = 0; i < n; i++) {

            // Case 1: First element
            // It is single if it is not equal to the next element
            if (i == 0) {
                if (nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }

            // Case 2: Last element
            // It is single if it is not equal to the previous element
            else if (i == n - 1) {
                if (nums[i] != nums[i - 1]) {
                    return nums[i];
                }
            }

            // Case 3: Middle elements
            // It is single if it is different from both neighbors
            else {
                if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
        }

        // This return will never be hit for valid input
        return -1;
    }

    /**
     * 2. Using Binary Search
     *
     * Time Complexity (TC): O(log n)
     * - Binary search is used, reducing the search space by half each iteration.
     *
     * Space Complexity (SC): O(1)
     * - Only constant extra space is used.
     */
    public int singleNonDuplicate2(int[] nums) {
        int n = nums.length;

        // Edge case: if array has only one element
        if (n == 1) return nums[0];

        // Check if the single element is at the beginning
        if (nums[0] != nums[1]) return nums[0];

        // Check if the single element is at the end
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

        // Binary search range excluding the edges
        int low = 1, high = n - 2;

        while (low <= high) {

            // Find middle index
            int mid = (low + high) / 2;

            // If middle element is different from both neighbors, it is the answer
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            /*
             * Pairing logic:
             * - Before the single element:
             *   even index -> paired with next element
             *   odd index  -> paired with previous element
             *
             * If this pattern holds, move to the right side.
             */
            else if (
                    (mid % 2 == 1 && nums[mid] == nums[mid - 1]) ||
                            (mid % 2 == 0 && nums[mid] == nums[mid + 1])
            ) {
                low = mid + 1;
            }

            /*
             * Otherwise, the pairing pattern is broken,
             * so the single element lies on the left side.
             */
            else {
                high = mid - 1;
            }
        }

        // This return will never be reached for valid input
        return -1;
    }


}
