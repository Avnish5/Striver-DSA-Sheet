package DAY_9_Recursion;

import java.util.ArrayList;

public class Kth_Permutation_Sequence {

    /**
     * T.C : O(n^2)
     *      - We iterate n times.
     *      - In each iteration, removing an element from ArrayList takes O(n) (shift of elements).
     *      => Total = n * n = O(n^2)
     *
     * S.C : O(n)
     *      - ArrayList of size n
     *      - Result string builder of size n
     *      => O(n)
     */
    public String getPermutation(int n, int k) {
        int fact = 1;
        ArrayList<Integer> list = new ArrayList<>();

        // Step 1: Compute factorial of n and fill the list with 1..n
        for (int i = 1; i <= n; i++) {
            list.add(i);
            fact *= i;
        }

        StringBuilder sb = new StringBuilder();
        k--; // Convert k to 0-based index

        // Step 2: Build the k-th permutation
        for (int i = 0; i < n; i++) {
            fact = fact / (n - i);     // Reduce factorial block size
            int index = k / fact;      // Select index from remaining numbers
            sb.append(list.get(index)); // Add selected number to result
            list.remove(index);         // Remove used number
            k = k % fact;              // Reduce k for the next block
        }

        return sb.toString();
    }

}
