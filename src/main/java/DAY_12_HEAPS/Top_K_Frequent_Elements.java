package DAY_12_HEAPS;

import java.util.*;

public class Top_K_Frequent_Elements {

    /**
     * 1. Using Hashmap
     *
     * Time Complexity: O(n + m log m)
     *   - O(n): build frequency map
     *   - O(m log m): insert m unique elements into max-heap
     *   - O(k log m): extract top k elements
     *
     * Space Complexity: O(m)
     *   - HashMap and PriorityQueue store up to m unique elements.
     */

    public int[] topKFrequent1(int[] nums, int k) {
       Map<Integer, Integer> map = new HashMap<>();

       for (int val : nums) {
           map.put(val, map.getOrDefault(val, 0) + 1);
       }

       PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));

       for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
           pq.offer(entry);
       }

       int[] ans = new int[k];

       for (int i = 0; i < k; i++) {
           ans[i] = Objects.requireNonNull(pq.poll().getKey());
       }

       return ans;

    }

    /**
     * 2. Using Bucket Sort
     *
     * Time Complexity: O(n)
     *   - O(n): build frequency map
     *   - O(n): distribute elements into frequency buckets
     *   - O(n): iterate through buckets to collect top k elements
     *
     * Space Complexity: O(n)
     *   - HashMap stores up to n unique elements
     *   - Bucket array of size n + 1, each containing list of elements
     *   - Result array of size k
     */

    public int[] topKFrequent2(int[] nums, int k) {
        // Step 1: Count frequency of each number
        Map<Integer, Integer> map = new HashMap<>();
        for (int val : nums) {
            map.put(val, map.getOrDefault(val , 0) + 1);
        }

        // Step 2: Create buckets - index represents frequency
        // freq[i] = list of numbers that appear exactly i times
        List<Integer>[] freq = new ArrayList[nums.length + 1];

        // Initialize the bucket lists
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        // Step 3: Fill the buckets based on frequency
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            int frequency = entry.getValue();   // how many times the number appears
            freq[frequency].add(entry.getKey()); // add number to the corresponding bucket
        }

        // Step 4: Collect the top K frequent elements
        int[] res = new int[k];
        int idx = 0;

        // Traverse buckets from highest frequency to lowest
        for (int i = freq.length - 1; i >= 0; i--) {
            // For each number inside the bucket
            for (int num : freq[i]) {
                res[idx++] = num; // add to result

                // If we have collected K elements, return the result
                if (idx == k) {
                    return res;
                }
            }
        }

        // In case something goes wrong (should never happen)
        return new int[]{0};
    }

}
