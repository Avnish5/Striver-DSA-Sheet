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
        Map<Integer, Integer> map = new HashMap<>();

        for (int val : nums) {
            map.put(val, map.getOrDefault(val , 0) + 1);
        }

        List<Integer>[] freq = new ArrayList[nums.length + 2];

        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            int frequency = entry.getValue();
            freq[frequency].add(entry.getKey());
        }

        int[] res = new int[k];
        int idx = 0;

        for (int i = freq.length - 1; i >=0; i--) {
            for (int num : freq[i]) {
                res[idx++] = num;

                if (idx == k) {
                    return res;
                }
            }
        }

        return new int[]{0};

    }
}
