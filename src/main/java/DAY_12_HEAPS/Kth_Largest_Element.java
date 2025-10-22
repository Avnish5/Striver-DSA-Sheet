package DAY_12_HEAPS;

import java.util.PriorityQueue;

public class Kth_Largest_Element {

    /**
     * Time Complexity: O(n * logk) - where n is the length of the input string.
     *
     * Space Complexity: O(k)
     */
    public int findKthLargest(int[] nums, int k) {
        // Min-heap to store the K largest elements
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Traverse all numbers
        for (int val : nums) {
            pq.add(val); // Add current element

            // If heap exceeds K elements, remove the smallest
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // The root (smallest in heap) is the Kth largest overall
        return pq.peek();
    }
}
