package DAY_2_Arrays_Part_2;

import java.util.Arrays;

public class Merge_Sorted_Array {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int zeroIndex = -1;
        for (int i = 0; i < m; i++) {
            if (nums1[i] == 0) {
                zeroIndex = i;
                break;
            }
        }

        for (int j = 0; j < n; j++) {
            nums1[zeroIndex]= nums2[j];
            zeroIndex++;
        }

        Arrays.sort(nums1);
    }

}
