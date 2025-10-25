package DAY_12_HEAPS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Find_Median_from_Data_Stream_Brute {

    /**
     *Time complexity: O(n * log n)
     * Space complexity: O(n)
     */
    private List<Integer> nums;

    public Find_Median_from_Data_Stream_Brute() {
        nums = new ArrayList<>();
    }

    public void addNum(int num) {
        nums.add(num);
    }

    public double findMedian() {
        // Sort every time we need the median
        Collections.sort(nums);

        int n = nums.size();
        if (n % 2 == 1) {
            return nums.get(n / 2);  // Odd -> middle element
        } else {
            return (nums.get(n / 2 - 1) + nums.get(n / 2)) / 2.0;  // Even -> mean of two middle
        }
    }

    public void rotate(int[][] matrix) {
      int n = matrix.length;

      for (int i = 0; i < n ; i++) {
          for (int j = 0; j < n; j++) {
              int temp = matrix[i][j];
              matrix[i][j] = matrix[j][i];
              matrix[j][i] = temp;
          }
      }

      for (int[] mat : matrix) {
          reverse(mat);
      }
    }

    public void reverse(int[] arr) {
        int low = 0 , high = arr.length - 1;

        while (low <= high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }
}
