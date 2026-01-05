package DAY_11_Binary_Search;

public class Search_In_Rotated_Sorted_Array {

    public int findPivot(int[] nums) {
        int low = 0, high = nums.length - 1;

        // Binary search to locate the pivot
        while (low < high) {

            // Calculate mid safely to avoid overflow
            int mid = low + (high - low) / 2;

            /*
             * If mid element is greater than the last element,
             * then the pivot lies to the right of mid.
             */
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            }
            /*
             * Otherwise, the pivot lies at mid or to the left of mid.
             */
            else {
                high = mid;
            }
        }

        /*
         * At the end of the loop,
         * low == high and points to the pivot index.
         */
        return high;
    }

    /*
     * Standard binary search on a sorted subarray.
     * Returns the index of the target if found, otherwise -1.
     */
    public int binarySearch(int[] nums, int low, int high, int target) {
        int idx = -1;

        // Perform binary search within the given bounds
        while (low <= high) {

            // Calculate mid safely
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                idx = mid;
                break;
            }
            // Target lies in the left half
            else if (nums[mid] > target) {
                high = mid - 1;
            }
            // Target lies in the right half
            else {
                low = mid + 1;
            }
        }

        return idx;
    }

    /**
     * Time Complexity (TC):
     * - findPivot(): O(log n)
     * - binarySearch(): O(log n)
     * - search(): O(log n) overall
     *
     * Space Complexity (SC): O(1)
     * - Only constant extra space is used.
     */
    public int search(int[] nums, int target) {

        // Step 1: Find pivot index
        int pivot = findPivot(nums);

        // Step 2: Search in the left sorted part
        int idx = binarySearch(nums, 0, pivot - 1, target);
        if (idx != -1) {
            return idx;
        }

        // Step 3: Search in the right sorted part
        return binarySearch(nums, pivot, nums.length - 1, target);
    }

}
