package DAY_3_Arrays_Part_3;

import java.util.ArrayList;
import java.util.List;

public class Majority_Element_2 {

    /**
     * Time Complexity: O(n)
     *   - First pass to find potential candidates → O(n)
     *   - Second pass to verify actual frequencies → O(n)
     *   - Total → O(n + n) = O(n)
     *
     * Space Complexity: O(1)
     *   - Only a fixed number of variables and an output list of at most 2 elements → O(1)
     */

    public List<Integer> majorityElement(int[] nums) {
        // Initialize the first and second majority candidates and their counts
        int maj1 = -1;
        int count1 = 0;

        int maj2 = -1;
        int count2 = 0;

        // Minimum frequency required for an element to be considered a majority (> n/3)
        int condition = (nums.length / 3) + 1;

        // First pass: find potential candidates for majority elements
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == maj1) {
                count1++; // Increment count for first candidate
            } else if (nums[i] == maj2) {
                count2++; // Increment count for second candidate
            } else if (count1 == 0) {
                maj1 = nums[i]; // Assign new candidate to maj1
                count1 = 1;
            } else if (count2 == 0) {
                maj2 = nums[i]; // Assign new candidate to maj2
                count2 = 1;
            } else {
                count1--; // Decrement both counts if no match
                count2--;
            }
        }

        // Second pass: verify the actual frequencies of the candidates
        List<Integer> ans = new ArrayList<>();
        int freq1 = 0;
        int freq2 = 0;

        for (int num : nums) {
            if (num == maj1) {
                freq1++;
            } else if (num == maj2) {
                freq2++;
            }
        }

        // Add candidates to the result if they meet the frequency condition
        if (freq1 >= condition) {
            ans.add(maj1);
        }

        if (freq2 >= condition) {
            ans.add(maj2);
        }

        // Return the list of majority elements (at most 2)
        return ans;
    }

}
