package DAY_12_HEAPS;

import java.util.*;

public class Maximum_Sum_Combination {

    /**
     * 1. Brute-Force
     *
     * Time Complexity: O(n² * log k) - where n is the length of each input array.
     * Each pair sum insertion/removal in the heap takes O(log k), and there are n² pairs.
     *
     * Space Complexity: O(k) - for maintaining the heap that stores the top K sums.
     */
    public ArrayList<Integer> topKSumPairs1(int[] a, int[] b, int k) {
        int n = a.length;

        // Min-heap to keep track of the top K largest sums
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Iterate through all possible pairs (a[i], b[j])
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = a[i] + b[j]; // Compute the pair sum

                pq.add(sum); // Add sum to heap

                // If heap size exceeds K, remove the smallest sum
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }

        // Collect top K sums from the heap
        ArrayList<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }

        // Reverse to get descending order (largest to smallest)
        Collections.reverse(ans);

        return ans;
    }

    static class Pair implements Comparable<Pair> {
        int i, j, sum;

        public Pair(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }

        @Override
        public int compareTo(Pair o) {
            // Max-heap based on sum (larger sums have higher priority)
            return o.sum - this.sum;
        }
    }

    private void reverse(int[] arr) {
        int low = 0, high = arr.length - 1;

        while (low < high) {
            int temp = arr[low];
            arr[low] = arr[high];
            arr[high] = temp;
            low++;
            high--;
        }
    }

    /**
     * 1.Optimized

     *
     * Time Complexity: O(k * log k + n * log n) =  O(k * log k)
     *   - Each insertion and removal from the heap takes O(log k), repeated up to K times.
     *
     * Space Complexity: O(k)
     *   - For the heap and visited set that store up to K elements.
     */
    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        // Sort both arrays in descending order
        Arrays.sort(a);
        reverse(a);
        Arrays.sort(b);
        reverse(b);

        // Max-heap to store pair sums (largest sum at top)
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>();

        // Set to track visited index pairs (i, j)
        Set<String> visited = new HashSet<>();

        // Start with the largest possible sum: a[0] + b[0]
        maxHeap.add(new Pair(0, 0, a[0] + b[0]));
        visited.add("0#0");

        ArrayList<Integer> result = new ArrayList<>();
        int n = a.length;

        // Extract top K sums
        while (k > 0 && !maxHeap.isEmpty()) {
            Pair pair = maxHeap.poll();
            result.add(pair.sum);
            k--;

            int i = pair.i;
            int j = pair.j;

            // Push next pair (i+1, j) if valid and not already visited
            if (i + 1 < n && !visited.contains((i + 1) + "#" + j)) {
                maxHeap.add(new Pair(i + 1, j, a[i + 1] + b[j]));
                visited.add((i + 1) + "#" + j);
            }

            // Push next pair (i, j+1) if valid and not already visited
            if (j + 1 < n && !visited.contains(i + "#" + (j + 1))) {
                maxHeap.add(new Pair(i, j + 1, a[i] + b[j + 1]));
                visited.add(i + "#" + (j + 1));
            }
        }

        return result;
    }

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;

            PriorityQueue<int[]> pq =
                    new PriorityQueue<>((a, b) -> b[0] - a[0]);  // max-heap

            int[] result = new int[n - k + 1];
            int idx = 0;

            for (int i = 0; i < n; i++) {

                // push value + index
                pq.add(new int[] { nums[i], i });

                // remove elements that are out of the window (i - k + 1)
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }

                // when window hits size k
                if (i >= k - 1) {
                    result[idx++] = pq.peek()[0];
                }
            }

            return result;
        }
    }




}
