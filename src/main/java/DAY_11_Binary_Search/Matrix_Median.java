package DAY_11_Binary_Search;

public class Matrix_Median {

    /**
     * TC: O(n * log(m) * log(max - min))
     *     - Binary search on value range (log(max - min))
     *     - For each mid, count elements <= mid in each row (n rows)
     *     - Each count uses binary search in a row (log m)
     *
     * SC: O(1)
     *     - No extra space used apart from variables
     *
     * Approach:
     * 1. Since each row is sorted, we can binary search on the value range.
     * 2. Find the minimum and maximum possible values in the matrix.
     * 3. For a guessed value (mid), count how many elements are <= mid.
     * 4. If count <= (n*m)/2, median lies on the right side.
     *    Else, median lies on the left side.
     * 5. The smallest value satisfying the condition is the median.
     */
    public int median(int[][] mat, int n, int m) {

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        // Step 1: Find global minimum and maximum in matrix
        for (int i = 0; i < n; i++) {
            low = Math.min(low, mat[i][0]);       // first element of each row
            high = Math.max(high, mat[i][m - 1]); // last element of each row
        }

        // Index of median in sorted order (0-based)
        int required = (n * m) / 2;

        // Step 2: Binary search on value range
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = 0;

            // Count elements <= mid in all rows
            for (int i = 0; i < n; i++) {
                count += countSmallerEqual(mat[i], mid);
            }

            // If not enough elements on left side, move right
            if (count <= required) {
                low = mid + 1;
            }
            // Otherwise, move left
            else {
                high = mid - 1;
            }
        }

        // 'low' is the smallest value such that count(<= low) > required
        return low;
    }

    private int countSmallerEqual(int[] row, int target) {
        int l = 0, r = row.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (row[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        // 'l' is the index of first element > target
        return l;
    }
}
