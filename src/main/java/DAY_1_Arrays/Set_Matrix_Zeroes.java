package DAY_1_Arrays;

import java.util.*;

public class Set_Matrix_Zeroes {


    /**
     * 1.
     *
     * Time Complexity: O(m * n)
     * - One pass to record rows and columns with zeros
     * - One pass to set rows and columns to zero
     *
     * Space Complexity: O(m + n)
     * - Uses two HashMaps to store affected rows and columns
     */
    public void setZeroes1(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] zeroRows = new boolean[m];
        boolean[] zeroColumns = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    zeroRows[i] = true;
                    zeroColumns[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                    if (zeroRows[i] || zeroColumns[j]) {
                        matrix[i][j] = 0;
                    }
                }
            }
    }

    /**
     * 2.
     *
     * Time Complexity: O(m * n)
     *
     * Space Complexity: O(1)
     * - No extra data structures are used.
     * - The first row and first column of the matrix are reused as markers.
     * - Only two boolean variables (firstrow, firstcol) are used.
     * - Hence auxiliary space is constant.
     */
    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        // Flags to track if the first row or first column originally contains a zero
        boolean firstrow = false;
        boolean firstcol = false;

        // Traverse the matrix and mark rows and columns that need to be zero
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n ; j++) {

                if(matrix[i][j] == 0) {

                    // Check if zero appears in first row
                    if(i == 0) firstrow = true;

                    // Check if zero appears in first column
                    if(j == 0) firstcol = true;

                    // Mark the row and column using first row and first column
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Update the inner matrix using the markers
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n ; j++) {

                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // If first row originally had a zero, set the entire first row to zero
        if(firstrow) {
            for(int i = 0; i < n ; i++) {
                matrix[0][i] = 0;
            }
        }

        // If first column originally had a zero, set the entire first column to zero
        if(firstcol) {
            for(int i = 0; i < m ; i++) {
                matrix[i][0] = 0;
            }
        }
    }



}
