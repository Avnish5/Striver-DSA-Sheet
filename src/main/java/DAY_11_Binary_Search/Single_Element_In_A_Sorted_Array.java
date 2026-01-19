package DAY_11_Binary_Search;

import java.util.Arrays;

public class Single_Element_In_A_Sorted_Array {

    /**
     * 1. Brute-Force
     *
     * Time Complexity (TC): O(n)
     * - The array is traversed once to find the single element.
     *
     * Space Complexity (SC): O(1)
     * - No extra space is used apart from constant variables.
     */
    public int singleNonDuplicate1(int[] nums) {
        int n = nums.length;

        // Edge case: if array has only one element
        if (n == 1) return nums[0];

        // Traverse the array to find the single element
        for (int i = 0; i < n; i++) {

            // Case 1: First element
            // It is single if it is not equal to the next element
            if (i == 0) {
                if (nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }

            // Case 2: Last element
            // It is single if it is not equal to the previous element
            else if (i == n - 1) {
                if (nums[i] != nums[i - 1]) {
                    return nums[i];
                }
            }

            // Case 3: Middle elements
            // It is single if it is different from both neighbors
            else {
                if (nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) {
                    return nums[i];
                }
            }
        }

        // This return will never be hit for valid input
        return -1;
    }

    /**
     * 2. Using Binary Search
     *
     * Time Complexity (TC): O(log n)
     * - Binary search is used, reducing the search space by half each iteration.
     *
     * Space Complexity (SC): O(1)
     * - Only constant extra space is used.
     */
    public int singleNonDuplicate2(int[] nums) {
        int n = nums.length;

        // Edge case: if array has only one element
        if (n == 1) return nums[0];

        // Check if the single element is at the beginning
        if (nums[0] != nums[1]) return nums[0];

        // Check if the single element is at the end
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

        // Binary search range excluding the edges
        int low = 1, high = n - 2;

        while (low <= high) {

            // Find middle index
            int mid = (low + high) / 2;

            // If middle element is different from both neighbors, it is the answer
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }

            /*
             * Pairing logic:
             * - Before the single element:
             *   even index -> paired with next element
             *   odd index  -> paired with previous element
             *
             * If this pattern holds, move to the right side.
             */
            else if (
                    (mid % 2 == 1 && nums[mid] == nums[mid - 1]) ||
                            (mid % 2 == 0 && nums[mid] == nums[mid + 1])
            ) {
                low = mid + 1;
            }

            /*
             * Otherwise, the pairing pattern is broken,
             * so the single element lies on the left side.
             */
            else {
                high = mid - 1;
            }
        }

        // This return will never be reached for valid input
        return -1;
    }

    public int helper(int i, int j, String s1, String s2, int[][] memo) {
        if(i >= s1.length() && j >= s2.length()) {
            return 0;
        }

        if(memo[i][j] != -1) {
            return memo[i][j];
        }

        if(i >= s1.length()) {
            return  memo[i][j]= (int)s2.charAt(j) + helper(i, j + 1, s1, s2, memo);
        }

        if(j >= s2.length()) {
            return  memo[i][j]=(int)s1.charAt(i) + helper(i + 1,  j, s1, s2, memo);
        }

        if(s1.charAt(i) == s2.charAt(j)) {
            return  memo[i][j]= helper(i+1, j+1, s1, s2, memo);
        }

        int option1 =  (int)s1.charAt(i) + helper(i + 1,  1, s1, s2, memo);
        int option2 = (int)s2.charAt(j) + helper(i, j + 1, s1, s2, memo);

        return memo[i][j]=Math.min(option2, option1);
    }
    public int minimumDeleteSum(String s1, String s2) {
        int[][] memo = new int[1001][1001];
        for(int i = 0; i < 1001; i++) {
            Arrays.fill(memo[i], -1);
        }
       return helper(0, 0 ,s1, s2, memo);
    }



}
