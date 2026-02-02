package DAY_26_DP_PART_2;

import java.util.Arrays;
import java.util.Comparator;

public class Maximum_Profit_In_Job_Scheduling {
    /**
     * Binary Search Helper
     *
     * Finds the index of the next job whose start time
     * is greater than or equal to the given current job's end time.
     *
     * This ensures non-overlapping job selection.
     *
     * Time Complexity:
     * ----------------
     * O(log n)
     *
     * Space Complexity:
     * -----------------
     * O(1)
     */
    private int binarySearch(int[][] arr, int l, int n, int currJobEnd) {

        int r = n - 1;
        int result = n; // default means no next valid job

        while (l <= r) {
            int mid = l + (r - l) / 2;

            // If next job starts after or at current job's end
            if (arr[mid][0] >= currJobEnd) {
                result = mid;
                r = mid - 1;   // try to find an earlier valid job
            } else {
                l = mid + 1;
            }
        }

        return result;
    }

    /**
     * Recursive Helper Function
     *
     * Returns the maximum profit that can be achieved
     * starting from job index `i`.
     *
     * For each job, we have two choices:
     * 1. Take the job → add its profit and move to next non-overlapping job
     * 2. Skip the job → move to the next job
     *
     * Time Complexity:
     * ----------------
     * O(2^n)  (without memoization)
     *
     * Space Complexity:
     * -----------------
     * O(n) due to recursion stack
     */
    private int recursionHelper(int[][] arr, int n, int i) {

        // Base case: no more jobs left
        if (i >= n) {
            return 0;
        }

        // Find the next job that doesn't overlap
        int next = binarySearch(arr, i + 1, n, arr[i][1]);

        // Option 1: Take current job
        int taken = arr[i][2] + recursionHelper(arr, n, next);

        // Option 2: Skip current job
        int notTaken = recursionHelper(arr, n, i + 1);

        // Choose the option with maximum profit
        return Math.max(taken, notTaken);
    }

    /**
     * 1. Recursion
     *
     * Job Scheduling Function
     *
     * Converts input arrays into a single structure,
     * sorts jobs by start time, and computes maximum profit.
     *
     * Time Complexity:
     * ----------------
     * Sorting: O(n log n)
     * Recursion: O(2^n)
     * Binary Search inside recursion: O(log n)
     *
     * Overall: O(2^n)
     *
     *
     * Space Complexity:
     * -----------------
     * O(n) recursion stack
     * O(n) for job array
     */
    public int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {

        int n = startTime.length;
        int[][] arr = new int[n][3];

        // Combine start time, end time, and profit into one array
        for (int i = 0; i < n; i++) {
            arr[i][0] = startTime[i];
            arr[i][1] = endTime[i];
            arr[i][2] = profit[i];
        }

        // Sort jobs by start time
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        return recursionHelper(arr, n, 0);
    }

    /**
     * 2. Recursive Helper with Memoization
     *
     * Returns the maximum profit achievable starting
     * from job index `i`.
     *
     * At each job, we have two choices:
     * 1. Take the job → add its profit and jump to the next non-overlapping job
     * 2. Skip the job → move to the next job
     *
     * Memoization ensures each job index is computed only once.
     *
     * Time Complexity:
     * ----------------
     * O(n log n)
     *
     * Explanation:
     * - There are `n` unique states (job indices)
     * - Each state performs a binary search → O(log n)
     *
     *
     * Space Complexity:
     * -----------------
     * O(n)
     *
     * Explanation:
     * - Memo array of size n
     * - Recursion stack depth up to n
     */
    private int memoizationHelper(int[][] arr, int n, int i, int[] memo) {

        // Base case: no jobs left
        if (i >= n) {
            return 0;
        }

        // Return cached result if already computed
        if (memo[i] != -1) {
            return memo[i];
        }

        // Find next job that does not overlap
        int next = binarySearch(arr, i + 1, n, arr[i][1]);

        // Option 1: Take current job
        int taken = arr[i][2] + memoizationHelper(arr, n, next, memo);

        // Option 2: Skip current job
        int notTaken = memoizationHelper(arr, n, i + 1, memo);

        // Store and return the maximum profit
        memo[i] = Math.max(taken, notTaken);
        return memo[i];
    }

