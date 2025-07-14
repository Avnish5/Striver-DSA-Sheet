package DAY_3_Arrays_Part_3;

import java.util.ArrayList;
import java.util.Arrays;

public class Reverse_Pairs {

    /**
     * 1. Brute Force Approach
     *

     * Time Complexity: O(n^2)
     *   - Two nested loops: outer loop runs n times, inner loop runs up to n times → O(n^2)
     *
     * Space Complexity: O(1)
     *   - No extra space used beyond a few scalar variables → O(1)
     */
    public int reversePairs1(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((long)nums[i] > 2L * nums[j]) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void merge(int[] arr, int start,int mid, int end) {

        int left = start;
        int right = mid + 1;
        ArrayList<Integer> temp = new ArrayList<>();

        while (left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        while (right <= end) {
            temp.add(arr[right]);
            right++;
        }

        for (int i = start; i <= end; i++) {
            arr[i] = temp.get(i - start);
        }

    }

    private int countReversePairs(int[] arr, int start, int mid, int end) {
        int count = 0;
        int j = mid +1;

        for (int i = start; i <= mid; i++) {
            while (j <= end && (long)arr[i] > (long) 2 * arr[j] ) {
                j++;
            }

            count += (j - (mid + 1));
        }

        return count;
    }
    private int mergeSort(int[] arr, int start, int end) {
        int cnt = 0;
        if (start < end) {
            int mid = (start + end) /2;
            cnt += mergeSort(arr, start, mid);
            cnt += mergeSort(arr, mid+1, end);
            cnt += countReversePairs(arr, start, mid, end);
            merge(arr, start, mid, end);
        }

        return cnt;
    }

    /**
     * 2- Optimal using merge sort
     *
     * Time Complexity: O(n log n)
     *   - Merge Sort takes O(n log n) time for divide and merge operations.
     *
     * Space Complexity: O(n)
     *   - Temporary arrays are used during merging.
     */
    public int reversePairs2(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

}
