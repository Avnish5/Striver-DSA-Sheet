package DAY_12_HEAPS;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Merge_K_Sorted_Arrays {

    /**
     * 1. Brute-Force
     *
     * Time Complexity: O(n * m * log(n * m))
     *
     * Space Complexity: O(n * m)
     */
    public ArrayList<Integer> mergeArrays1(int[][] mat) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] arr : mat) {
            for (int val : arr) {
                pq.add(val);
            }
        }

        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }

        return ans;

    }

    static class Pair implements Comparable<Pair> {
        int row, index, val;

        public Pair(int row, int index, int val) {
            this.row = row;
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Pair o) {
            return this.val - o.val;
        }
    }

    /**
     * 2. Optimized Approach
     *
     * Time Complexity: O(n * m * log n)
     *   - There are n rows and m elements per row.
     *   - Each insertion/removal in heap costs O(log n).
     *   - Total operations: (n * m) insertions/removals → O(n * m * log n)
     *
     * Space Complexity: O(n)
     *   - Heap stores at most one element from each row.
     */
    public ArrayList<Integer> mergeArrays(int[][] mat) {
        ArrayList<Integer> ans = new ArrayList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        int n = mat.length;
        int m = mat[0].length;

        // Step 1: Add first element of each row to the heap
        for (int i = 0; i < n; i++) {
            pq.add(new Pair(i, 0, mat[i][0]));
        }

        // Step 2: Extract min and add next element from that row
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            ans.add(curr.val);

            int row = curr.row;
            int index = curr.index;

            if (index + 1 < m) {
                pq.add(new Pair(row, index + 1, mat[row][index + 1]));
            }
        }

        return ans;
    }
}