    /**
     * Job Scheduling (Main Function)
     *
     * Combines job details, sorts jobs by start time,
     * and computes the maximum profit using DP + binary search.
     *
     * Time Complexity:
     * ----------------
     * Sorting: O(n log n)
     * DP + Binary Search: O(n log n)
     *
     * Overall: O(n log n)
     *
     *
     * Space Complexity:
     * -----------------
     * O(n) for memo array
     * O(n) for job array
     * O(n) recursion stack
     */
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {

        int n = startTime.length;
        int[][] arr = new int[n][3];
        int[] memo = new int[n];

        // Initialize memo array with -1
        Arrays.fill(memo, -1);

        // Combine job details into a single array
        for (int i = 0; i < n; i++) {
            arr[i][0] = startTime[i]; // start time
            arr[i][1] = endTime[i];   // end time
            arr[i][2] = profit[i];    // profit
        }

        // Sort jobs by start time
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        return memoizationHelper(arr, n, 0, memo);
    }

    /**
     * Job data structure
     * Each job has a start time, end time, and profit.
     */
    static class Job {
        int startTime;
        int endTime;
        int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

    /**
     * 3. Bottom-Up Dynamic Programming
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n^2)
     *
     * Explanation:
     * - Sorting jobs by end time takes O(n log n)
     * - For each job, we scan backward to find the last non-overlapping job
     *   which in worst case takes O(n)
     * - Overall complexity is dominated by O(n^2)
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n)
     *
     * Explanation:
     * - Array of jobs of size n
     * - DP array of size n
     */

    public int jobScheduling3(int[] startTime, int[] endTime, int[] profit) {

        int n = startTime.length;

        // Create Job objects from input arrays
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        // Sort jobs by end time (earliest finish first)
        Arrays.sort(jobs, Comparator.comparingInt(j -> j.endTime));

        // dp[i] = maximum profit using jobs from index 0 to i (inclusive)
        int[] dp = new int[n];

        // Build the dp array iteratively
        for (int i = 0; i < n; i++) {

            // Profit if we take the current job
            int prev = 0;

            // Find the last job that does not overlap with current job
            for (int j = i - 1; j >= 0; j--) {
                if (jobs[i].startTime >= jobs[j].endTime) {
                    prev = dp[j];
                    break;
                }
            }

            int take = prev + jobs[i].profit; // Take current job
            int skip = (i > 0) ? dp[i - 1] : 0; // Skip current job

            // Choose the better option
            dp[i] = Math.max(take, skip);
        }

        // Maximum profit considering all jobs
        return dp[n - 1];
    }

    /**
     * Binary Search Helper
     *
     * Finds the index of the last job (before index `idx`)
     * whose endTime is less than or equal to jobs[idx].startTime.
     *
     * Time Complexity:
     * ----------------
     * O(log n)
     *
     * Space Complexity:
     * -----------------
     * O(1)
     */
    private int findLastNonOverlapping(Job[] jobs, int idx) {
        int low = 0, high = idx - 1;
        int res = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (jobs[mid].endTime <= jobs[idx].startTime) {
                res = mid;       // valid non-overlapping job
                low = mid + 1;   // try to find a later one
            } else {
                high = mid - 1;
            }
        }

        return res;
    }

    /**
     * Weighted Job Scheduling (Optimized with Binary Search)
     *
     * Time Complexity (TC):
     * ---------------------
     * O(n log n)
     *
     * Explanation:
     * - Sorting jobs by end time: O(n log n)
     * - DP loop runs n times
     * - Each DP state uses binary search: O(log n)
     * - Total: O(n log n)
     *
     *
     * Space Complexity (SC):
     * ----------------------
     * O(n)
     *
     * Explanation:
     * - Job array of size n
     * - DP array of size n
     * - Binary search uses constant extra space
     */
    public int jobScheduling4(int[] startTime, int[] endTime, int[] profit) {

        int n = startTime.length;

        Job[] jobs = new Job[n];
        int[] dp = new int[n];

        // Create Job objects
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        // Sort jobs by end time
        Arrays.sort(jobs, Comparator.comparingInt(j -> j.endTime));

        // Build DP array
        for (int i = 0; i < n; i++) {

            // Profit if current job is taken
            int prev = 0;
            int last = findLastNonOverlapping(jobs, i);
            if (last != -1) {
                prev = dp[last];
            }

            int take = prev + jobs[i].profit;
            int skip = (i > 0) ? dp[i - 1] : 0;

            // Choose max of taking or skipping current job
            dp[i] = Math.max(take, skip);
        }

        // Maximum profit using all jobs
        return dp[n - 1];
    }


}
