package DAY_14_STACK_AND_QUEUE_PART_2;

import java.util.Stack;

public class Celebrity_Problem {

    /**
     * 1. Brute Force
     *
     * Time Complexity: O(n^2)
     * - We check every pair (i, j) in the matrix.
     *
     * Space Complexity: O(1)
     * - No extra space is used apart from variables.
     */
    public int celebrity1(int mat[][]) {
        int n = mat.length;

        for(int i = 0; i < n; i++) {
            boolean knowsSomeone = false;   // True if person i knows anyone
            boolean knownByAll = true;      // True if everyone knows person i

            for(int j = 0; j < n; j++) {
                if(i != j) {
                    // Check if i knows j
                    if(mat[i][j] == 1) {
                        knowsSomeone = true;
                    }

                    // Check if j does NOT know i
                    if(mat[j][i] == 0) {
                        knownByAll = false;
                    }
                }
            }

            // If both conditions satisfy, i is the celebrity
            if(!knowsSomeone && knownByAll) return i;
        }

        return -1;
    }



    /**
     * 2. Optimized
     *
     * Time Complexity: O(n)
     * - Each elimination reduces one person → O(n)
     * - Verification takes O(n)
     *
     * Space Complexity: O(n)
     * - Stack stores all people
     */
    public int celebrity2(int mat[][]) {
        int n = mat.length;
        Stack<Integer> stack = new Stack<>();

        // Step 1: Push all people into stack
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }

        // Step 2: Eliminate non-celebrities
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();

            if (mat[a][b] == 1) {
                // a knows b → a is not celebrity
                stack.push(b);
            } else {
                // a does not know b → b is not celebrity
                stack.push(a);
            }
        }

        // Step 3: Get candidate
        int candidate = stack.pop();

        // Step 4: Verify candidate
        for (int i = 0; i < n; i++) {
            if (i != candidate) {
                // Candidate should not know anyone
                if (mat[candidate][i] == 1) return -1;

                // Everyone should know candidate
                if (mat[i][candidate] == 0) return -1;
            }
        }

        return candidate;
    }
}
