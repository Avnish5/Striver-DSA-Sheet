package DAY_1_Arrays;

import java.util.*;

public class Set_Matrix_Zeroes {

    // 1. Brute-Force
    public void setZeroes1(int[][] matrix) {
        int rowSize = matrix.length;
        int columnSize = matrix[0].length;

        List<List<Integer>> cordinatesList = new ArrayList<>();

        for (int i =0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (matrix[i][j] == 0) {
                    cordinatesList.add(Arrays.asList(i, j));
                }
            }
        }
        for (List<Integer> cordinate : cordinatesList) {
            int row = cordinate.get(0);
            int column = cordinate.get(1);

            for (int i = 0; i < columnSize; i++) {
                matrix[row][i] = 0;
            }

            for (int j = 0; j < rowSize; j++) {
                matrix[j][column] = 0;
            }
        }
    }

    /**
     * 2. Using Hash-Map
     *
     * Time Complexity: O(m * n)
     * - One pass to record rows and columns with zeros
     * - One pass to set rows and columns to zero
     *
     * Space Complexity: O(m + n)
     * - Uses two HashMaps to store affected rows and columns
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // To store which rows and columns should be set to zero
        Map<Integer, Boolean> rowMap = new HashMap<>();
        Map<Integer, Boolean> colMap = new HashMap<>();

        // First pass: mark rows and columns that contain a zero
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    // Record the row and column index
                    rowMap.put(i, true);
                    colMap.put(j, true);
                }
            }
        }

        // Second pass: update elements to zero if their row or column was marked
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n; j++) {
                // If the row OR column is marked, set the current element to zero
                if (rowMap.containsKey(i) || colMap.containsKey(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 3. Without  Using Hash-Map
     *
     * Time Complexity: O(m * n)
     * - One pass to record rows and columns with zeros
     * - One pass to set rows and columns to zero
     *
     * Space Complexity: O(m + n)
     * - Uses two HashMaps to store affected rows and columns
     */
    public void setZeroes3(int[][] matrix) {

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



}
