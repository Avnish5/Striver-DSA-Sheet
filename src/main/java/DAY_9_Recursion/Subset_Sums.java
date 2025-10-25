package DAY_9_Recursion;

public class Subset_Sums {

    /**
     * 1. Using Recursion
     *
     *
     * 🔹 Time Complexity:  O(2^N)
     *   - Each element has two choices: include or exclude.
     *   - This results in 2^N possible recursive calls in the worst case.
     *
     * 🔹 Space Complexity: O(N)
     *   - Due to recursion stack depth (maximum depth = number of elements).
     *   - No additional data structures are used apart from recursion.
     */
    public Boolean recursionHelper(int arr[], int target, int currSum, int index) {
        // Base case: when all elements have been considered
        if (index == arr.length) {
            // Return true if current sum matches target
            return currSum == target;
        }

        //  Choice 1: Include current element in the sum
        Boolean include = recursionHelper(arr, target, currSum + arr[index], index + 1);

        //  Choice 2: Exclude current element from the sum
        Boolean exclude = recursionHelper(arr, target, currSum, index + 1);

        // Return true if either path achieves the target sum
        return include || exclude;
    }


    /**
     * 2. Using Memoization
     *
     * 🔹 Time Complexity:  O(N × Target)
     *   - Each state `(index, currSum)` is computed at most once.
     *   - For every state, we do O(1) work.
     *
     * 🔹 Space Complexity: O(N × Target)  (for DP table) + O(N) (for recursion stack)
     *   - DP table stores results for each combination of index and sum.
     *   - Recursion stack depth is O(N) in the worst case.
     */
    public Boolean recursionMemoizationHelper(int arr[], int target, int currSum, int index, Boolean[][] dp) {
        // Base case: all elements have been considered
        if (index == arr.length) {
            // Return true if the accumulated sum equals target
            return currSum == target;
        }

        // If this subproblem has been solved before, return stored result
        if (dp[index][currSum] != null) return dp[index][currSum];

        Boolean include = false;

        //  Choice 1: Include current element (only if within target limit)
        if (currSum + arr[index] <= target) {
            include = recursionMemoizationHelper(arr, target, currSum + arr[index], index + 1, dp);
        }

        //  Choice 2: Exclude current element
        Boolean exclude = recursionMemoizationHelper(arr, target, currSum, index + 1, dp);

        // Store and return the result for current state
        return dp[index][currSum] = include || exclude;
    }

    public Boolean isSubsetSum(int arr[], int sum) {
        Boolean[][] dp = new Boolean[arr.length + 1][sum + 1];
        return recursionMemoizationHelper(arr, sum, 0 , 0, dp);
    }
}
