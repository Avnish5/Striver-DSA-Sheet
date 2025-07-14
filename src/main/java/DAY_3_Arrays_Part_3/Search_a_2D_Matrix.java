package DAY_3_Arrays_Part_3;

import java.util.ArrayList;
import java.util.List;

public class Search_a_2D_Matrix {
        /**
         * Time Complexity: O(m + n)
         *   - O(m) to find the potential row that may contain the target.
         *   - O(n) to linearly search through that row.

         * Space Complexity: O(1)
         *   - No additional space is used aside from a few variables.
         */
        public boolean searchMatrix1(int[][] matrix, int target) {
            int m = matrix.length;       // Number of rows
            int n = matrix[0].length;    // Number of columns

            // Step 1: Identify the row in which the target could exist.
            int rowIndex = -1;
            for (int i = 0; i < m; i++) {
                // Check if target is in the range of this row
                if (target >= matrix[i][0] && target <= matrix[i][n - 1]) {
                    rowIndex = i;
                    break;
                }
            }

            // If no such row is found, target doesn't exist in matrix
            if (rowIndex == -1) return false;

            // Step 2: Linearly search for the target in the identified row
            for (int j = 0; j < n; j++) {
                if (matrix[rowIndex][j] == target) return true;
            }

            // If not found
            return false;
        }


    /**
     * Time Complexity: O(log m + log n)
     *   - Binary search to find the correct row → O(log m)
     *   - Binary search within the row → O(log n)
     *
     * Space Complexity: O(1)
     *   - No extra space used
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int top = 0;
        int bot = matrix.length - 1;

        // Step 1: Binary search to locate the correct row
        while (top <= bot) {
            int mid = (top + bot) / 2;

            if (matrix[mid][0] < target && matrix[mid][matrix[mid].length - 1] > target) {
                break; // found the row
            } else if (matrix[mid][0] > target) {
                bot = mid - 1;
            } else {
                top = mid +1;
            }
        }

        int row = (top + bot) / 2;
        int left = 0;
        int right = matrix[row].length - 1;

        // Step 2: Binary search within the identified row
        while(left <= right) {
            int mid = (left + right) / 2;

            if (matrix[row][mid] == target) {
                return true;
            } else if (matrix[row][mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }



}


