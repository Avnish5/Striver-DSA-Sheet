package DAY_16_String_Part_2;

import java.util.Arrays;

public class Minimum_Insertion_Steps_to_Make_a_String_Palindrome {


    private int recursion(String s, int i, int j) {
        // Base case: If substring length is 0 or 1, no insertions needed.
        if (i >= j) {
            return 0;
        }

        // If characters at i and j match, move inward without insertion.
        if (s.charAt(i) == s.charAt(j)) {
            return recursion(s, i + 1, j - 1);
        }

        // If characters don't match, insert on either side and take min insertions + 1.
        return 1 + Math.min(recursion(s, i + 1, j), recursion(s, i, j - 1));
    }

    /**
     * 1. Recursive solution
     *
     * Time Complexity:
     *   - Worst-case: O(2^n)
     *     Each call can branch into two calls when characters don't match,
     *     leading to an exponential number of calls without memoization.
     *
     * Space Complexity: O(n)
     *   - Due to the recursion call stack, where n is the length of the string.
     */
    public int minInsertions1(String s) {
        // Start recursion for entire string.
        return recursion(s, 0, s.length() - 1);
    }

    private int recursionWithMemo(String s, int i, int j, int[][] memo) {
        // Base case: substring length 0 or 1 needs no insertions
        if (i >= j) {
            return 0;
        }

        // If result already computed, return from memo
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // If characters match, move inward without insertion
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = recursionWithMemo(s, i + 1, j - 1, memo);
        } else {
            // Characters don't match: try inserting either side and take minimum + 1
            memo[i][j] = 1 + Math.min(recursionWithMemo(s, i + 1, j, memo),
                    recursionWithMemo(s, i, j - 1, memo));
        }

        return memo[i][j];
    }

    /**
     * 2. Recursive solution with memoization to find the minimum insertions
     *    needed to make a string palindrome.
     *
     * Time Complexity:
     *   - O(n^2)
     *     Each unique substring (i, j) is computed once and stored in memo.
     *
     * Space Complexity:
     *   - O(n^2) for the memoization table,
     *   - plus O(n) for the recursion call stack.
     */
    public int minInsertions2(String s) {
        // Initialize memo table with -1 to mark uncomputed states
        int[][] memo = new int[501][501];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }

        // Start recursion with memo for full string
        return recursionWithMemo(s, 0, s.length() - 1, memo);
    }

}
