package Day_1_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    // 2. Hash-Map
    public void setZeroes2(int[][] matrix) {
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

        HashMap<Integer, Boolean> rowMap = new HashMap<>();
        HashMap<Integer, Boolean> columnMap = new HashMap<>();

        for (List<Integer> cordinate : cordinatesList) {
            int row = cordinate.get(0);
            int column = cordinate.get(1);

            if (! rowMap.containsKey(row)) {
                for (int i = 0; i < columnSize; i++) {
                    matrix[row][i] = 0;
                }
            }

            if (!columnMap.containsKey(column)) {
                for (int j = 0; j < rowSize; j++) {
                    matrix[j][column] = 0;
                }
            }
            rowMap.put(row, true);
            columnMap.put(column, true);

        }
    }

    //3. Optimized
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
            if (zeroRows[i]) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < n; j++) {
            if (zeroColumns[j]) {
                for (int i = 0; i < m; i++) {
                    matrix[j][i] = 0;
                }
            }
        }
    }
}
