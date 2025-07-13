package Day_2_Arrays_Part_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Repated_And_MissingNumber {

    /**
     * 1. Brute Force using hashset
     * Time Complexity: O(n) - single pass through array and set
     * Space Complexity: O(n) - for the HashSet
     */
    ArrayList<Integer> findTwoElement1(int arr[]) {
        // HashSet to track seen elements
        Set<Integer> set = new HashSet<>();

        // Variable to store the repeating number
        int repeatedNumber = -1;

        // Total number of elements
        int n = arr.length;

        // Traverse array to identify the repeated number
        for (int val : arr) {
            if (set.contains(val)) {
                // If already in set, it's the repeating number
                repeatedNumber = val;
            } else {
                // Otherwise, add to set
                set.add(val);
            }
        }

        // Calculate expected sum of first n natural numbers using long to avoid overflow
        long expectedSum = (long) n * (n + 1) / 2;

        // Calculate the actual sum of unique elements from the set
        long actualSum = 0;
        for (int num : set) {
            actualSum += num;
        }

        // The missing number is the difference between expected and actual sum
        int missingNumber = (int) (expectedSum - actualSum);

        // Prepare result list: [repeating, missing]
        ArrayList<Integer> result = new ArrayList<>();
        result.add(repeatedNumber);   // The number that appears twice
        result.add(missingNumber);    // The number that is missing

        return result;
    }


    /**
     * 2. Optimal Using math
     * Time Complexity:  O(n)     — one pass through the array
     * Space Complexity: O(1)     — constant extra space (no HashSet used)
     */
    public ArrayList<Integer> findTwoElement2(int arr[]) {
        int n = arr.length;

        // Calculate expected sum of first n natural numbers
        long sumN = (long) n * (n + 1) / 2;

        // Calculate expected sum of squares of first n natural numbers
        long sumNSq = (long) n * (n + 1) * (2 * n + 1) / 6;

        long sumActual = 0;      // To hold the actual sum of array elements
        long sumSqActual = 0;    // To hold the actual sum of squares of array elements

        // Compute actual sum and sum of squares from the input array
        for (int num : arr) {
            sumActual += num;
            sumSqActual += (long) num * num;
        }

        // Let x = repeating number, y = missing number
        // diff = x - y
        long diff = sumActual - sumN;

        // squareDiff = x^2 - y^2 = (x - y)(x + y)
        long squareDiff = sumSqActual - sumNSq;

        // sum = x + y
        long sum = squareDiff / diff;

        // Solve equations:
        // x = (diff + sum) / 2
        // y = x - diff
        long x = (diff + sum) / 2;     // Repeating number
        long y = x - diff;             // Missing number

        // Prepare and return result
        ArrayList<Integer> result = new ArrayList<>();
        result.add((int) x);  // Add repeating number
        result.add((int) y);  // Add missing number
        return result;
    }



}
