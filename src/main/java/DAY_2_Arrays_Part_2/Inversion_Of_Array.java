package DAY_2_Arrays_Part_2;

import java.util.ArrayList;

public class Inversion_Of_Array {

    /**
     * 1. Brute Force
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int inversionCount1(int arr[]) {
        int n = arr.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }

        return ans;
    }


    private int merge(int[] arr, int start,int mid, int end) {
        int cnt = 0;
        int left = start;
        int right = mid+1;
        int[] temp = new int[(end - start) + 1];
        int k = 0;

        while(left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                temp[k++] = arr[left++];
            } else {
                temp[k++] = arr[right++];
                cnt += (mid - left + 1);
            }
        }

        while (left <= mid) {
            temp[k++] = arr[left++];
        }

        while (right <= end) {
            temp[k++] = arr[right++];
        }

       for (int p = 0; p < temp.length; p++) {
           arr[start + p] = temp[p];
       }

        return cnt;

    }
    private int mergeSort(int[] arr, int start, int end) {
        int cnt = 0;
        if (start < end) {
            int mid = start + (end - start) /2;
            cnt += mergeSort(arr, start, mid);
            cnt += mergeSort(arr,mid+1, end);
            cnt += merge(arr, start, mid, end);

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
    private  int inversionCount2(int arr[]) {
        int n = arr.length;
        return  mergeSort(arr, 0, n-1);

    }
}
