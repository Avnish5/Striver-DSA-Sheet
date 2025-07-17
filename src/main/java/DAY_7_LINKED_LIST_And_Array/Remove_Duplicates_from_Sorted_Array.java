package DAY_7_LINKED_LIST_And_Array;

public class Remove_Duplicates_from_Sorted_Array {

    /**
     * 1.
     *
     * Time Complexity: O(n) — we iterate through the array once.
     * Space Complexity: O(1) — no extra space used, only variables.
     */
    public int removeDuplicates1(int[] nums) {
        // currIndex tracks where to write the next unique value
        int currIndex = 0;

        // prev stores the current unique number being processed
        int prev = nums[0];

        // ans stores the number of unique elements found
        int ans = 0;

        // Start from index 1 since index 0 is already assigned to prev
        for (int i = 1; i < nums.length; i++) {
            // If current element is same as previous, skip it (duplicate)
            if (nums[i] == prev) continue;

            // If a new number is found, write the previous unique number
            // at currIndex, and move currIndex forward
            nums[currIndex] = prev;
            currIndex += 1;

            // Update prev to the new unique number
            prev = nums[i];

            // Increment the count of unique elements
            ans++;
        }

        // After loop ends, we need to write the last unique value
        nums[currIndex] = prev;
        ans++;

        // Return the count of unique elements
        return ans;
    }

    /**
     * 2
     *
     * Time Complexity: O(n) — each element is visited once.
     * Space Complexity: O(1) — constant space used.
     */
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;

        // Edge case: if the array is empty or has one element, it's already unique
        if (n == 0) return 0;

        // i is the slow pointer — it tracks the position of the last unique element
        // j is the fast pointer — it scans through the array
        int i = 0, j = 1;

        while (j < n) {
            // If a new unique element is found
            if (nums[i] != nums[j]) {
                i++;               // Move i to the next position
                nums[i] = nums[j]; // Overwrite duplicate at i with unique at j
            }
            j++; // Always move j forward
        }

        // The number of unique elements is i + 1
        return i + 1;
    }



}
