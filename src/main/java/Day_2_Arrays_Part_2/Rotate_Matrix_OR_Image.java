package Day_2_Arrays_Part_2;

public class Rotate_Matrix_OR_Image {

    /**
     * Time Complexity: O(N^2)
     *
     * Space Complexity: O(1)
     */
    public void rotate(int[][] matrix) {
        // Get the dimensions of the matrix (assumes a square matrix)
        int m = matrix.length;
        int n = matrix[0].length;

        // Step 1: Transpose the matrix
        // Swap matrix[i][j] with matrix[j][i] for all i < j
        // TC for transpose: O(N^2), SC: O(1)
        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        // TC for reversing all rows: O(N^2), SC: O(1)
        for (int[] row : matrix) {
            reverseRow(row);
        }

        // Overall Time Complexity: O(N^2)
        // Overall Space Complexity: O(1)
    }

    /**
     * Reverses a single row of the matrix in place
     * @param row The row to be reversed
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    private void reverseRow(int[] row) {
        int left = 0;
        int right = row.length - 1;

        // Swap elements from both ends toward the center
        while (left < right) {
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }

}
