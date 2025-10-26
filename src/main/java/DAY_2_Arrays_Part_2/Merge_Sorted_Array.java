package DAY_2_Arrays_Part_2;

import java.util.Arrays;
import java.util.HashMap;
public class Merge_Sorted_Array {
    /**
     * TC : 0(m+ n)
     *
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize pointers for nums1, nums2, and the merged array
        int i = m - 1;          // Pointer for the last element in the initial part of nums1
        int j = n - 1;          // Pointer for the last element in nums2
        int k = (m + n) - 1;    // Pointer for the last position in nums1 (where merged elements go)

        // Merge nums1 and nums2 starting from the end
        while (i >= 0 && j >= 0) {
            // Place the larger element at the current position k in nums1
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        String s = new String("ff").intern();

        // If there are remaining elements in nums2, copy them over to nums1
        // No need to copy remaining elements from nums1 because they are already in place
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }


}
